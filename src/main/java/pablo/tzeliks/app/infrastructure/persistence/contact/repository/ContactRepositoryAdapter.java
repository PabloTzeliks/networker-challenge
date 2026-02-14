package pablo.tzeliks.app.infrastructure.persistence.contact.repository;

import org.springframework.stereotype.Repository;
import pablo.tzeliks.app.domain.contact.model.Contact;
import pablo.tzeliks.app.domain.contact.ports.ContactRepositoryPort;
import pablo.tzeliks.app.infrastructure.persistence.contact.entity.ContactEntity;
import pablo.tzeliks.app.infrastructure.persistence.contact.mapper.ContactMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContactRepositoryAdapter implements ContactRepositoryPort {

    private final JpaContactRepository repository;
    private final ContactMapper mapper;

    public ContactRepositoryAdapter(JpaContactRepository repository, ContactMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Contact save(Contact contact) {

        ContactEntity entity = mapper.toEntity(contact);

        if (contact.getCreatedAt().equals(contact.getUpdatedAt())) {
            entity.markAsNew();
        }

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public List<Contact> findAllByOwnerId(UUID ownerId) {

        return repository.findAllByOwnerId(ownerId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Contact> findByIdAndOwnerId(UUID id, UUID ownerId) {

        return repository.findByIdAndOwnerId(id, ownerId)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(Contact contact) {

        repository.deleteById(contact.getId());
    }

    @Override
    public boolean existsByOwnerIdAndPhoneNumber(UUID ownerId, String phoneNumber) {

        return repository.existsByOwnerIdAndPhoneNumber(ownerId, phoneNumber);
    }
}
