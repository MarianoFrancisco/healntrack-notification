package com.sa.healntrack.notification_service.notification.infrastructure.adapter.out.email;

import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import com.sa.healntrack.notification_service.common.application.exception.PermanentInfrastructureException;
import com.sa.healntrack.notification_service.common.application.exception.TransientInfrastructureException;
import com.sa.healntrack.notification_service.common.infrastructure.config.NotificationEmailProperties;
import com.sa.healntrack.notification_service.notification.application.port.out.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.SendFailedException;
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
            throw new PermanentInfrastructureException(ErrorCode.SMTP_AUTH_FAILED);
        } catch (MailSendException e) {
            if (e.getCause() instanceof SendFailedException || String.valueOf(e.getMessage()).contains("550")) {
                throw new PermanentInfrastructureException(ErrorCode.SMTP_PERMANENT_FAILURE);
            }
            throw new TransientInfrastructureException(ErrorCode.SMTP_TRANSIENT_FAILURE, e);
        } catch (MessagingException e) {
            String msg = e.getMessage() != null ? e.getMessage() : "";
            if (msg.contains("Read timed out") || msg.contains("Could not connect") || msg.contains("421")) {
                throw new TransientInfrastructureException(ErrorCode.SMTP_TRANSIENT_FAILURE, e);
            }
            throw new PermanentInfrastructureException(ErrorCode.SMTP_PERMANENT_FAILURE, e);
        } catch (Exception e) {
            throw new TransientInfrastructureException(ErrorCode.UNKNOWN_ERROR, e);
        }
    }
}
