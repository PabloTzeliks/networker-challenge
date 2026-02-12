package pablo.tzeliks.app.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pablo.tzeliks.app.application.contact.dto.auth.LoginRequest;
import pablo.tzeliks.app.application.contact.dto.auth.LoginResponse;
import pablo.tzeliks.app.application.contact.dto.auth.RegisterRequest;
import pablo.tzeliks.app.domain.user.ports.UserRepositoryPort;
import pablo.tzeliks.app.infrastructure.security.CustomUserDetails;
import pablo.tzeliks.app.infrastructure.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepositoryPort repositoryPort;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          UserRepositoryPort repositoryPort,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.repositoryPort = repositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = ((CustomUserDetails) auth.getPrincipal()).getDomainUser();

        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid RegisterRequest request)

}
