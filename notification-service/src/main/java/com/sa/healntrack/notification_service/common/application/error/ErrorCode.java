package com.sa.healntrack.notification_service.common.application.error;

public enum ErrorCode {
    INVALID_REQUEST_ID,
    INVALID_RECIPIENT_EMAIL,
    EMPTY_SUBJECT,
    EMPTY_BODY,

    SMTP_AUTH_FAILED,
    SMTP_PERMANENT_FAILURE,
    SMTP_TRANSIENT_FAILURE,

    DESERIALIZATION_ERROR,
    UNKNOWN_ERROR
}
