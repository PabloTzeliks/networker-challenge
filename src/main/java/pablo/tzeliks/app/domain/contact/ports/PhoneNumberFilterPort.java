package pablo.tzeliks.app.domain.contact.ports;

public interface PhoneNumberFilterPort {

    String minimize(String phoneNumber);

    String maximize(String phoneNumber);
}
