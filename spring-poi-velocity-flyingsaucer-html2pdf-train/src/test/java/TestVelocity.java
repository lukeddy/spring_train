import com.tangzq.generator.PdfDocumentGenerator;
import com.tangzq.tools.ResourceLoader;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

public class TestVelocity extends TestBase {

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    private static final String ENCODING = "UTF-8";

    @Test
    public void testSendSimpleEmail() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("policyNo","1234567890123456");
        model.put("holderName","丽丽张123丽丽张123");
        model.put("insuredName","丽丽张123丽丽张123丽丽张123丽丽张123");
        model.put("beneficiaryName","测试受益人姓名");
        model.put("branchName","北京");
        model.put("companyName","科索沃公司");
        model.put("destination","英国,俄罗斯,冰岛,日内瓦,威尼斯小镇");
        model.put("holderAdress","北京市屋顶后街金融大街14号中国人寿广场xxx曾x101室");
        model.put("holderPostCode","123456");
        model.put("insuredBirthday","2013-11-10");
        model.put("insuredName","爱新觉罗启星");
        model.put("insuredPassportNo","测试护照号码123456789");
        model.put("insuredPhone","13112345678");
        model.put("insuredPingyinName","aixinjuluoqixing");
        model.put("insuredSex","女");
        model.put("issueDate","2015-07-30");
        model.put("period","十一年");
        model.put("premium","1009.00");
        model.put("relation","子女");
        model.put("remarks","这是一张测试保单,仅为测试,学习所用,请勿转载");
        model.put("accidentalSumInsured","150000");
        model.put("emergencySumInsured", "500000");
        model.put("medicalSumInsured", "220000");
        model.put("logoPath", "http://www.shikezhi.com/assets/images/logo.png");

        //1.将数据填充进页面生成字符串
        String htmlContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mytemplate.html", ENCODING, model);
        //System.out.println(htmlContent);

        //将生成后的字符串转换成pdf文档
        PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
        pdfGenerator.generate(htmlContent,System.currentTimeMillis()+".pdf");

    }
}
