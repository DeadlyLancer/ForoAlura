package com.aluracursos.foro.foro.api.mapa;

import com.aluracursos.foro.foro.api.dto.topico.TopicoRegistro;
import com.aluracursos.foro.foro.api.dto.topico.TopicoUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name= "topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.SINRESPUESTA;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private List<Retorno> retornos = new ArrayList<>();

public Topico(TopicoRegistro datos, Usuario autor, Curso curso) {
    this.titulo = datos.titulo();
    this.mensaje = datos.mensaje();
    this.autor = autor;
    this.curso = curso;
}
    public void actualizarDatos(TopicoUpdate datosActualizar, Usuario autor, Curso curso) {
        if (datosActualizar.titulo() != null) {
            this.titulo = datosActualizar.titulo();
        }
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
        }
        if (datosActualizar.estado() != datosActualizar.estado()) {
            this.estado = datosActualizar.estado();
        }
        if (autor != null) {
            this.autor = autor;
        }
        if (curso != null) {
            this.curso = curso;
        }
    }
    public void agregarRespuesta(Retorno respuesta) {
        this.retornos.add(respuesta);
        if (respuesta.getSolucion()) {
            this.estado = Estado.RESUELTO;
        } else {
            this.estado = Estado.SINRESPUESTA;
        }
    }

    public void cerrarTopico() {
        this.estado = Estado.CERRADO;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}