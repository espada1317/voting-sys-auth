package voting.sys.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.sys.auth.domain.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> getUsersById(Long id);

    Optional<Users> getUsersByIdnp(String idnp);

}