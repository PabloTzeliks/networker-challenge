package pablo.tzeliks.app.application.user.usecase;

import pablo.tzeliks.app.application.user.dto.auth.RegisterRequest;
import pablo.tzeliks.app.domain.contact.exception.AuthenticationException;
import pablo.tzeliks.app.domain.user.model.User;
import pablo.tzeliks.app.domain.user.ports.PasswordEncoderPort;
import pablo.tzeliks.app.domain.user.ports.UserRepositoryPort;

public class CreateUserUseCase {

    private final UserRepositoryPort repositoryPort;
    private final PasswordEncoderPort encoderPort;

    public CreateUserUseCase(UserRepositoryPort repositoryPort,
                        PasswordEncoderPort encoderPort) {
        this.repositoryPort = repositoryPort;
        this.encoderPort = encoderPort;
    }

    public void execute(RegisterRequest request) {

        var user = repositoryPort.findByUsername(request.username()).
                orElseThrow(() -> new AuthenticationException("Usuário já existe."));

        var encodedPassword = encoderPort.encode(request.password());

        User newUser = new User(request.username(), request.password());

        repositoryPort.save(newUser);
    }
}
