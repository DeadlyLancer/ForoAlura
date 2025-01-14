package com.aluracursos.foro.foro.api.mapa;

import com.aluracursos.foro.foro.api.dto.usuario.UserUpdate;
import com.aluracursos.foro.foro.api.dto.usuario.UserReg;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Type tipo;


    public Usuario(UserReg datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.contrasena = datos.contrasena();
        this.tipo = datos.tipo() != null ? datos.tipo() : Type.ROLE_USER;
    }


    public void actualizarDatos(UserUpdate datosActualizar) {
        if (datosActualizar.nombre() != null) {
            this.nombre = datosActualizar.nombre();
        }
        if (datosActualizar.email() != null) {
            this.email = datosActualizar.email();
        }
        if (datosActualizar.contrasena() != null) {
            this.contrasena = datosActualizar.contrasena();
        }
        if (datosActualizar.tipo() != null) {
            this.tipo = datosActualizar.tipo();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(tipo.toString()));
    }

    @Override
    public String getPassword() {
        return contrasena;
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
