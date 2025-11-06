package com.sa.healntrack.notification_service.notification.application.port.in.send_notification;


public record SendNotificationCommand(
        String requestId,
        String to,
        String toName,
        String subject,
        String bodyHtml
) {

}
