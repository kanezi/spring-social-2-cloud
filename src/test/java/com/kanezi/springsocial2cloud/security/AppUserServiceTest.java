package com.kanezi.springsocial2cloud.security;

import com.kanezi.springsocial2cloud.security.db.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    UserEntityRepository userEntityRepository;

    @InjectMocks
    AppUserService appUserService;

    @Test
    void shouldReturnTrueForExistingUser() {
        when(userEntityRepository.existsById("test"))
                .thenReturn(true);

        assertTrue(appUserService.userExists("test"));

        verify(userEntityRepository).existsById("test");
    }

    @Test
    void userExistsShouldPropagateExceptions() {
        when(userEntityRepository.existsById(anyString()))
                .thenThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class, () -> appUserService.userExists("test"));

        verify(userEntityRepository).existsById("test");
    }



}