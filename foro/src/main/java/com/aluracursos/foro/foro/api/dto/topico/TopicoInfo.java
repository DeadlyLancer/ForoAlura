package com.aluracursos.foro.foro.api.dto.topico;

import com.aluracursos.foro.foro.api.dto.CursoRD;
import com.aluracursos.foro.foro.api.mapa.Topico;
import com.aluracursos.foro.foro.api.user.DatosRespuestaUsuario;

public record TopicoInfo(Long id, String titulo, String mensaje, String fechaCreacion, String estado, DatosRespuestaUsuario autor,
                         CursoRD curso) {

    public TopicoInfo(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(), new DatosRespuestaUsuario(topico.getAutor()),
                new CursoRD(topico.getCurso()));
    }
}
