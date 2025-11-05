package com.sa.healntrack.notification_service.notification.application.port.in.send_notification;

import com.sa.healntrack.notification_service.common.application.exception.DomainException;
import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import com.sa.healntrack.notification_service.notification.domain.Recipient;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
@EqualsAndHashCode
public final class SendNotificationCommand {

    private final String requestId;
    private final Recipient recipient;
    private final String subject;
    private final String bodyHtml;

    public SendNotificationCommand(String requestId, Recipient recipient, String subject, String bodyHtml) {
        if (!StringUtils.isNotBlank(requestId)) {
            throw new DomainException(ErrorCode.INVALID_REQUEST_ID);
        }
        if (recipient == null) {
            throw new DomainException(ErrorCode.INVALID_RECIPIENT_EMAIL);
        }
        if (!StringUtils.isNotBlank(subject)) {
            throw new DomainException(ErrorCode.EMPTY_SUBJECT);
        }
        if (!StringUtils.isNotBlank(bodyHtml)) {
            throw new DomainException(ErrorCode.EMPTY_BODY);
        }

        this.requestId = requestId;
        this.recipient = recipient;
        this.subject = subject;
        this.bodyHtml = bodyHtml;
    }
}
