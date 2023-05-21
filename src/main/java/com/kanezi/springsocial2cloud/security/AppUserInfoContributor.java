package com.kanezi.springsocial2cloud.security;

import com.kanezi.springsocial2cloud.security.db.UserEntity;
import com.kanezi.springsocial2cloud.security.db.UserEntityRepository;
import lombok.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Value
public class AppUserInfoContributor implements InfoContributor {

    UserEntityRepository userEntityRepository;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app-user.stats", Map.of("count", userEntityRepository.count()))
                .build();
    }
}
