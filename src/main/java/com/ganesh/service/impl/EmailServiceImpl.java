package com.ganesh.service.impl;

import com.ganesh.service.EmailService;
import com.ganesh.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    public static final String UTF_8_ENCODING = "UTF-8";
    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;

    @Override
    @Async
    public void simpleMailMessage(String name, String to, String token) {
       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setSubject("New User Account Verification");
           message.setFrom(fromEmail);
           message.setTo(to);
          // message.setText("Hey This is SimpleMailMessage Testing.");
           message.setText(EmailUtils.getEmailMessage(name, host, token));
           emailSender.send(message);
       }catch (Exception exception){
           System.out.println(exception.getMessage());
           throw new RuntimeException(exception.getMessage());
       }

    }

    @Override
    @Async
    public void sendMimeMessageWithAttachments(String name, String to, String token) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("New User Account Verification");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, token));
            // Add Attachment form file system
            FileSystemResource first = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/first.png"));
            FileSystemResource second = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/second.png"));
            FileSystemResource third = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/third.rtf"));
            helper.addAttachment(first.getFilename(), first);
            helper.addAttachment(second.getFilename(), second);
            helper.addAttachment(third.getFilename(), third);
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedImages(String name, String to, String token) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("New User Account Verification");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, token));
            // Add Attachment form file system
            FileSystemResource first = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/first.png"));
            FileSystemResource second = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/second.png"));
            FileSystemResource third = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/third.rtf"));
            helper.addInline(getContentId(first.getFilename()), first);
            helper.addInline(getContentId(second.getFilename()), second);
            helper.addInline(getContentId(third.getFilename()), third);
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {

    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    private String getContentId(String fileName){
      return "<" + fileName + ">";
    }
}
