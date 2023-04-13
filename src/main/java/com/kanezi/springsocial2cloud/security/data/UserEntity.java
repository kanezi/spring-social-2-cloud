package com.kanezi.springsocial2cloud.security.data;

import com.kanezi.springsocial2cloud.security.LoginProvider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    String username;

    String password;
    String email;
    String name;
    @Column(name = "image_url")
    String imageUrl;

    @Enumerated(value = EnumType.STRING)
    LoginProvider provider;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserAuthorityEntity> userAuthorities = new ArrayList<>();

    @PrePersist
    void assignCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public UserEntity(String username, String password, String email, String name, String imageUrl, LoginProvider provider) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.provider = provider;
    }

    public void addAuthority(AuthorityEntity authority) {

        if (userAuthorities.stream().anyMatch(uae -> uae.getUser().equals(this) && uae.getAuthority().equals(authority))) {
            return;
        }

        UserAuthorityEntity userAuthority = new UserAuthorityEntity(this, authority);

        this.userAuthorities.add(userAuthority);
        authority.getAssignedTo().add(userAuthority);
    }

    public void removeAuthority(AuthorityEntity authority) {

        Iterator<UserAuthorityEntity> iterator = userAuthorities.iterator();

        while (iterator.hasNext()) {
            UserAuthorityEntity next = iterator.next();

            if (next.getAuthority().equals(authority)) {
                iterator.remove();
                authority.getAssignedTo().remove(next);
                next.setUser(null);
                next.setAuthority(null);
            }

        }

    }

    public void mergeAuthorities(List<AuthorityEntity> authorities) {
        var toRemove = this
                .userAuthorities
                .stream()
                .filter(uae -> !authorities.contains(uae.getAuthority()))
                .toList();

        toRemove.forEach(uae -> this.removeAuthority(uae.getAuthority()));

        authorities.forEach(this::addAuthority);
    }
}

