package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.mappers;

import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotificationCommand;
import com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events.NotificationRequestedMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationRequestedEventMapper {

    public SendNotificationCommand toCommand(NotificationRequestedMessage e) {
        return new SendNotificationCommand(
                e.requestId(),
                e.to(),
                e.toName(),
                e.subject(),
                e.bodyHtml()
        );
    }
}
