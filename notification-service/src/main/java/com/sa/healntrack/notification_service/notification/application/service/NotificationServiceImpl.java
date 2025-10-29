package com.sa.healntrack.notification_service.notification.application.service;

import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotification;
import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotificationCommand;
import com.sa.healntrack.notification_service.notification.application.port.out.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements SendNotification {

    private final EmailSender emailSender;

    @Override
    public void handle(SendNotificationCommand cmd) {
        emailSender.send(cmd.getRecipient().getAddress(), cmd.getRecipient().getName(), cmd.getSubject(), cmd.getBodyHtml());
    }
}
