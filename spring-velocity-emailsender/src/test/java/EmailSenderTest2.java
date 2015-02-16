import com.test.email.Alert;
import com.test.email.EmailSender;
import com.test.utils.Constants;
import com.test.utils.DateUtils;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tzq
 * Date: 13-6-6
 * Time: 下午6:24
 * To change this template use File | Settings | File Templates.
 */
public class EmailSenderTest2 extends TestBase {

    @Autowired
    @Qualifier("emailSender")
    private EmailSender emailSender;

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    private static final String ENCODING = "UTF-8";
    private static final String templatePrefix= "templates/";

    @Test
    public void testSendAlertEmailWithHeaderAndFooter() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String subject="I am subject";
        String logoPath="http://www.baidu.com/index.php?tn=monline_5_dg";
        String visionPointPath="http://pic6.nipic.com/20100415/4321211_002326376155_2.jpg";

        model.put("subject", subject);
        model.put("logoPath", logoPath);
        model.put("visionPointPath",visionPointPath);
        Alert alert=new Alert();
        alert.setAlertNumber("111111111");
        alert.setAlertText("this is test");
        alert.setSubject("This is subject.");
        alert.setAlertCard("http://pic6.nipic.com/20100415/4321211_002326376155_2.jpg");
        alert.setDate(DateUtils.getDate(DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
        alert.setContact("xxx@example.com\n");
        alert.setDistribution("This is description message.");

        model.put("alert",alert);

        model.put("css",MessageFormat.format("{0}htmlEmailTemplate.css", templatePrefix));
        model.put("header",getTemplateFile("GlobalHeader",true));
        model.put("title","Hello this is page title");
        model.put("baseUrl","http://www.sinaimg.cn");
        model.put("footer",getTemplateFile("GlobalFooter",true));
        model.put("companyName","tzq");
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, getTemplateFile("MyAlert",true), ENCODING, model);
        String []cc=new String[]{"testaaa123456@163.com","testaaa123456@163.com"};
        emailSender.sendEmail(Constants.TEST_EMAIL, Constants.TEST_EMAIL,cc, subject, body);
    }

    private String getTemplateFile(String baseName, boolean sendHtml)
    {
        String name = "{0}" + baseName + (sendHtml ? "-HtmlText.vm" : "-PlainText.vm");
        return MessageFormat.format(name, templatePrefix);
    }
}
