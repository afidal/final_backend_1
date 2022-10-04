package com.example.clinicaodontologica.security_configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString

@Entity
public class Usuario implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String password;
    @Enumerated (EnumType.STRING)
    private UsuarioRol usuarioRol;

    public Usuario() {
    }

    public Usuario(String nombre, String username, String email, String password, UsuarioRol usuarioRol) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usuarioRol = usuarioRol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioRol.name());
        return Collections.singletonList(grantedAuthority);
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
