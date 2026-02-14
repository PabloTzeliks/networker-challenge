package pablo.tzeliks.app.application.contact.usecase;

import org.springframework.stereotype.Service;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.CreateContactRequest;
import pablo.tzeliks.app.application.contact.mapper.ContactDtoMapper;
import pablo.tzeliks.app.domain.exception.ResourceAlreadyExistsException;
import pablo.tzeliks.app.domain.contact.model.Contact;
import pablo.tzeliks.app.domain.contact.ports.ContactRepositoryPort;
import pablo.tzeliks.app.domain.contact.ports.PhoneNumberLogicPort;

import java.util.UUID;

@Service
public class AddContactUseCase {

    private final ContactDtoMapper mapper;
    private final ContactRepositoryPort repositoryPort;
    private final PhoneNumberLogicPort phoneLogicPort;

    public AddContactUseCase(ContactDtoMapper mapper,
                             ContactRepositoryPort repositoryPort,
                             PhoneNumberLogicPort phoneLogicPort) {
        this.mapper = mapper;
        this.repositoryPort = repositoryPort;
        this.phoneLogicPort = phoneLogicPort;
    }

    public ContactResponse execute(CreateContactRequest request, UUID ownerId) {

        String validPhoneNumber = phoneLogicPort.minimize(request.phoneNumber());

        if (repositoryPort.existsByOwnerIdAndPhoneNumber(ownerId, validPhoneNumber)) {
            throw new ResourceAlreadyExistsException("Número de Telefone já cadastrado.");
        }

        Contact newContact = mapper.toEntity(request, validPhoneNumber, ownerId);

        return mapper.toDto(repositoryPort.save(newContact));
    }
}
