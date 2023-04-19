package com.kanezi.springsocial2cloud.security.db;

import com.kanezi.springsocial2cloud.security.LoginProvider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_id_generator")
    @SequenceGenerator(name = "authority_id_generator", sequenceName = "authorities_seq", allocationSize = 5)
    Long id;
    String name;

    @Enumerated(value = EnumType.STRING)
    LoginProvider provider;

    @OneToMany(mappedBy = "authority")
    List<UserAuthorityEntity> assignedTo = new ArrayList<>();

    public AuthorityEntity(String name, LoginProvider provider) {
        this.name = name;
        this.provider = provider;
    }
}
