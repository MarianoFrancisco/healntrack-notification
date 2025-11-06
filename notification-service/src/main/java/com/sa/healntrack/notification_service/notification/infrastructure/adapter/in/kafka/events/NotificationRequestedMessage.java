package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events;

public record NotificationRequestedMessage(
        String requestId,
        String to,
        String toName,
        String subject,
        String bodyHtml
) {
}
