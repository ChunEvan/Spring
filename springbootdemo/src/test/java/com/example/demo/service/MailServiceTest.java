package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    void test(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("你好");
        simpleMailMessage.setText("啦啦啦啦啦");
        simpleMailMessage.setTo("huangchunyx@163.com");
        simpleMailMessage.setFrom("huangchunyx@163.com");
        mailSender.send(simpleMailMessage);
    }

    @Test
    void test2() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("哈哈");
        mimeMessageHelper.setText("<p style='color:red'>你好</p>", true);
        mimeMessageHelper.addAttachment("1.jpg",new File("1.jpg"));
        mimeMessageHelper.setTo("huangchunyx@163.com");
        mimeMessageHelper.setFrom("huangchunyx@163.com");
        mailSender.send(mimeMessageHelper.getMimeMessage());
    }



}
