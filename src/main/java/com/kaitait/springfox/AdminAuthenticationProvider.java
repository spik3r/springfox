package com.kaitait.springfox;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    private static final String ROLE_USER = "ROLE_USER";

    private final SecurityProperties securityProperties;

    @Inject
    public AdminAuthenticationProvider(final SecurityProperties securityProperties) {
        this.securityProperties = Objects.requireNonNull(securityProperties, "securityProperties must not be null");
    }

    @Override
    public Authentication authenticate(final Authentication authentication) {
        if (isValidUser(authentication.getName(), authentication.getCredentials().toString())) {
            final List<GrantedAuthority> grantedPermissions = new ArrayList<>();
            grantedPermissions.addAll(AuthorityUtils.createAuthorityList(ROLE_USER));
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), grantedPermissions);
        }
        throw new BadCredentialsException("Customer " + authentication.getName() + " has failed to authenticate.");

    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return true;
    }

    private boolean isValidUser(final String username, final String password) {
        // get username & password form application.yml
        final SecurityProperties.User user = securityProperties.getUser();
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) &&
                user.getName().equals(username) &&
                user.getPassword().equals(password);
    }
}
