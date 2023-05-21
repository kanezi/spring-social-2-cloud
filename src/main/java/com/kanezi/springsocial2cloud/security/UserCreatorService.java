package com.kanezi.springsocial2cloud.security;

import jakarta.annotation.PostConstruct;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Value
@Slf4j
public class UserCreatorService {

    AppUserService appUserService;
    PasswordEncoder passwordEncoder;

    AdminConfig adminConfig;

    @PostConstruct
    private void createHardcodedUsers() {
        var bob = AppUser.builder()
                .username("bob")
                .provider(LoginProvider.APP)
                .password(passwordEncoder.encode("1234"))
                .authorities(List.of(new SimpleGrantedAuthority("read")))
                .build();

        var bil = AppUser.builder()
                .username("bil")
                .provider(LoginProvider.APP)
                .password(passwordEncoder.encode("321"))
                .authorities(List.of(new SimpleGrantedAuthority("read")))
                .build();

        var admin = AppUser.builder()
                .username("admin")
                .provider(LoginProvider.APP)
                .password(passwordEncoder.encode(adminConfig.adminPassword))
                .authorities(List.of(new SimpleGrantedAuthority("manage")))
                .build();

        appUserService.createUser(bob);
        appUserService.createUser(bil);
        appUserService.createUser(admin);

    }

}
