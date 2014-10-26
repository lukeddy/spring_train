package com.tzq.controller;

import com.tzq.email.Alert;
import com.tzq.email.EmailSender;
import com.tzq.util.Constants;
import com.tzq.util.DateUtils;
import com.tzq.util.RequestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/admin")
public class EmailController {
    Logger LOGGER = LoggerFactory.getLogger("EmailController");

    @Autowired
    @Qualifier("emailSender")
    private EmailSender emailSender;

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    private static final String ENCODING = "UTF-8";

    //跳转到上传页面
    @RequestMapping(value = "/emailpage")
    public String goToUploadPage() {
        return "send_email";
    }
    @RequestMapping("/send")
    public ModelAndView play(@RequestParam("toEmail") String toEmail, HttpServletRequest request,ModelMap modelMap){
        String msg="";
        try {
            testSendAlertEmail(toEmail,request);
            msg= "Email sended successfully!";
        } catch (Exception e) {
            msg= "Email sended failed!";
            e.printStackTrace();
        }

        modelMap.addAttribute("msg",msg);
        return new ModelAndView("send_email",modelMap);
    }
    public void testSendAlertEmail(String toEmail,HttpServletRequest request) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String subject="我是发送主题";
        String logoPath= RequestUtils.getApplicationBaseURL(request)+"/images/logo.jpg";
        String visionPointPath=RequestUtils.getApplicationBaseURL(request)+"/images/banner.png";

        model.put("subject", subject);
        model.put("logoPath", logoPath);
        model.put("visionPointPath",visionPointPath);
        Alert alert=new Alert();
        alert.setAlertNumber("10000110");
        alert.setDate(DateUtils.getDate(DateUtils.DD_MM_YYYY_HH_MM_AA));
        alert.setSubject(subject);
        alert.setDistribution("为所有开发者、所有应用场景而设计");
        alert.setAlertText("Bootstrap 让前端开发更快速、简单。所有开发者都能快速上手、所有设备都可以适配、所有项目都适用。");
        alert.setAlertCard(RequestUtils.getApplicationBaseURL(request)+"/images/alert.jpg");
        alert.setContact("tel:1111111111<br/>" +
                "test@aaa.com<br/>");

        model.put("alert",alert);
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/alert.vm", ENCODING, model);
        emailSender.sendEmail(Constants.TEST_EMAIL, toEmail,null, subject, body);
    }
}