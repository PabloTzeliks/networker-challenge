package pablo.tzeliks.app.domain.contact.model;

import pablo.tzeliks.app.domain.exception.generics.BusinessRuleException;

import java.time.Instant;
import java.util.UUID;

public class Contact {

    private final UUID id;
    private final UUID ownerId;
    private String name;
    private final String phoneNumber;
    private final Instant createdAt;
    private Instant updatedAt;

    public Contact(UUID ownerId, String name, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Contact(UUID id, UUID ownerId, String name, String phoneNumber, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) throw new BusinessRuleException("Nome inválido");
        this.name = newName;
        this.updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
