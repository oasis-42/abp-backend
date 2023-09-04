package dev.joelfrancisco.abp.entities;

import dev.joelfrancisco.abp.valueObjects.EntityId;

import java.time.LocalDate;
import java.util.Objects;

public abstract class BaseEntity {
    private EntityId id;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public BaseEntity(EntityId id, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseEntity() {
        this.id = EntityId.newId();
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
