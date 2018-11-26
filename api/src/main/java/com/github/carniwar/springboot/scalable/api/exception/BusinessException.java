package com.github.carniwar.springboot.scalable.api.exception;

import com.github.carniwar.springboot.scalable.api.enumeration.MessageCode;
import lombok.Getter;

/**
 * System business rule violation exception. Contains a message code and parameters that can be used with i18n message properties.
 *
 * This exception must be used to validate business rules, rollback transaction (in case of failure) and inform upper layers of the problem.
 *
 * TODO Create message bundles and interpolate messages for internationalization
 */
@Getter
public class BusinessException extends RuntimeException {

    private MessageCode messageCode;
    private String[] parameters;

    public BusinessException(MessageCode messageCode, String... parameters) {
        this(null, messageCode, parameters);
    }

    public BusinessException(Throwable cause, MessageCode messageCode, String... parameters) {
        super(messageCode.name(), cause);
        this.messageCode = messageCode;
        this.parameters = parameters;
    }

}
