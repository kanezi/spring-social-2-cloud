package com.kanezi.springsocial2cloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeController.class)
public class HomeControllerOauth2Test {

    @Autowired
    MockMvc mockMvc;

    @Test
    void oidcUserCanVisitHomePage() throws Exception {
        mockMvc.perform(get("")
                        .with(oidcLogin()))
                .andExpect(status().isOk());
    }

    @Test
    void oauth2UserCanVisitHomePage() throws Exception {
        OAuth2User oAuth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_user:read"),
                Collections.singletonMap("username", "test"),
                "username");

        mockMvc.perform(get("")
                        .with(oauth2Login().oauth2User(oAuth2User)))
                .andExpect(status().isOk());
    }

}
