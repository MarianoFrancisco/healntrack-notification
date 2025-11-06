package com.sa.healntrack.notification_service.common.application.exception;

public class MailSenderException extends RuntimeException {
    public MailSenderException() {
        super("Error al enviar el mail");
    }
}
