package com.kanezi.springsocial2cloud.security;

import com.kanezi.springsocial2cloud.security.mock.WithMockAppUser;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
@ActiveProfiles("postgres")
@Transactional
class AppUserServiceIntegrationTest {

    @Autowired
    AppUserService appUserService;

    @Test
    void shouldCreateUser() {
        assertThat(appUserService.userExists("test")).isFalse();
        appUserService.createUser("test", "test");
        assertThat(appUserService.userExists("test")).isTrue();
    }

    @Test
    void shouldCreateUserWithUserDetails() {
        appUserService.createUser(User
                .builder()
                .username("test")
                .password("1234")
                .authorities(Collections.emptyList())
                .build());

        assertThat(appUserService.userExists("test")).isTrue();
    }

    @Test
    void shouldCreateUserWithAppUser() {
        appUserService.createUser(AppUser
                .builder()
                .username("test")
                .password("1234")
                .email("test@test.com")
                .authorities(List.of(new SimpleGrantedAuthority("read")))
                .build());

        assertThat(appUserService.userExists("test")).isTrue();

        assertThat(appUserService.loadUserByUsername("test"))
                .hasFieldOrPropertyWithValue("username", "test")
                .hasFieldOrPropertyWithValue("email", "test@test.com")
                .hasFieldOrPropertyWithValue("authorities", List.of(new SimpleGrantedAuthority("read")))
        ;
    }

    @Test
    @Sql("/db/fixture/dummy_user.sql")
    void shouldDeleteUser() {
        assertThat(appUserService.userExists("dummy")).isTrue();
        appUserService.deleteUser("dummy");
        assertThat(appUserService.userExists("dummy")).isFalse();
    }


    @Test
    void exceptionIsThrownForLoadingNonExistingUsers() {
        assertThatThrownBy(() -> appUserService.loadUserByUsername("NA"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User NA not found!");
    }

    @Test
    @WithMockAppUser(password = "1234")
    void userCanChangePasswordWhenOldPasswordIsCorrect() {
        assertThatCode(() -> appUserService.changePassword("1234", "6547"))
                .doesNotThrowAnyException();
    }

    @Test
    @WithMockAppUser(password = "1234")
    void passwordChangeThrowsExceptionForWrongOldPassword() {
        assertThatThrownBy(() -> appUserService.changePassword("1234567", "6547"))
                .isInstanceOf(IllegalArgumentException.class)
        ;
    }


}