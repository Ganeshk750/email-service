package com.ganesh.service;

public interface EmailService {

    void simpleMailMessage(String name, String to, String token);

    void sendMimeMessageWithAttachments(String name, String to, String token);

    void sendMimeMessageWithEmbeddedImages(String name, String to, String token);

    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);

    void sendHtmlEmail(String name, String to, String token);

    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
