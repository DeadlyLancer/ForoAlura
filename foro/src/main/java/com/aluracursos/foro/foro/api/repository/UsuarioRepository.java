package com.aluracursos.foro.foro.api.repository;

import com.aluracursos.foro.foro.api.mapa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String username);
}
