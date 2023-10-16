package dev.joelfrancisco.abp.ports;

import dev.joelfrancisco.abp.valueObjects.UserPassword;

public interface PasswordEncoder {
    String encode(UserPassword password);
    boolean matches(UserPassword password, String encoded);
}
