package ba.imad.sis.services.user;

import ba.imad.sis.domain.User;
import ba.imad.sis.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
    }

    public User getCurrentUser(){
        return loadUserByUsername(getCurrentUserEmail());
    }

    public String getCurrentUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public boolean isAuthenticated() {
        return !getCurrentUserEmail().equals("anonymousUser");
    }
}
