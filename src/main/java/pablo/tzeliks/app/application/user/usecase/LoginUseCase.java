package pablo.tzeliks.app.application.user.usecase;

import org.springframework.stereotype.Service;
import pablo.tzeliks.app.application.user.dto.auth.LoginRequest;
import pablo.tzeliks.app.application.user.dto.auth.LoginResponse;
import pablo.tzeliks.app.domain.exception.generics.AuthenticationException;
import pablo.tzeliks.app.domain.user.ports.PasswordEncoderPort;
import pablo.tzeliks.app.domain.user.ports.TokenLogicPort;
import pablo.tzeliks.app.domain.user.ports.UserRepositoryPort;

@Service
public class LoginUseCase {

    private final UserRepositoryPort repositoryPort;
    private final PasswordEncoderPort encoderPort;
    private final TokenLogicPort tokenPort;

    public LoginUseCase(UserRepositoryPort repositoryPort,
                        PasswordEncoderPort encoderPort,
                        TokenLogicPort tokenPort) {
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
