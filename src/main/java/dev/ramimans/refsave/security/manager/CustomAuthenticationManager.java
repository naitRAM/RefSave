package dev.ramimans.refsave.security.manager;

import dev.ramimans.refsave.dao.UserDaoImpl;
import dev.ramimans.refsave.dto.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserDaoImpl userDao;

    public CustomAuthenticationManager(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken(authentication.getName(), null);

    }
}
