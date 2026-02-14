package pablo.tzeliks.app.application.contact.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateContactRequest(

        @JsonProperty("name")
        @NotNull(message = "Nome é obrigatório")
        String name
) { }
