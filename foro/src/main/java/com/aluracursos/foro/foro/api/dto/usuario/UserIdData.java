package com.aluracursos.foro.foro.api.dto.usuario;

import com.aluracursos.foro.foro.api.mapa.Usuario;

public record UserIdData(String nombre, String email, String contrasena, String tipo) {

    public UserIdData(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getTipo().toString());
    }
}
