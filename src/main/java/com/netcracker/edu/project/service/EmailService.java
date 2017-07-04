package com.netcracker.edu.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;


//@EnableScheduling
@Component
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

//    @Scheduled(fixedRate = 50000)
//    public void run(){
//        sendMessage("vlad.dodon25@gmail.com", "test", "myMessage");
//        System.out.println("sendMessage");
//    }

    public void sendMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessageWithFiles(String to, String subject, String text, List<File> files) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            for(File file : files) {
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                helper.addAttachment("Invoice", fileSystemResource);
            }

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
