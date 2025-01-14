package com.aluracursos.foro.foro.api.dto.usuario;

import com.aluracursos.foro.foro.api.mapa.Usuario;

public record UserRetorno(String nombre, String email, String tipo) {

    public UserRetorno(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
