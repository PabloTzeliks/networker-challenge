package pablo.tzeliks.app.domain.exception.generics;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
