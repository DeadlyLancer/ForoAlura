package com.aluracursos.foro.foro.api.dto.usuario;

import com.aluracursos.foro.foro.api.mapa.Usuario;

public record UserList(Long id, String nombre, String email, String tipo) {

    public UserList(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}