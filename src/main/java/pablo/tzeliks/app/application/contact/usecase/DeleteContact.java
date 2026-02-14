package pablo.tzeliks.app.application.contact.usecase;

import pablo.tzeliks.app.application.contact.mapper.ContactDtoMapper;
import pablo.tzeliks.app.domain.contact.ports.ContactRepositoryPort;
import pablo.tzeliks.app.domain.exception.ResourceNotFoundException;

import java.util.UUID;

public class DeleteContact {

    private final ContactDtoMapper mapper;
    private final ContactRepositoryPort repositoryPort;

    public DeleteContact(ContactDtoMapper mapper, ContactRepositoryPort repositoryPort) {
        this.mapper = mapper;
        this.repositoryPort = repositoryPort;
    }

    public void execute(UUID id, UUID ownerId) {

        var contact = repositoryPort.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado."));

        repositoryPort.delete(contact);
    }
}
