package pablo.tzeliks.app.domain.exception;

import pablo.tzeliks.app.domain.exception.generics.BusinessRuleException;

public class ResourceNotFoundException extends BusinessRuleException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
