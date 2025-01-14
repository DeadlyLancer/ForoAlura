package com.aluracursos.foro.foro.api.repository;

import com.aluracursos.foro.foro.api.mapa.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
