package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.TbsRoleBean;

import service.TbsRoleService;
import util.json.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-common.xml","classpath:spring-mvc.xml"})
public class RoleTest {
	 
	 @Autowired
	 TbsRoleService<TbsRoleBean> tbsRoleService;
	 TbsRoleBean tbsRoleBean=new TbsRoleBean();
	 @Test
     public void testRole() throws Exception{
		 tbsRoleBean.setId("11");
		 tbsRoleBean = tbsRoleService.selectByEntiry(tbsRoleBean).get(0);
		 System.out.println(JSONUtil.toJSONString(tbsRoleBean));
     }
}
