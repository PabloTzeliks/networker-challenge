package pablo.tzeliks.app.domain.exception.generics;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
