package com.sa.healntrack.notification_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.kafka.listener")
public class KafkaListenerProperties {
    private Integer concurrency = 3;
}
