package pablo.tzeliks.app.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.CreateContactRequest;
import pablo.tzeliks.app.application.contact.dto.UpdateContactRequest;
import pablo.tzeliks.app.application.contact.usecase.AddContactUseCase;
import pablo.tzeliks.app.application.contact.usecase.SearchContactsUseCase;
import pablo.tzeliks.app.application.contact.usecase.UpdateContactUseCase;
import pablo.tzeliks.app.infrastructure.security.CustomUserDetails;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final AddContactUseCase addContact;
    private final SearchContactsUseCase searchUserContacts;
    private final UpdateContactUseCase updateContact;

    public ContactController(AddContactUseCase addContact, SearchContactsUseCase searchUserContacts, UpdateContactUseCase updateContact) {
        this.addContact = addContact;
        this.searchUserContacts = searchUserContacts;
        this.updateContact = updateContact;
    }

    @PostMapping
    public ResponseEntity<ContactResponse> add(@Valid @RequestBody CreateContactRequest request,
                                               @AuthenticationPrincipal CustomUserDetails userDetails,
                                               UriComponentsBuilder uriBuilder) {

        ContactResponse response = addContact.execute(request, userDetails.getDomainUser().getId());

        var uri = uriBuilder.path("/contacts/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok().body(searchUserContacts.execute(userDetails.getDomainUser().getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponse> update(@RequestBody UpdateContactRequest request, @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable UUID id) {

        return ResponseEntity.ok().body(updateContact.execute(request, id, userDetails.getDomainUser().getId()));
    }
}
