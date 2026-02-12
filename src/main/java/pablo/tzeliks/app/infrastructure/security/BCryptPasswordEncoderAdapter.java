package pablo.tzeliks.app.infrastructure.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import pablo.tzeliks.app.domain.user.ports.PasswordEncoderPort;

public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final PasswordEncoder encoder;

    public BCryptPasswordEncoderAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(String rawPassword) {

        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {

        return encoder.matches(rawPassword, hashedPassword);
    }
}
