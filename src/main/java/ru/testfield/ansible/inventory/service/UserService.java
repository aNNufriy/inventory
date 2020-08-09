package ru.testfield.ansible.inventory.service;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserRole;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

@Service
public class UserService implements UserDetailsService {

    final LoginUserRepository loginUserRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    public UserService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        logger.info("loadUserByUsername username=" + username);

        final LoginUser user = loginUserRepository.findByLogin(username);

        if (user == null) {
            throw new BadCredentialsException("User " + username + " doesn't exist");
        }

        return new UserDetails() {

            private static final long serialVersionUID = 2059202961588104658L;

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.isEnabled();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isEnabled();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.isEnabled();
            }

            @Override
            public String getUsername() {
                return user.getLogin();
            }

            @Override
            public String getPassword() {
                return user.getPasswordBcryptHash();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
                if(user.getRoles()!=null) {
                    for (LoginUserRole role : user.getRoles()) {
                        auths.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                    }
                }
                return auths;
            }
        };
    }
}
