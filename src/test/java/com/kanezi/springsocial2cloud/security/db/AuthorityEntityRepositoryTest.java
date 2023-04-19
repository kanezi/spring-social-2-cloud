package com.kanezi.springsocial2cloud.security.db;

import com.kanezi.springsocial2cloud.security.LoginProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("postgres")
class AuthorityEntityRepositoryTest {

    @Autowired
    AuthorityEntityRepository authorityEntityRepository;


    @Test
    void canQueryByName() {
        authorityEntityRepository.save(new AuthorityEntity("test", LoginProvider.APP));

        Optional<AuthorityEntity> test = authorityEntityRepository.findByName("test");

        assertThat(test).hasValueSatisfying(ae -> {
            assertThat(ae.getName()).isEqualTo("test");
            assertThat(ae.getProvider()).isEqualTo(LoginProvider.APP);
            assertThat(ae.getId()).isNotNull();
        });
    }
}