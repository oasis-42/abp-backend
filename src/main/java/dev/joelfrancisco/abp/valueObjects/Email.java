package dev.joelfrancisco.abp.valueObjects;

import java.util.Objects;

public record Email(String value) {
    public Email(String value) {
        this.value = Objects.requireNonNull(value, "email should not be null");
        validateFormat(value);
    }

    private void validateFormat(String value) {
        // TODO regex to validate email
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
