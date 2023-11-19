package com.tdd.grupo5.medallero.util.smtp;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender smtp;

  public EmailService(JavaMailSender smtp) {
    this.smtp = smtp;
  }

  public void sendRestorePasswordLink(String to, String url, String subject) {

    MimeMessage message = smtp.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setTo(to);
      helper.setFrom("noreply@medallero.com", "noreply@medallero.com");
      helper.setSubject(subject);
      helper.setText(
          String.format(
                  """
                      <div>
                        Haga click en el link de abajo para ir a cambiar a una nueva clave:
                        <div>
                          <a href="%s" target=_blank> Click </a>
                        </div>
                      </div>
                      """,
                  url)
              .formatted(to),
          true); // hay que meter el link con un attachment
      smtp.send(message);
    } catch (Exception e) {
      throw new RuntimeException("Error: No se pudo enviar el mail");
    }
  }
}
