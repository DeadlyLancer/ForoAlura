package com.aluracursos.foro.foro.api.dto.retorno;

import com.aluracursos.foro.foro.api.mapa.Retorno;

public record RetornoRetorno(String mensaje, String topico, String autor) {

    public RetornoRetorno(Retorno retorno) {
        this(retorno.getMensaje(), retorno.getTopico().getTitulo(), retorno.getAutor().getNombre());
    }
}