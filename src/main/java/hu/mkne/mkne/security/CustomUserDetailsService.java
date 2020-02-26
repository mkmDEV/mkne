package hu.mkne.mkne.security;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private MemberRepository users;

    public CustomUserDetailsService(MemberRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member user = users.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email: %s not found", email)));

        return new User(user.getEmail(), user.getPassword(), user.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }
}
