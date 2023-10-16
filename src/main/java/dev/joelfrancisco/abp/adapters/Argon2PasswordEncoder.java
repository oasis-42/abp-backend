package dev.joelfrancisco.abp.adapters;

import dev.joelfrancisco.abp.ports.PasswordEncoder;
import dev.joelfrancisco.abp.valueObjects.UserPassword;
import org.springframework.stereotype.Service;

@Service
public class Argon2PasswordEncoder implements PasswordEncoder {
    org.springframework.security.crypto.argon2.Argon2PasswordEncoder encoder =
            new org.springframework.security.crypto.argon2.Argon2PasswordEncoder(16, 32, 1, 60000, 10);

    @Override
    public String encode(UserPassword password) {
        return encoder.encode(password.getValue());
    }

    @Override
    public boolean matches(UserPassword password, String encoded) {
        return encoder.matches(password.getValue(), encoded);
    }
}
