package dev.ramimans.refsave.security.manager;

import dev.ramimans.refsave.dao.UserDaoImpl;
import dev.ramimans.refsave.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public CustomAuthenticationManager(UserDaoImpl userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userDao.readUser(authentication.getName());
        if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), null, new ArrayList<>());
        }
        throw new BadCredentialsException("Password does not match our records");
    }
}
