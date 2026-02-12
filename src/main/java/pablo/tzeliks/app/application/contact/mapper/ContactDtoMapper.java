package pablo.tzeliks.app.application.contact.mapper;

import org.springframework.stereotype.Component;
import pablo.tzeliks.app.application.contact.dto.ContactResponse;
import pablo.tzeliks.app.application.contact.dto.CreateContactRequest;
import pablo.tzeliks.app.domain.contact.model.Contact;
import pablo.tzeliks.app.domain.contact.ports.PhoneNumberFilterPort;

import java.time.LocalDateTime;

@Component
public class ContactDtoMapper {

//    private PhoneNumberFilterPort phoneNumberFilter;
//
//    public ContactDtoMapper(PhoneNumberFilterPort phoneNumberFilter) {
//        this.phoneNumberFilter = phoneNumberFilter;
//    }
//
//    public ContactResponse toDto(Contact entity) {
//
//        return new ContactResponse(
//                entity.getId(),
//                entity.getName(),
//                phoneNumberFilter.maximize(entity.getPhoneNumber()),
//                entity.getCreatedAt()
//        );
//    }
//
//    public Contact toEntity(CreateContactRequest request) {
//
//        return new Contact(
//                null,
//                request.name(),
//                phoneNumberFilter.minimize(request.phoneNumber()),
//                LocalDateTime.now()
//        );
//    }
}
