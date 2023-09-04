package dev.joelfrancisco.abp.valueObjects;

import java.util.UUID;

public record EntityId(UUID value) {
    public static EntityId newId() {
        return new EntityId(UUID.randomUUID());
    }
}
