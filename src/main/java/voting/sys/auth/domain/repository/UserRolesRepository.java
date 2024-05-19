package voting.sys.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.sys.auth.domain.entity.UserRoles;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    UserRoles getUserRolesByName(String name);

}