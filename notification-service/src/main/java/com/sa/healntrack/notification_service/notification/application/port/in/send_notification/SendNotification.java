package com.sa.healntrack.notification_service.notification.application.port.in.send_notification;

public interface SendNotification {
    void handle(SendNotificationCommand command);
}
