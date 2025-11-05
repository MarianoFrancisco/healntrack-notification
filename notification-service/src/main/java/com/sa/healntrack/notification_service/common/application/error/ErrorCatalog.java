package com.sa.healntrack.notification_service.common.application.error;

import java.util.Map;

public final class ErrorCatalog {
    private static final Map<ErrorCode, String> MESSAGES = Map.ofEntries(
            Map.entry(ErrorCode.INVALID_REQUEST_ID, "El identificador de solicitud es obligatorio."),
            Map.entry(ErrorCode.INVALID_RECIPIENT_EMAIL, "El correo electrónico del destinatario no es válido."),
            Map.entry(ErrorCode.EMPTY_SUBJECT, "El asunto del mensaje no puede estar vacío."),
            Map.entry(ErrorCode.EMPTY_BODY, "El cuerpo del mensaje no puede estar vacío."),

            Map.entry(ErrorCode.SMTP_AUTH_FAILED, "Fallo de autenticación con el servidor de correo."),
            Map.entry(ErrorCode.SMTP_PERMANENT_FAILURE, "Fallo permanente al enviar el correo electrónico."),
            Map.entry(ErrorCode.SMTP_TRANSIENT_FAILURE, "Fallo temporal al enviar el correo electrónico. Intente nuevamente."),

            Map.entry(ErrorCode.DESERIALIZATION_ERROR, "El formato del evento de notificación es inválido."),
            Map.entry(ErrorCode.UNKNOWN_ERROR, "Ha ocurrido un error inesperado.")
    );

    private ErrorCatalog() {
    }

    public static String messageFor(ErrorCode code) {
        return MESSAGES.getOrDefault(code, "Ha ocurrido un error inesperado.");
    }
}
