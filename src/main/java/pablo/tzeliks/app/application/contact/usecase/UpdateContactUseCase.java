package pablo.tzeliks.app.application.contact.usecase;

import org.springframework.stereotype.Service;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.UpdateContactRequest;
import pablo.tzeliks.app.application.contact.mapper.ContactDtoMapper;
import pablo.tzeliks.app.domain.contact.ports.ContactRepositoryPort;
import pablo.tzeliks.app.domain.exception.ResourceNotFoundException;

import java.util.UUID;

@Service
public class UpdateContactUseCase {

    private final ContactDtoMapper mapper;
    private final ContactRepositoryPort repositoryPort;

    public UpdateContactUseCase(ContactDtoMapper mapper, ContactRepositoryPort repositoryPort) {
        this.mapper = mapper;
        this.repositoryPort = repositoryPort;
    }

    public ContactResponse execute(UpdateContactRequest request, UUID id, UUID ownerId) {

        var contact = repositoryPort.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado."));

        contact.updateName(request.name());

        return mapper.toDto(repositoryPort.save(contact));
    }
}
