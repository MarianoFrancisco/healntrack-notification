package com.sa.healntrack.notification_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerProperties {
    private String groupId;
    private boolean enableAutoCommit = false;
    private String isolationLevel = "read_committed";
    private String autoOffsetReset = "latest";
    private Integer maxPollIntervalMs = 600000;
    private Integer sessionTimeoutMs = 15000;
    private Integer heartbeatIntervalMs = 5000;
    private Integer maxPollRecords = 500;
}
