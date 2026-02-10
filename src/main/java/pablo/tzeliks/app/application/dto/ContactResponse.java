package pablo.tzeliks.app.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContactResponse(

        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name,

        @JsonProperty("phone_number")
        String phoneNumber,

        @JsonProperty("created_at")
        LocalDateTime createdAt
) { }
