package com.sa.healntrack.notification_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "notification.topic")
public class NotificationTopicProperties {
    private String requested = "notifications.requested";
}
