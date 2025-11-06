package com.sa.healntrack.notification_service.common.application.exception;

public class MessageException extends RuntimeException {
    public MessageException() {
        super("Error al construir el mensaje");
    }
}
