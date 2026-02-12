package pablo.tzeliks.app.domain.user.ports;

public interface PasswordEncoderPort {

    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
