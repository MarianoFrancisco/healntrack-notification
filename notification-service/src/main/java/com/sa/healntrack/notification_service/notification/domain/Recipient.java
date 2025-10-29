package com.sa.healntrack.notification_service.notification.domain;

import com.sa.healntrack.notification_service.common.application.exception.DomainException;
import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Getter
@ToString
@EqualsAndHashCode
public class Recipient {

    private static final Pattern EMAIL_RX = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final String address;
    private final String name;

    public Recipient(String address, String name) {
        if (!StringUtils.isNotBlank(address) || !EMAIL_RX.matcher(address).matches()) {
            throw new DomainException(ErrorCode.INVALID_RECIPIENT_EMAIL);
        }
        this.address = address;
        this.name = name;
    }
}
