package dev.joelfrancisco.abp.exceptions;

import dev.joelfrancisco.abp.entities.BaseEntity;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(BaseEntity entity) {
        super("entity=" + entity.getClass().getName() + " of id=" + entity.getId() + " not found");
    }
}
