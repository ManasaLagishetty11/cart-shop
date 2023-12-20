package com.evoke.cartshop.services;

import com.evoke.cartshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private MailSender mailSender;

    public void sendMessage(SimpleMailMessage simpleMailMessage) {
        this.mailSender.send(simpleMailMessage);
    }

    public void sendMailtoUserForSuccessfulRegistration(User user) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("manasalagishetty11@gmail.com");
        simpleMailMessage.setTo("manasa110996@gmail.com");
        simpleMailMessage.setSubject("Welcome to Cart Shop "+user.getFirstName());
        simpleMailMessage.setText("Your registration process was successfully completed.");
        sendMessage(simpleMailMessage);
    }

    public void sendMailtoUserAfterOrderConformation(User user) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("manasalagishetty11@gmail.com");
        simpleMailMessage.setTo("manasa110996@gmail.com");
        simpleMailMessage.setSubject("Order Confirmation");
        simpleMailMessage.setText("Hi "+user.getFirstName()+". your order confirmed");
        sendMessage(simpleMailMessage);

    }
}
