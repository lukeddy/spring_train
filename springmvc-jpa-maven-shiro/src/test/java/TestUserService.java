import com.tang.dao.util.Pagination;
import com.tang.dao.util.QueryCondition;
import com.tang.entity.Permission;
import com.tang.entity.Role;
import com.tang.entity.User;
import com.tang.service.IPermissionService;
import com.tang.service.IRoleService;
import com.tang.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService测试类
 */
public class TestUserService extends TestBase {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    /**
     * 添加用户
     */

    @Test
    public void testAddUser(){
        User user=new User();
        user.setAccount("admin");
        user.setPassword("admin");
        user.setNickname("Administrator account");

        Assert.isTrue(userService.register(user));
        User user2=new User();
        user2.setAccount("test");
        user2.setPassword("test");
        user2.setNickname("test account");
        Assert.isTrue(userService.register(user2));
    }

    /**
     * 添加权限
     */
    @Test
    public void testAddPermission(){
        Permission p1=new Permission();
        p1.setName("add");
        p1.setDescription("添加操作");
        p1.setPermission("add_permission");
        Assert.isTrue(permissionService.add(p1));
        Permission p2=new Permission();
        p2.setName("update");
        p2.setDescription("更新操作");
        p2.setPermission("update_permission");
        Assert.isTrue(permissionService.add(p2));
        Permission p3=new Permission();
        p3.setName("del");
        p3.setDescription("删除操作");
        p3.setPermission("del_permission");
        Assert.isTrue(permissionService.add(p3));
        Permission p4=new Permission();
        p4.setName("query");
        p4.setDescription("查询操作");
        p4.setPermission("query_permission");
        Assert.isTrue(permissionService.add(p4));
    }

    /**
     * 添加角色
     */
    @Test
    public void testAddRole(){

         Role role=new Role();
         role.setName("admin");
         //role.setPmss(perList);
         role.setDescription("admin role");

        // role.setUsers(users);

         Assert.isTrue(roleService.add(role));

    }

    /**
     * 添加角色2
     */
    @Test
    public void testAddSaleRole(){
        Role role=new Role();
        role.setName("sale");
        role.setDescription("sale role");
        Assert.isTrue(roleService.add(role));
    }

    /**
     * 添加角色2
     */
    @Test
    public void testEditSaleRole(){
        Role role=roleService.findByName("sale");
        Permission p=permissionService.findByName("query");
        List<Permission> permissionList=new ArrayList<Permission>();
        permissionList.add(p);
        role.setPmss(permissionList);
        roleService.update(role);
    }

    /**
     * 给角色添加权限
     */
    @Test
    public void testEditRole(){
        List<Permission> perList=permissionService.findAll();
        User user=userService.getByAccount("admin");
        List<User> users=new ArrayList<User>();
        users.add(user);

        Role role=roleService.findByName("admin");
        role.setPmss(perList);
        role.setUsers(users);

        roleService.update(role);
    }
    /**
     * 给角色添加权限2
     */
    @Test
    public void testEditUser2(){

        //Role adminRole=roleService.findByName("admin");

        Role saleRole=roleService.findByName("sale");


        List<Role> roleList=new ArrayList<Role>();
        //roleList.add(adminRole);
        roleList.add(saleRole);

        User user=userService.getByAccount("test");
        user.setRoles(roleList);
        userService.update(user);
    }

    /**
     * 给用户添加角色
     */
    @Test
    public void setRoleForUser(){
        Role role=roleService.findByName("admin");
        List<Role> roles=new ArrayList<Role>();
        roles.add(role);

        User user=userService.getByAccount("admin");
        user.setRoles(roles);
        userService.update(user);
    }


    /**
     * 查找用户
     * 注:异常org.hibernate.LazyInitializationException: failed to lazily initialize a collection，
     * 解决：http://stackoverflow.com/questions/10769656/how-to-use-jpa-2-0-manytomany-without-issues
     */
    @Test
    public void testFindUser(){
       User user=userService.getByAccount("admin");
       Assert.notNull(user);
       for(Role r:user.getRoles()){
           System.out.println("role:"+r.getName()+","+r.getDescription());
           for(Permission p:r.getPmss()){
               System.out.println("permission:"+p.getName()+","+p.getDescription());
           }
       }
    }

    /**
     * 分页查找测试
     */
    @Test
    public void testPaginationFind(){
        //QueryCondition qc=new QueryCondition("name",QueryCondition.EQ,"");
        Pagination<User> pagination=userService.getPagination(User.class,null,"order by account asc",1,2);
        if(null!=pagination){
            for(User u:pagination.getRecordList()){
                System.out.println(u.getAccount()+","+u.getNickname());
            }
        }
    }

}
