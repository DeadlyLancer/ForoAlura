package com.aluracursos.foro.foro.api.dto.retorno;

import com.aluracursos.foro.foro.api.dto.topico.TopicoRetorno;
import com.aluracursos.foro.foro.api.dto.usuario.UserRetorno;
import com.aluracursos.foro.foro.api.mapa.Retorno;


public record RetornoInfo(String mensaje, TopicoRetorno topico, String fechaCreacion, UserRetorno autor, String solucion) {

    public RetornoInfo(Retorno respuesta) {
        this(respuesta.getMensaje(), new TopicoRetorno(respuesta.getTopico()), respuesta.getFechaCreacion().toString(),
                new UserRetorno(respuesta.getAutor()), respuesta.getSolucion().toString());
    }
}