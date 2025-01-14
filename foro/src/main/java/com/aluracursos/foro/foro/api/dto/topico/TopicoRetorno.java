package com.aluracursos.foro.foro.api.dto.topico;

import com.aluracursos.foro.foro.api.mapa.Topico;

public record TopicoRetorno(String titulo, String mensaje, String autor, String curso) {

    public TopicoRetorno(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}