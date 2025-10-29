package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotificationRequestedEvent {
    public String requestId;
    public String to;
    public String toName;
    public String subject;
    public String bodyHtml;
}
