package com.aluracursos.foro.foro.api.dto;

import com.aluracursos.foro.foro.api.mapa.Curso;

public record CursoRD(String nombre, String categoria) {

    public CursoRD(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
