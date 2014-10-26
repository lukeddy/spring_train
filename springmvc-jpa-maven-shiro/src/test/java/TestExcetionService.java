import com.tang.dao.util.Pagination;
import com.tang.entity.Permission;
import com.tang.entity.Role;
import com.tang.entity.TException;
import com.tang.entity.User;
import com.tang.service.IExceptionService;
import com.tang.service.IPermissionService;
import com.tang.service.IRoleService;
import com.tang.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * UserService测试类
 */
public class TestExcetionService extends TestBase {

    @Autowired
    private IExceptionService exceptionService;

    @Autowired
    private IUserService userService;

    @Test
    public void testAddException(){
        TException ex=new TException();
        ex.setClazz("com.text.Aclass");
        ex.setActiveTime(Calendar.getInstance().getTime());
        ex.setMethodName("testMethod");
        ex.setException("There is no statement named Demo.queryBalanceInfo1 in this SqlMap.");
        Boolean isSaved= exceptionService.add(ex);
        Assert.isTrue(isSaved);

    }

    @Test
    public void testException(){
        try {
            userService.insertTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
