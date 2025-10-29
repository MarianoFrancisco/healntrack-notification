package com.sa.healntrack.notification_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notification.email")
public class NotificationEmailProperties {
    private String from = "no-reply@healntrack.com";
}
