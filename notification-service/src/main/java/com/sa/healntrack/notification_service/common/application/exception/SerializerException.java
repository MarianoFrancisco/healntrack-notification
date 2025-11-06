package com.sa.healntrack.notification_service.common.application.exception;

public class SerializerException extends RuntimeException {
    public SerializerException(String error) {
        super("Error al serializar la clase: " + error);
    }
}
