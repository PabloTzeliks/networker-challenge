package pablo.tzeliks.app.domain.contact.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Contact {

    private final UUID id;
    private String name;
    private String phoneNumber;
    private final LocalDateTime createdAt;

    public Contact(UUID id, String name, String phoneNumber, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
