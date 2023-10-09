package dev.joelfrancisco.abp.valueObjects;

import dev.joelfrancisco.abp.exceptions.InvalidPasswordException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

public class UserPassword {
    private String value;

    private UserPassword() {
    }

    public static UserPassword of(String hash) {
        UserPassword userPassword = new UserPassword();
        userPassword.setValue(hash);
        return userPassword;
    }

    private String hash(String password) {
        // TODO hash it with Argon2
        return password;
    }

    public UserPassword(String value) throws InvalidPasswordException {
        validateLength(value);
        validateSymbols(value);
        validateCapitalized(value);
        validateNumber(value);
        this.value = hash(value);
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
        boolean found = false;

        for (char c : value.toCharArray()) {
            if (Character.isUpperCase(c)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new InvalidPasswordException("Password should have at least one capitalized letter");
        }
    }

    private void validateNumber(String value) throws InvalidPasswordException {
        boolean found = false;

        for (char c : value.toCharArray()) {
            if (Character.isDigit(c)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new InvalidPasswordException("Password should have at least one number");
        }
    }

    private void setValue(String value) {
       this.value = value;
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
    public static class UserPasswordConverter implements AttributeConverter<UserPassword, String> {

        @Override
        public String convertToDatabaseColumn(UserPassword userPassword) {
            return userPassword.getValue();
        }

        @Override
        public UserPassword convertToEntityAttribute(String s) {
            try {
                return new UserPassword(s);
            } catch (InvalidPasswordException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
