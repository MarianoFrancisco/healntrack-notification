package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.mappers;

import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotificationCommand;
import com.sa.healntrack.notification_service.notification.domain.Recipient;
import com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events.NotificationRequestedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationRequestedEventMapper {

    public static SendNotificationCommand toCommand(NotificationRequestedEvent e) {
        return new SendNotificationCommand(
                e.getRequestId(),
                new Recipient(e.getTo(), e.getToName()),
                e.getSubject(),
                e.getBodyHtml()
        );
    }
}
