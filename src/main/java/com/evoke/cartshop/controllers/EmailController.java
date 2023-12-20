package com.evoke.cartshop.controllers;

import com.evoke.cartshop.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("manasalagishetty11@gmail.com");
        simpleMailMessage.setTo("manasa110996@gmail.com");
        simpleMailMessage.setSubject("Test");
        simpleMailMessage.setText("test for email");
        emailService.sendMessage(simpleMailMessage);
        return "Email sent successfully";
    }
}
