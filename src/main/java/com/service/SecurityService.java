package com.service;

import com.model.Credentials;
import com.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
/*@PropertySource(value = "classpath:application.properties")*/
public class SecurityService {

    @Value("${spring.cookie.expiration.date=60000}")
    private int cookieExpiration;
    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public String getPasswordEncode(String password) {
        return DigestUtils.md5Hex(password);
    }

    public void signUp(Credentials credentials) {
        String sole = getUUID();
        String passwordEncoded = getPasswordEncode(credentials.getPassword()) + sole;
        User user = new User();
        user.setName(credentials.getLogin());
        user.setPassword(passwordEncoded);
        user.setSole(sole);
        userService.create(user);
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    public boolean validateUser(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("user-token")) {
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<Cookie> refreshCookies(User user, String password) {
        String passwordEncode = getPasswordEncode(password + user.getSole());
        if (Objects.equals(passwordEncode, user.getPassword())) {
            Cookie cookie = new Cookie("user-token", user.getSole());
            cookie.setMaxAge(cookieExpiration);
            return Optional.of(cookie);
        }
        return Optional.empty();
    }
}
