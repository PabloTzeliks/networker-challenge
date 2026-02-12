package pablo.tzeliks.app.application.user.usecase;

import pablo.tzeliks.app.application.user.dto.auth.LoginRequest;
import pablo.tzeliks.app.application.user.dto.auth.LoginResponse;
import pablo.tzeliks.app.domain.contact.exception.AuthenticationException;
import pablo.tzeliks.app.domain.user.ports.PasswordEncoderPort;
import pablo.tzeliks.app.domain.user.ports.TokenServicePort;
import pablo.tzeliks.app.domain.user.ports.UserRepositoryPort;

public class LoginUseCase {

    private final UserRepositoryPort repositoryPort;
    private final PasswordEncoderPort encoderPort;
    private final TokenServicePort tokenPort;

    public LoginUseCase(UserRepositoryPort repositoryPort,
                        PasswordEncoderPort encoderPort,
                        TokenServicePort tokenPort) {
        this.repositoryPort = repositoryPort;
        this.encoderPort = encoderPort;
        this.tokenPort = tokenPort;
    }

    public LoginResponse execute(LoginRequest request) {

        var user = repositoryPort.findByUsername(request.username()).
                orElseThrow(() -> new AuthenticationException("Usuário não encontrado."));

        if (!encoderPort.matches(request.password(), user.getPassword())) {

            throw new AuthenticationException("Credenciais Inválidas.");
        }

        var newToken = tokenPort.generate(user);

        return new LoginResponse(newToken);
    }
}
