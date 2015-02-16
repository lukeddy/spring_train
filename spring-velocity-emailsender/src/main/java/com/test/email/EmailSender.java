package com.test.email;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component("emailSender")
public class EmailSender {

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;


    public void sendEmail(final String fromEmailAddress, final String toEmailAddresses ,final String []cc,
                           final String subject,final String body) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(toEmailAddresses);
                message.setFrom(new InternetAddress(fromEmailAddress));
                if(null!=cc&&cc.length>0){
                    message.setCc(cc);
                }
                message.setSubject(subject);
                //String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, null);
                message.setText(body, true);
            }
        };
        this.mailSender.send(preparator);
    }

    public void sendEmailWithAttachment(final String fromEmailAddress, final String toEmailAddresses ,final String [] cc,
                          final String subject,final String body, final String attachmentPath,
                          final String attachmentName) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(toEmailAddresses);
                message.setFrom(new InternetAddress(fromEmailAddress));
                if(null!=cc&&cc.length>0){
                    message.setCc(cc);
                }
                message.setSubject(subject);
                //String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, null);
                message.setText(body, true);
                if (!StringUtils.isBlank(attachmentPath)) {
                    FileSystemResource file = new FileSystemResource(attachmentPath);
                    message.addAttachment(attachmentName, file);
                }
            }
        };
        this.mailSender.send(preparator);
    }
}
