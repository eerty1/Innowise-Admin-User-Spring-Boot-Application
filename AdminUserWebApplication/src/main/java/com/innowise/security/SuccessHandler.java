package com.innowise.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.innowise.model.Role.ADMIN;
import static com.innowise.model.Role.USER;


@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(USER.name())) {
                redirectStrategy.sendRedirect(request, response, "/userpool");
                break;
            } else if (grantedAuthority.getAuthority().equals(ADMIN.name())) {
                redirectStrategy.sendRedirect(request, response, "/userpool");
                break;
            }

            throw new IllegalStateException();
        }
    }
}
