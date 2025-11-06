package com.sa.healntrack.notification_service.common.application.exception;

public class DeserializerException extends RuntimeException {
    public DeserializerException(String error) {
        super("Error al deserializar la clase: " + error);
    }
}
