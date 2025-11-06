package com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.notification_service.common.application.exception.DeserializerException;
import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotification;
import com.sa.healntrack.notification_service.notification.application.port.in.send_notification.SendNotificationCommand;
import com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.events.NotificationRequestedMessage;
import com.sa.healntrack.notification_service.notification.infrastructure.adapter.in.kafka.mappers.NotificationRequestedEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationKafkaConsumer {

    private final SendNotification useCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@notificationTopicProperties.requested}",
            groupId = "#{@kafkaConsumerProperties.groupId}"
    )
    public void onMessage(ConsumerRecord<String, byte[]> record) {

        try {
            NotificationRequestedMessage event = objectMapper.readValue(record.value(), NotificationRequestedMessage.class);
            SendNotificationCommand cmd = NotificationRequestedEventMapper.toCommand(event);
            useCase.handle(cmd);
        } catch (IOException ex) {
            log.error("DESERIALIZATION_ERROR offset={}", record.offset(), ex);
            throw new DeserializerException("NotificationRequestedMessage");
        }
    }
}
