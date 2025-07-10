package io.github.Guimaraes131.strong_api.security;

import io.github.Guimaraes131.strong_api.model.User;
import io.github.Guimaraes131.strong_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserService service;

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String login = userDetails.getUsername();

        return service.getByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}
