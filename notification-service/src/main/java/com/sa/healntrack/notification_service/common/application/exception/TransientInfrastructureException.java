package com.sa.healntrack.notification_service.common.application.exception;

import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import lombok.Getter;

@Getter
public class TransientInfrastructureException extends RuntimeException {
    private final ErrorCode code;

    public TransientInfrastructureException(ErrorCode code) {
        this.code = code;
    }

    public TransientInfrastructureException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public TransientInfrastructureException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
