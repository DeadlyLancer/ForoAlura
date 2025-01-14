package com.aluracursos.foro.foro.api.mapa;

import com.aluracursos.foro.foro.api.dto.retorno.RetornoReg;
import com.aluracursos.foro.foro.api.dto.retorno.RetornoUpdate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Retorno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    private Boolean solucion = false;

    // Constructor personalizado
    public Retorno(RetornoReg datos, Topico topico, Usuario autor) {
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.autor = autor;
        this.solucion = datos.solucion();
        actualizarEstadoTopico(datos.solucion());
    }

    public void actualizarDatos(RetornoUpdate datosActualizar, Topico topico, Usuario autor) {
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
        }
        if (topico != null) {
            this.topico = topico;
        }
        if (autor != null) {
            this.autor = autor;
        }
        if (datosActualizar.solucion() != null && !datosActualizar.solucion().equals(this.solucion)) {
            this.solucion = datosActualizar.solucion();
            actualizarEstadoTopico(this.solucion);
        }
    }

    // Método auxiliar para actualizar el estado del tópico
    private void actualizarEstadoTopico(Boolean esSolucion) {
        if (esSolucion) {
            this.topico.setEstado(Estado.RESUELTO);
        } else {
            this.topico.setEstado(Estado.ABIERTO);
        }
    }
}
