package pablo.tzeliks.app.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import pablo.tzeliks.app.domain.entity.Contact;
import pablo.tzeliks.app.domain.ports.ContactRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContactRepositoryAdapter implements ContactRepositoryPort {

    JpaContactRepository repository;

    public ContactRepositoryAdapter(JpaContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Contact> findByNumber(String number) {
        return repository.findContactByNumber(number);
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
