package pablo.tzeliks.app.infrastructure.filter;

import pablo.tzeliks.app.domain.ports.PhoneNumberFilterPort;

public class PhoneNumberFilterAdapter implements PhoneNumberFilterPort {

    @Override
    public String minimize(String phoneNumber) {
        return "";
    }

    @Override
    public String maximize(String phoneNumber) {
        return "";
    }
}
