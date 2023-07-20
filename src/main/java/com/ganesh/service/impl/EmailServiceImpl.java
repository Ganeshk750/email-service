package com.ganesh.service.impl;

import com.ganesh.service.EmailService;
import com.ganesh.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

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

    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedImages(String name, String to, String token) {

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
}
