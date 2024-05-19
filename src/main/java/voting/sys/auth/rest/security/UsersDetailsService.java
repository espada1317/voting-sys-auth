package voting.sys.auth.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.repository.UsersRepository;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userInfo = usersRepository.getUsersByUsername(username);
        return userInfo.map(UsersDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found [" + username + "]!"));
    }
}
