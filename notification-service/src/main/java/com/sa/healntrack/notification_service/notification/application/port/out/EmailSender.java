package com.sa.healntrack.notification_service.notification.application.port.out;

public interface EmailSender {
    void send(String toAddress, String toName, String subject, String htmlBody);
}
