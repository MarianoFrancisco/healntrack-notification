package com.sa.healntrack.notification_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaBootstrapProperties {
    private String bootstrapServers;
}
