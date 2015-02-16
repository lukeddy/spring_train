import com.test.email.Alert;
import com.test.email.EmailSender;
import com.test.utils.Constants;
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
    private static final String templatePrefix="Test";

    @Test
    public void testSendEmail() throws Exception {
        Map<String, String> model = new HashMap<String, String>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmail(Constants.TEST_EMAIL, Constants.TEST_EMAIL,cc, "Test Email", body);
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
        emailSender.sendEmail(Constants.TEST_EMAIL, Constants.TEST_EMAIL, cc,"Test Email", body);
    }

    @Test
    public void testSendEmailWithAttachment() throws Exception {
        Map<String, String> model = new HashMap<String, String>();
        model.put("title", "Hello velocity");
        model.put("content", "It's great to meet you :)");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmailWithAttachment(Constants.TEST_EMAIL, Constants.TEST_EMAIL,cc, "Test email with attachment", body,
                "X:\\TODOS\\java\\ProgressiveServer\\src\\main\\webapp\\WEB-INF\\images\\alert.jpg", "WEB-INF/images/logo.jpg");
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
        emailSender.sendEmailWithAttachment(Constants.TEST_EMAIL, Constants.TEST_EMAIL,cc, "Test email with attachment", body,
                "X:\\TODOS\\java\\ProgressiveServer\\src\\main\\webapp\\WEB-INF\\images\\alert.jpg", "WEB-INF/images/logo.jpg");
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
        emailSender.sendEmail(Constants.TEST_EMAIL, Constants.TEST_EMAIL,cc, subject, body);
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
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};

        emailSender.sendEmailWithAttachment(Constants.TEST_EMAIL, Constants.TEST_EMAIL, cc, "Test email with attachment", body,
                "X:\\TODOS\\java\\ProgressiveServer\\src\\main\\webapp\\WEB-INF\\images\\alert.jpg", "WEB-INF/images/logo.jpg");
    }

}
