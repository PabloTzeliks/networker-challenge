package pablo.tzeliks.app.domain.exception.generics;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
