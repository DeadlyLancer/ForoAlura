package com.aluracursos.foro.foro.api.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRegistro(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje ,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId) {
}

