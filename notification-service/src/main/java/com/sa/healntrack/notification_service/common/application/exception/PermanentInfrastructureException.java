package com.sa.healntrack.notification_service.common.application.exception;

import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import lombok.Getter;

@Getter
public class PermanentInfrastructureException extends RuntimeException {
    private final ErrorCode code;

    public PermanentInfrastructureException(ErrorCode code) {
        this.code = code;
    }

    public PermanentInfrastructureException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
