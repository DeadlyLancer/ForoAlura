package com.aluracursos.foro.foro.api.controller;

import com.aluracursos.foro.foro.api.dto.usuario.*;
import com.aluracursos.foro.foro.api.mapa.Usuario;
import com.aluracursos.foro.foro.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<UserRetorno> registrar(
            @RequestBody @Valid UserReg datosRegistro,
            UriComponentsBuilder uri) {

        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistro));
        URI location = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(location).body(new UserRetorno(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UserList>> listar(
            @PageableDefault(size = 10) Pageable paginacion) {

        Page<UserList> usuarios = usuarioRepository.findAll(paginacion).map(UserList::new);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserIdData> retornaDatos(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));
        return ResponseEntity.ok(new UserIdData(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserRetorno> actualizar(
            @RequestBody @Valid UserUpdate datosActualizar) {

        Usuario usuario = usuarioRepository.findById(datosActualizar.id())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));

        usuario.actualizarDatos(datosActualizar);
        return ResponseEntity.ok(new UserRetorno(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));

        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
