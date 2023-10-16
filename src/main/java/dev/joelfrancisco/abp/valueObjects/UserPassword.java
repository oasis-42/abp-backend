package dev.joelfrancisco.abp.valueObjects;

import dev.joelfrancisco.abp.exceptions.InvalidPasswordException;
import dev.joelfrancisco.abp.ports.PasswordEncoder;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

public class UserPassword {
    private final PasswordEncoder passwordEncoder;
    private String value;

    private String hash() {
        return this.passwordEncoder.encode(this);
    }

    public UserPassword(PasswordEncoder passwordEncoder, String value) throws InvalidPasswordException {
        this.passwordEncoder = passwordEncoder;
        setValue(value);
    }

    private void validateLength(String value) throws InvalidPasswordException {
        if (value.length() < 15) {
            throw new InvalidPasswordException("Password should have at least 15 characters");
        }
    }

    private void validateSymbols(String value) {
        // TODO regex to verify if contain at least one symbol
    }

    private void validateCapitalized(String value) throws InvalidPasswordException {
        if (value.chars().noneMatch(Character::isUpperCase)) {
            throw new InvalidPasswordException("Password should have at least one capitalized letter");
        }
    }

    private void validateNumber(String value) throws InvalidPasswordException {
        if (value.chars().noneMatch(Character::isDigit)) {
            throw new InvalidPasswordException("Password should have at least one number");
        }
    }

    private void setValue(String value) throws InvalidPasswordException {
        validateLength(value);
        validateSymbols(value);
        validateCapitalized(value);
        validateNumber(value);
        this.value = hash();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPassword that = (UserPassword) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Converter
    @Component
    public static class UserPasswordConverter implements AttributeConverter<UserPassword, String> {
        private final PasswordEncoder passwordEncoder;

        public UserPasswordConverter(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public String convertToDatabaseColumn(UserPassword userPassword) {
            return userPassword.getValue();
        }

        @Override
        public UserPassword convertToEntityAttribute(String s) {
            try {
                return new UserPassword(passwordEncoder, s);
            } catch (InvalidPasswordException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
