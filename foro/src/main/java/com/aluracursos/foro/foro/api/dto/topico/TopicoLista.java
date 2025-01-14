package com.aluracursos.foro.foro.api.dto.topico;

import com.aluracursos.foro.foro.api.mapa.Topico;

public record TopicoLista(Long id, String titulo, String mensaje, String autor, String curso) {

    public TopicoLista(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }
}
