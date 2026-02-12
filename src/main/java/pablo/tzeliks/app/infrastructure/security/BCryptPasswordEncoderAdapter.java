package pablo.tzeliks.app.infrastructure.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pablo.tzeliks.app.domain.user.ports.PasswordEncoderPort;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final PasswordEncoder encoder;

    public BCryptPasswordEncoderAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(String rawPassword) {

        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String hashedPassword) {

        return encoder.matches(rawPassword, hashedPassword);
    }
}
