package pablo.tzeliks.app.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.CreateContactRequest;
import pablo.tzeliks.app.application.contact.usecase.AddContactUseCase;
import pablo.tzeliks.app.domain.user.model.User;

@RestController
@RequestMapping("contacts")
public class ContactController {

    private AddContactUseCase addContact;

    public ContactController(AddContactUseCase addContact) {
        this.addContact = addContact;
    }

    @PostMapping
    public ResponseEntity<ContactResponse> add(@Valid @RequestBody CreateContactRequest request,
                                               @AuthenticationPrincipal User user,
                                               UriComponentsBuilder uriBuilder) {

        ContactResponse response = addContact.execute(request, user.getId());

        var uri = uriBuilder.path("/contacts/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
