package voting.sys.auth.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import voting.sys.auth.domain.entity.UserRoles;
import voting.sys.auth.domain.repository.UserRolesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;

    @Override
    public List<UserRoles> getAllUserRoles() {
        return userRolesRepository.findAll();
    }

    @Override
    public UserRoles getUserRoleByName(String name) {
        return userRolesRepository.getUserRolesByName(name);
    }
}
