package com.aluracursos.foro.foro.api.dto.retorno;

import com.aluracursos.foro.foro.api.mapa.Retorno;

public record RetornoList(Long id, String mensaje, String topico, String autor) {
public RetornoList(Retorno respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}

