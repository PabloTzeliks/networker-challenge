package pablo.tzeliks.app.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pablo.tzeliks.app.domain.exception.generics.UserNotFoundException;
import pablo.tzeliks.app.domain.user.model.User;
import pablo.tzeliks.app.domain.user.ports.UserRepositoryPort;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenLogicAdapter tokenService;
    private final UserRepositoryPort repositoryPort;

    public SecurityFilter(TokenLogicAdapter tokenService, UserRepositoryPort repositoryPort) {
        this.tokenService = tokenService;
        this.repositoryPort = repositoryPort;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {

            var login = tokenService.validate(token);

            if (!login.isEmpty()) {

                User user = repositoryPort.findByUsername(login)
                        .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

                CustomUserDetails userDetails = new CustomUserDetails(user);

                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
