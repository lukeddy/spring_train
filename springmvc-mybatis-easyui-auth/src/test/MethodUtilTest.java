package test;


import org.junit.Assert;
import org.junit.Test;
import util.core.MethodUtil;


public class MethodUtilTest {

	 @Test
     public void testGetDes(){
         MethodUtil mu=new MethodUtil();
         String desStr=mu.getDES("desKey!@#", "admin", 0);
         Assert.assertNotNull(desStr);
         System.out.println(desStr);

     }
}
