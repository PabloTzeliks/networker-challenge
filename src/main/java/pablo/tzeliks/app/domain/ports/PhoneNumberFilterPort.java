package pablo.tzeliks.app.domain.ports;

public interface PhoneNumberFilterPort {

    String minimize(String phoneNumber);

    String maximize(String phoneNumber);
}
