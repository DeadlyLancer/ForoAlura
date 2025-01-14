package com.aluracursos.foro.foro.api.dto.retorno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RetornoReg(
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        Long autorId,
        @NotBlank
        Boolean solucion) {
}
