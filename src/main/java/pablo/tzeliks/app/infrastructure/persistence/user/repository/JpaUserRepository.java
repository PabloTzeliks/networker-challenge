package pablo.tzeliks.app.infrastructure.persistence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pablo.tzeliks.app.domain.user.domain.User;
import pablo.tzeliks.app.infrastructure.persistence.user.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
