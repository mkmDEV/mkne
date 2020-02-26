package hu.mkne.mkne.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenService jwtTokenService;

    public SecurityConfig(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }
}
