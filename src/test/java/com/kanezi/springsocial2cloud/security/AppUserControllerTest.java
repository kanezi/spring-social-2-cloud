package com.kanezi.springsocial2cloud.security;

import com.kanezi.springsocial2cloud.TestSecurityConfiguration;
import com.kanezi.springsocial2cloud.security.mock.WithMockAppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AppUserController.class)
@Import(TestSecurityConfiguration.class)
class AppUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AppUserService appUserService;

    @Test
    @WithAnonymousUser
    void anonymousUserCanSignUp() throws Exception {
        mockMvc.perform(get("/user/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("app-user/signup"))
        ;
    }

    @Test
    @WithAnonymousUser
    void anonymousUserIsNotAllowedToSeeUserPage() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "http://localhost/login"))
        ;
    }


    @Test
    @WithMockAppUser(password = "1234")
    void usersCanChangePasswords() throws Exception {
        mockMvc.perform(patch("/user/password")
                        .with(csrf())
                        .param("oldPassword", "1234")
                        .param("password", "9876")
                        .param("passwordConfirm", "9876")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user"))
        ;
    }

}