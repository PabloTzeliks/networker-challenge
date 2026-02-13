package pablo.tzeliks.app.application.contact.usecase;

import org.springframework.stereotype.Service;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.CreateContactRequest;
import pablo.tzeliks.app.application.contact.mapper.ContactDtoMapper;
import pablo.tzeliks.app.domain.contact.model.Contact;
import pablo.tzeliks.app.domain.contact.ports.ContactRepositoryPort;
import pablo.tzeliks.app.domain.contact.ports.PhoneNumberLogicPort;

import java.util.UUID;

@Service
public class AddContactUseCase {

    private final ContactDtoMapper mapper;
    private final ContactRepositoryPort repository;
    private final PhoneNumberLogicPort phoneLogicPort;

    public AddContactUseCase(ContactDtoMapper mapper, ContactRepositoryPort repository, PhoneNumberLogicPort phoneLogicPort) {
        this.mapper = mapper;
        this.repository = repository;
        this.phoneLogicPort = phoneLogicPort;
    }

    public ContactResponse execute(CreateContactRequest request, UUID ownerId) {

        Contact newContact = ;

    }
}
