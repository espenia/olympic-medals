package com.tdd.grupo5.medallero.util.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired private JavaMailSender smtp;

  public void sendRestorePasswordLink(String to, String subject, String text) {

    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(to);
    message.setFrom("noreply@medallero.com");
    message.setSubject(subject);
    message.setText(text); // hay que meter el link con un attachment

    smtp.send(message);
  }
}
