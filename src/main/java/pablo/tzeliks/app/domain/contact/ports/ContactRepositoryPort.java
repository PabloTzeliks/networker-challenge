package pablo.tzeliks.app.domain.contact.ports;

import pablo.tzeliks.app.domain.contact.model.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepositoryPort {

    Contact save(Contact contact);

    Optional<Contact> findById(UUID id);

    Optional<Contact> findByPhoneNumber(String number);

    List<Contact> findAll();

    void deleteById(UUID id);
}
