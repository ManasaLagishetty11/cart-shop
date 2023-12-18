package com.evoke.cartshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="role_id")
    private RoleEnum role;

    public Role(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_"+this.role.toString();
    }
}
