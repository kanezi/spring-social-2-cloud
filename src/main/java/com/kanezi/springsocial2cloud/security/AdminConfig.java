package com.kanezi.springsocial2cloud.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {
    @Value("${admin.password}") String adminPassword;
}
