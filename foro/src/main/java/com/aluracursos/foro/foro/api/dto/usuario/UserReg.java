package com.aluracursos.foro.foro.api.dto.usuario;

import com.aluracursos.foro.foro.api.mapa.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserReg(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena,
        @NotNull
        Type tipo) {
}
