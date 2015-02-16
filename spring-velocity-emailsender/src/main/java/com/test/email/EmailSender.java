package com.test.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 发送邮件工具类
 */
@Component("emailSender")
public class EmailSender {

    public final static Log logger = LogFactory.getLog(EmailSender.class);
    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;

    @Autowired
    private TaskExecutor taskExecutor;

    /**
     * 同步发送邮件方法
     * @param fromEmailAddress  发送人
     * @param toEmailAddresses  收件人
     * @param cc                抄送人
     * @param subject           邮件标题
     * @param body              邮件内容
     */
    public void sendEmail(final String fromEmailAddress, final String toEmailAddresses ,final String []cc,
                          final String subject,final String body) {
        try{
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
            logger.info("给用户[" + toEmailAddresses + "]发送邮件成功");
        } catch (Exception e) {
            logger.error("给用户[" + toEmailAddresses + "]发送邮件失败！",e);
        }
    }

    /**
     * 同步发送带附件的邮件方法
     * @param fromEmailAddress  发件人
     * @param toEmailAddresses  收件人
     * @param cc                抄送人
     * @param subject           邮件标题
     * @param body              邮件内容
     * @param attachmentPath    附件路径
     * @param attachmentName    附件名字
     */
    public void sendEmailWithAttachment(final String fromEmailAddress, final String toEmailAddresses ,final String [] cc,
                                        final String subject,final String body, final String attachmentPath,
                                        final String attachmentName) {
        try{
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
            logger.info("给用户[" + toEmailAddresses + "]发送邮件成功");
        } catch (Exception e) {
            logger.error("给用户[" + toEmailAddresses + "]发送邮件失败！",e);
        }
    }


    /**
     * 异步发送邮件方法
     * @param fromEmailAddress  发送人
     * @param toEmailAddresses  收件人
     * @param cc                抄送人
     * @param subject           邮件标题
     * @param body              邮件内容
     */
    public void sendAsynchronousEmail(final String fromEmailAddress, final String toEmailAddresses ,final String []cc,
                                      final String subject,final String body) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    MimeMessagePreparator preparator = new MimeMessagePreparator() {
                        public void prepare(MimeMessage mimeMessage) throws Exception {
                            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                            message.setTo(toEmailAddresses);
                            message.setFrom(new InternetAddress(fromEmailAddress));
                            if (null != cc && cc.length > 0) {
                                message.setCc(cc);
                            }
                            message.setSubject(subject);
                            //String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/test.vm", ENCODING, null);
                            message.setText(body, true);
                        }
                    };
                    mailSender.send(preparator);
                    logger.info("给用户[" + toEmailAddresses + "]发送邮件成功");
                } catch (Exception e) {
                    logger.error("给用户[" + toEmailAddresses + "]发送邮件失败！", e);
                }
            }
        });
    }

    /**
     * 异步发送带附件的邮件方法
     * @param fromEmailAddress  发件人
     * @param toEmailAddresses  收件人
     * @param cc                抄送人
     * @param subject           邮件标题
     * @param body              邮件内容
     * @param attachmentPath    附件路径
     * @param attachmentName    附件名字
     */
    public void sendAsynchronousEmailWithAttachMent(final String fromEmailAddress, final String toEmailAddresses ,final String [] cc,
                                                    final String subject,final String body, final String attachmentPath,
                                                    final String attachmentName) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    MimeMessagePreparator preparator = new MimeMessagePreparator() {
                        public void prepare(MimeMessage mimeMessage) throws Exception {
                            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                            message.setTo(toEmailAddresses);
                            message.setFrom(new InternetAddress(fromEmailAddress));
                            if (null != cc && cc.length > 0) {
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
                    mailSender.send(preparator);
                    logger.info("给用户[" + toEmailAddresses + "]发送邮件成功");
                } catch (Exception e) {
                    logger.error("给用户[" + toEmailAddresses + "]发送邮件失败！", e);
                }
            }
        });
    }
}
