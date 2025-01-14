package com.aluracursos.foro.foro.api.controller;



import com.aluracursos.foro.foro.api.dto.topico.*;
import com.aluracursos.foro.foro.api.mapa.Curso;
import com.aluracursos.foro.foro.api.mapa.Topico;
import com.aluracursos.foro.foro.api.mapa.Usuario;
import com.aluracursos.foro.foro.api.repository.CursoRepository;
import com.aluracursos.foro.foro.api.repository.TopicoRepository;
import com.aluracursos.foro.foro.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import org.springframework.web.server.ResponseStatusException;


import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<TopicoRetorno> registrar(@RequestBody @Valid TopicoRegistro infoRegistros, UriComponentsBuilder uri) {
        Usuario autor = usuarioRepository.findById(infoRegistros.autorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));
        Curso curso = cursoRepository.findById(infoRegistros.cursoId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Curso no encontrado"));

        Topico topico = new Topico(infoRegistros, autor, curso);
        topicoRepository.save(topico);

        URI location = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(location).body(new TopicoRetorno(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicoRetorno> actualizar(@RequestBody @Valid TopicoUpdate datosActualizar) {
        Topico topico = topicoRepository.findById(datosActualizar.id())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tópico no encontrado"));
        Usuario autor = usuarioRepository.findById(datosActualizar.autorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));
        Curso curso = cursoRepository.findById(datosActualizar.cursoId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Curso no encontrado"));

        topico.actualizarDatos(datosActualizar, autor, curso);
        return ResponseEntity.ok(new TopicoRetorno(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoInfo> retornaDatos(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tópico no encontrado"));
        return ResponseEntity.ok(new TopicoInfo(topico));
    }

    @GetMapping
    public ResponseEntity<Page<TopicoLista>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        Page<TopicoLista> topicos = topicoRepository.findAll(paginacion).map(TopicoLista::new);
        return ResponseEntity.ok(topicos);
    }
}
