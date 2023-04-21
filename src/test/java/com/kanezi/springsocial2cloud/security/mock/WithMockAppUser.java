package com.kanezi.springsocial2cloud.security.mock;

import com.kanezi.springsocial2cloud.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockAppUser.AppUserSecurityContextFactory.class)
public @interface WithMockAppUser {
    String username() default "test";

    String email() default "test@test.com";

    String password() default "test";

    String[] authorities() default {"test"};

    /**
     * factory class that creates and sets up authentication context for mocked app user
     * used in testing methods to mock extended properties of AppUser (vs UserDetails)
     */
    class AppUserSecurityContextFactory implements WithSecurityContextFactory<WithMockAppUser> {
        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        public SecurityContext createSecurityContext(WithMockAppUser mockAppUser) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            List<SimpleGrantedAuthority> mockAuthorities = Arrays
                    .stream(mockAppUser.authorities())
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            String mockPassword = passwordEncoder.encode(mockAppUser.password());

            AppUser appUser = AppUser
                    .builder()
                    .username(mockAppUser.username())
                    .email(mockAppUser.email())
                    .password(mockPassword)
                    .authorities(mockAuthorities)
                    .build();

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    appUser,
                    mockPassword,
                    mockAuthorities);

            context.setAuthentication(auth);

            return context;
        }
    }

}
