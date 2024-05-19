package voting.sys.auth.rest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import voting.sys.auth.domain.entity.UserRoles;
import voting.sys.auth.domain.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDetails implements UserDetails {

    private final String idnp;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UsersDetails(Users userInfo) {
        this.idnp = userInfo.getIdnp();
        this.password = userInfo.getPassword();
        this.authorities = userInfo.getUserRoles().stream()
                .map(UserRoles::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return idnp;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
