package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import com.sa.healntrack.notification_service.common.application.exception.DomainException;
import com.sa.healntrack.notification_service.common.application.exception.TransientInfrastructureException;
import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotification;
import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotificationCommand;
import com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events.NotificationRequestedEvent;
import com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.mappers.NotificationRequestedEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationKafkaConsumer {

    private final SendNotification useCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@notificationTopicProperties.requested}",
            groupId = "#{@kafkaConsumerProperties.groupId}",
            containerFactory = "notificationKafkaListenerContainerFactory"
    )
    public void onMessage(ConsumerRecord<String, String> record) {
        NotificationRequestedEvent event = null;

        try {
            event = objectMapper.readValue(record.value(), NotificationRequestedEvent.class);

            SendNotificationCommand cmd = NotificationRequestedEventMapper.toCommand(event);
            useCase.handle(cmd);
        } catch (JsonProcessingException | IllegalArgumentException ex) {
            log.error("DESERIALIZATION_ERROR offset={}", record.offset(), ex);
            return;
        } catch (DomainException ex) {
            String reqId = (event != null ? event.getRequestId() : "unknown");
            log.warn("DOMAIN_ERROR code={} requestId={} offset={}",
                    ex.getCode().name(), reqId, record.offset());
        } catch (Exception ex) {
            String reqId = (event != null ? event.getRequestId() : "unknown");
            log.error("UNKNOWN_ERROR requestId={} offset={} (will retry)",
                    reqId, record.offset(), ex);
            throw new TransientInfrastructureException(ErrorCode.UNKNOWN_ERROR, ex);
        }
    }
}
