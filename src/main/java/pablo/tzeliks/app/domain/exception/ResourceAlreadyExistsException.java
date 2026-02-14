package pablo.tzeliks.app.domain.exception;

import pablo.tzeliks.app.domain.exception.generics.BusinessRuleException;

public class ResourceAlreadyExistsException extends BusinessRuleException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
