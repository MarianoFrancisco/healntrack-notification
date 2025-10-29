package com.sa.healntrack.notification_service.common.application.exception;

import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainException extends RuntimeException {
    private final ErrorCode code;
}
