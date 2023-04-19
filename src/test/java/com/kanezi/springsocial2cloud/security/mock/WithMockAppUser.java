package com.kanezi.springsocial2cloud.security.mock;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = AppUserSecurityContextFactory.class)
public @interface WithMockAppUser {
    String username() default "test";
    String email() default "test@test.com";
    String password() default "test";
    String[] authorities() default {"test"};
}
