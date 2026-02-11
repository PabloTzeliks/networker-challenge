package pablo.tzeliks.app.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pablo.tzeliks.app.application.dto.ContactResponse;
import pablo.tzeliks.app.application.dto.CreateContactRequest;
import pablo.tzeliks.app.application.usecase.AddContactUseCase;

@RestController
@RequestMapping("api")
public class ContactController {

    private AddContactUseCase addContact;

    public ContactController(AddContactUseCase addContact) {
        this.addContact = addContact;
    }

    @PostMapping("/")
    public ResponseEntity<ContactResponse> add(@Valid @RequestBody CreateContactRequest request) {

        ContactResponse response = addContact.execute(request);

        return ResponseEntity.ok(response);
    }
}
