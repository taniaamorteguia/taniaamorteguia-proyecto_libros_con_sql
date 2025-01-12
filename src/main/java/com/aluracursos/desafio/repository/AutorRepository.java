package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository  extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND (a.anioFallecimiento > :anio OR a.anioFallecimiento IS NULL)")
    List<Autor> autoresVivosParaFecha(@Param("anio") int anio);
}
