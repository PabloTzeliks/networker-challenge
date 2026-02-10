package pablo.tzeliks.app.application.mapper;

import pablo.tzeliks.app.application.dto.ContactResponse;
import pablo.tzeliks.app.application.dto.CreateContactRequest;
import pablo.tzeliks.app.domain.entity.Contact;

import java.time.LocalDateTime;
import java.util.UUID;

public class ContactDtoMapper {

    public ContactResponse toDto(Contact entity) {

        return new ContactResponse(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getCreatedAt()
        );
    }

    public Contact toEntity(CreateContactRequest request, UUID id) {

        return new Contact(
                id,
                request.name(),
                request.phoneNumber(),
                LocalDateTime.now()
        );
    }
}
