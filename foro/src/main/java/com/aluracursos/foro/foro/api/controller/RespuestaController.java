package com.aluracursos.foro.foro.api.controller;

import com.aluracursos.foro.foro.api.dto.retorno.*;
import com.aluracursos.foro.foro.api.mapa.Estado;
import com.aluracursos.foro.foro.api.mapa.Retorno;
import com.aluracursos.foro.foro.api.mapa.Topico;
import com.aluracursos.foro.foro.api.mapa.Usuario;
import com.aluracursos.foro.foro.api.repository.RetornoRepository;
import com.aluracursos.foro.foro.api.repository.TopicoRepository;
import com.aluracursos.foro.foro.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private final RetornoRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public RespuestaController(RetornoRepository respuestaRepository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RetornoRetorno> registrar(@RequestBody RetornoReg datosRegistro, UriComponentsBuilder uri) {
        Topico topico = topicoRepository.getReferenceById(datosRegistro.topicoId());
        if (topico.getEstado() == Estado.CERRADO) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorId());
        Retorno respuesta = respuestaRepository.save(new Retorno(datosRegistro, topico, autor));
        topico.agregarRespuesta(respuesta);
        RetornoRetorno datosRespuesta = new RetornoRetorno(respuesta);
        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<RetornoList>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(RetornoList::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetornoInfo> retornaDatos(@PathVariable Long id) {
        Retorno respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new RetornoInfo(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RetornoRetorno> actualizar(@RequestBody RetornoUpdate datosActualizar) {
        Retorno respuesta = respuestaRepository.getReferenceById(datosActualizar.id());
        Topico topico = topicoRepository.getReferenceById(datosActualizar.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datosActualizar.autorId());
        respuesta.actualizarDatos(datosActualizar, topico, autor);
        return ResponseEntity.ok( new RetornoRetorno(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Retorno respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
