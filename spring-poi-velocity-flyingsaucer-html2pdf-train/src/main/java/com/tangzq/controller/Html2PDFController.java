package com.tangzq.controller;

import com.tangzq.generator.PdfDocumentGenerator;
import com.tangzq.tools.ResourceLoader;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * html转换成pdf控制器
 */
@Controller
@RequestMapping(value="/html2pdf")
public class Html2PDFController {

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    private static final String ENCODING = "UTF-8";

    private Map<String,Object> model;

    public Html2PDFController(){
        model = new HashMap<String, Object>();
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

    }

    /**
     * 预览网页
     * @param response
     */
    @RequestMapping(value="/preview")
    public void previewHTML(
            HttpServletResponse response
    ){
        //填充模版中的数据
        String htmlContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mytemplate.html", ENCODING, model);
        //输出数据
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(htmlContent);
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * 下载生成的PDF
     */
    @RequestMapping(value="/download")
    public void downloadPDF(
            HttpServletResponse response
    ){
        //填充模版中的数据
        String htmlContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mytemplate.html", ENCODING, model);
        //生成pdf并输出

        String filename=System.currentTimeMillis()+".pdf";
        response.setContentType("image/png,image/jpeg");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        try{
            OutputStream outputStream= response.getOutputStream();
            PdfDocumentGenerator pdfDocumentGenerator=new PdfDocumentGenerator();
            pdfDocumentGenerator.generate(htmlContent,outputStream);
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
