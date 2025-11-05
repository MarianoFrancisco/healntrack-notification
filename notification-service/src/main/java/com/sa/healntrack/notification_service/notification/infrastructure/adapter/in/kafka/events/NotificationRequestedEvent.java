package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationRequestedEvent {

    private String requestId;
    private String to;
    private String toName;
    private String subject;
    private String bodyHtml;
}
