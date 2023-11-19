package com.tdd.grupo5.medallero.util.smtp;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender smtp;

  public EmailService(JavaMailSender smtp) {
    this.smtp = smtp;
  }

  public void sendRestorePasswordLink(String to, String subject) {

    MimeMessage message = smtp.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setTo(to);
      helper.setFrom("noreply@medallero.com", "noreply@medallero.com");
      helper.setSubject(subject);
      helper.setText(
          """
                      <div>
                        Haga click en el link de abajo para ir a cambiar a una nueva clave:
                        <div>
                          <a href="https://grupo-5.2023.tecnicasdedisenio.com.ar/api/auth/password-update?mail=%s" target=_blank> Click </a>
                        </div>
                      </div>
                      """
              .formatted(to),
          true); // hay que meter el link con un attachment
      smtp.send(message);
    } catch (Exception e) {
      throw new RuntimeException("Error: No se pudo enviar el mail");
    }
  }
}
