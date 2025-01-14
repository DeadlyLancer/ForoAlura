package com.aluracursos.foro.foro.api.dto.usuario;

import com.aluracursos.foro.foro.api.mapa.Type;

public record UserUpdate(Long id, String nombre, String email, String contrasena, Type tipo) {
}
