package pablo.tzeliks.app.infrastructure.filter;

import org.springframework.stereotype.Component;
import pablo.tzeliks.app.domain.contact.ports.PhoneNumberFilterPort;
import pablo.tzeliks.app.infrastructure.exception.FilterException;

@Component
public class PhoneNumberFilterAdapter implements PhoneNumberFilterPort {

    public String minimize(String telefoneBruto) {
        if (telefoneBruto == null) {
            throw new FilterException("Telefone não pode ser nulo");
        }

        String apenasNumeros = telefoneBruto.replaceAll("\\D", "");

        if (apenasNumeros.length() != 13) {
            throw new FilterException(
                    "Telefone inválido. Esperado 13 dígitos numéricos (País+DDD+9+Número). Recebido: " + apenasNumeros.length()
            );
        }

        return apenasNumeros;
    }

    public String maximize(String telefoneLimpo) {

        if (telefoneLimpo == null || telefoneLimpo.length() != 13) {
            return telefoneLimpo;
        }

        return telefoneLimpo.replaceAll(
                "(\\d{2})(\\d{2})(\\d{1})(\\d{4})(\\d{4})",
                "+$1 ($2) $3 $4-$5"
        );
    }
}
