package pablo.tzeliks.app.application.contact.mapper;

import org.springframework.stereotype.Component;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.CreateContactRequest;
import pablo.tzeliks.app.domain.contact.model.Contact;

import java.util.UUID;

@Component
public class ContactDtoMapper {

    public ContactResponse toDto(Contact entity) {

        return new ContactResponse(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public Contact toEntity(CreateContactRequest request, UUID ownerId) {

        return new Contact(
                ownerId,
                request.name(),
                request.phoneNumber()
        );
    }

    public Contact toEntity(CreateContactRequest request, String validPhoneNumber, UUID ownerId) {

        return new Contact(
                ownerId,
                request.name(),
                validPhoneNumber
        );
    }
}
