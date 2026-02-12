package pablo.tzeliks.app.application.user.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Username é obrigatório") String username,
        @NotBlank(message = "Password é obrigatório") String password
) { }
