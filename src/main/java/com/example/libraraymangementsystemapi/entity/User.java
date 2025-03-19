package com.example.libraraymangementsystemapi.entity;

import com.example.libraraymangementsystemapi.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date registeredDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    protected User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.role=role;
        this.registeredDate= Date.valueOf(java.time.LocalDate.now());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
