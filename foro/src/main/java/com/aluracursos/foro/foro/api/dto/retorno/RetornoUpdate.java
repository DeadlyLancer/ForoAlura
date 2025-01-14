package com.aluracursos.foro.foro.api.dto.retorno;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RetornoUpdate(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotBlank
        Long autorId,
        @NotNull
        Boolean solucion) {

}
