package com.aluracursos.foro.foro.api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuth(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena) {
}