import com.test.email.Alert;
import com.test.email.EmailSender;
import com.test.utils.DateUtils;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;


public class EmailSenderTest extends TestBase {

    @Autowired
    @Qualifier("emailSender")
    private EmailSender emailSender;

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    private static final String ENCODING = "UTF-8";
    private static final String FROM_EMAIL ="testaaa123456@163.com";
    private static final String TO_EMAIL="testaaa123456@163.com";


    @Test
    public void testSendSimpleEmail() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        emailSender.sendEmail(FROM_EMAIL,TO_EMAIL,null, "Test Email,同步邮件", body);
    }

    @Test
    public void testSendSimpleEmailAsync() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity，异步发送邮件标题");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        emailSender.sendAsynchronousEmail(FROM_EMAIL, TO_EMAIL, null, "Test Email,异步发送邮件标题", body);
        //需要添加一个延迟时间，否则还没等发送主线程就结束了
        Thread.sleep(10000);
    }

    @Test
    public void testSendSimpleEmailWithAttachment() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        emailSender.sendEmailWithAttachment(FROM_EMAIL, TO_EMAIL,null, "Test email with attachment", body,
                "C:\\Users\\Administrator\\Desktop\\discover_unusual_pets_competition\\Cover.jpg", "Doge.jpg");
    }
    @Test
    public void testSendSimpleEmailWithAttachmentAsync() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        emailSender.sendAsynchronousEmailWithAttachMent(FROM_EMAIL,TO_EMAIL,null, "Test email with attachment", body,
                "C:\\Users\\Administrator\\Desktop\\discover_unusual_pets_competition\\Cover.jpg", "Doge.jpg");
        //需要添加一个延迟时间，否则还没等发送主线程就结束了
        Thread.sleep(10000);
    }
    @Test
    public void testSendEmail() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmail(FROM_EMAIL, TO_EMAIL,cc, "Test Email", body);
    }
    @Test
    public void testSendEmail2() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        Alert alert=new Alert();
        alert.setAlertNumber("111111111");
        alert.setAlertText("this is test");
        alert.setAlertCard("http://www.sinaimg.cn/qc/photo_auto/photo/38/16/26963816/26963816_950.jpg");
        alert.setDate(DateUtils.getDate(DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
        alert.setContact("xxx@example.com\n" +
                "XXX Department\n");
        alert.setDistribution("This is description message.");
        model.put("alert",alert);
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test_alert.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmail(FROM_EMAIL, TO_EMAIL, cc,"Test Email", body);
    }

    @Test
    public void testSendEmailWithAttachment() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmailWithAttachment(FROM_EMAIL, TO_EMAIL,cc, "Test email with attachment", body,
                "C:\\Users\\Administrator\\Desktop\\discover_unusual_pets_competition\\Cover.jpg", "doge.jpg");
    }
    @Test
    public void testSendEmailWithAttachment2() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "Hello I am title");
        model.put("content", "It's great to meet you :)");
        Alert alert=new Alert();
        alert.setAlertNumber("111111111");
        alert.setAlertText("this is text");
        alert.setSubject("This is subject.");
        alert.setAlertCard("http://www.sinaimg.cn/qc/photo_auto/photo/38/16/26963816/26963816_950.jpg");
        alert.setDate(DateUtils.getDate(DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
        alert.setContact("xxx@example.com\n" +
                "XXX Department\n");
        alert.setDistribution("This is description message.");
        model.put("alert",alert);
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test_alert.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmailWithAttachment(FROM_EMAIL, TO_EMAIL,cc, "Test email with attachment", body,
                "C:\\Users\\Administrator\\Desktop\\discover_unusual_pets_competition\\Cover.jpg", "doge.jpg");
    }

    @Test
    public void testSendAlertEmail() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String subject="I am subjtct";
        String logoPath="http://img1.imgtn.bdimg.com/it/u=2088418210,1208956161&fm=23&gp=0.jpg";
        String visionPointPath="http://img.cheshi-img.com/meinv/0/2010/0406/4bbaf33e290bd.jpg";

        model.put("subject", subject);
        model.put("logoPath", logoPath);
        model.put("visionPointPath",visionPointPath);
        Alert alert=new Alert();
        alert.setAlertNumber("111111111");
        alert.setAlertText("this is test");
        alert.setAlertCard("http://pic6.nipic.com/20100415/4321211_002326376155_2.jpg");
        alert.setDate(DateUtils.getDate(DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
        alert.setContact("xxx@example.com\n" +
                "aaaa Department\n");
        alert.setDistribution("This is description message.");

        model.put("alert",alert);
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/alert.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmail(FROM_EMAIL, TO_EMAIL,cc, subject, body);
    }

    @Test
    public void testSendAlertEmailWithAttachment() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String subject="Queue Line Threshold Exceeded";
        String logoPath="http://www.baidu.com/index.php?tn=monline_5_dg";
        String visionPointPath="http://pic6.nipic.com/20100415/4321211_002326376155_2.jpg";

        model.put("subject", subject);
        model.put("logoPath", logoPath);
        model.put("visionPointPath",visionPointPath);
        Alert alert=new Alert();
        alert.setAlertNumber("111111111");
        alert.setAlertText("this is test");
        alert.setAlertCard("http://pic6.nipic.com/20100415/4321211_002326376155_2.jpg");
        alert.setDate(DateUtils.getDate(DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
        alert.setContact("xxx@example.com\n");
        alert.setDistribution("This is description message.");

        model.put("alert",alert);
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/alert.vm", ENCODING, model);
        String []cc=new String[]{"2981025303@qq.com","544301172@qq.com"};

        emailSender.sendEmailWithAttachment(FROM_EMAIL,TO_EMAIL, cc, "Test email with attachment", body,
                "C:\\Users\\Administrator\\Desktop\\discover_unusual_pets_competition\\Cover.jpg", "doge.jpg");
    }

}
