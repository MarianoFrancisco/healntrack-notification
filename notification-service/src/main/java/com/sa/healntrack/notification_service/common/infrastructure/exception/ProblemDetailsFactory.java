package com.sa.healntrack.notification_service.common.infrastructure.exception;

import com.sa.healntrack.notification_service.common.application.error.ErrorCatalog;
import com.sa.healntrack.notification_service.common.application.error.ErrorCode;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

final class ProblemDetailsFactory {

    private ProblemDetailsFactory() {
    }

    static ProblemDetail of(HttpStatus status, String title, String category, ErrorCode code) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, ErrorCatalog.messageFor(code)); // español
        pd.setTitle(title);
        pd.setProperty("error_category", category);
        pd.setProperty("timestamp", Instant.now());
        pd.setProperty("code", code.name());
        return pd;
    }
}
