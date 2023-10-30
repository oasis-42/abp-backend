package dev.joelfrancisco.abp.exceptions;

import dev.joelfrancisco.abp.entities.BaseEntity;

public class NotFoundException extends RuntimeException {
    public NotFoundException(BaseEntity entity) {
        super("entity=" + entity.getClass().getName() + " of id=" + entity.getId() + " not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
