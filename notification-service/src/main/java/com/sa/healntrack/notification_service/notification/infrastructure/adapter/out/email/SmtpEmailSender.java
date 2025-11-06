package com.sa.healntrack.notification_service.notification.infrastructure.adapter.out.email;

import com.sa.healntrack.notification_service.common.application.exception.MailAuthenticatorException;
import com.sa.healntrack.notification_service.common.application.exception.MailSenderException;
import com.sa.healntrack.notification_service.common.application.exception.MessageException;
import com.sa.healntrack.notification_service.common.infrastructure.config.NotificationEmailProperties;
import com.sa.healntrack.notification_service.notification.application.port.out.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.internet.InternetAddress;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender mailSender;
    private final NotificationEmailProperties emailProps;

    @Override
    public void send(String toAddress, String toName, String subject, String htmlBody) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, StandardCharsets.UTF_8.name());

            helper.setFrom(emailProps.getFrom());

            if (StringUtils.isNotBlank(toName)) {
                helper.setTo(new InternetAddress(toAddress, toName));
            } else {
                helper.setTo(toAddress);
            }

            helper.setReplyTo(new InternetAddress("no-reply@healntrack.com", "No Reply"));

            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            mailSender.send(msg);

        } catch (MailAuthenticationException e) {
            throw new MailAuthenticatorException();
        } catch (MailSendException e) {
            throw new MailSenderException();
        } catch (MessagingException e) {
            throw new MessageException();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error enviando el correo electrónico a " + toAddress);
        }
    }
}
