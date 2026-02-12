package pablo.tzeliks.app.application.user.usecase;

import org.springframework.stereotype.Service;
import pablo.tzeliks.app.application.user.dto.auth.RegisterRequest;
import pablo.tzeliks.app.domain.contact.exception.AuthenticationException;
import pablo.tzeliks.app.domain.user.model.User;
import pablo.tzeliks.app.domain.user.ports.PasswordEncoderPort;
import pablo.tzeliks.app.domain.user.ports.UserRepositoryPort;

@Service
public class CreateUserUseCase {

    private final UserRepositoryPort repositoryPort;
    private final PasswordEncoderPort encoderPort;

    public CreateUserUseCase(UserRepositoryPort repositoryPort,
                        PasswordEncoderPort encoderPort) {
        this.repositoryPort = repositoryPort;
        this.encoderPort = encoderPort;
    }

    public void execute(RegisterRequest request) {

        if (repositoryPort.findByUsername(request.username()).isPresent()) {

            throw new AuthenticationException("Usuário já existe.");
        }

        var encodedPassword = encoderPort.encode(request.password());

        User newUser = new User(request.username(), encodedPassword);

        repositoryPort.save(newUser);
    }
}
