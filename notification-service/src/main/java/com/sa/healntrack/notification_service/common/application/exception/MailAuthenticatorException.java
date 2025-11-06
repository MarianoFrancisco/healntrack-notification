package com.sa.healntrack.notification_service.common.application.exception;

public class MailAuthenticatorException extends RuntimeException {
    public MailAuthenticatorException() {
        super("Error al autenticar el mail");
    }
}
