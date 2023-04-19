package com.kanezi.springsocial2cloud.security.db;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_authorities")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthorityEntity {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name = "user_authority_user_fk"))
    @ToString.Exclude
    UserEntity user;
    @ManyToOne
    @JoinColumn(name = "authority_id", foreignKey = @ForeignKey(name = "user_authority_authority_fk"))
    @ToString.Exclude
    AuthorityEntity authority;

    public UserAuthorityEntity(UserEntity user, AuthorityEntity authority) {
        this.user = user;
        this.authority = authority;
    }
}
