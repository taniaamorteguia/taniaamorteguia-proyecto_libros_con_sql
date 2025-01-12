package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT * FROM libros", nativeQuery = true)
    List<Libro> encontrarLibrosBuscados();

   @Query(value = "SELECT * FROM libros WHERE idiomas LIKE %:idioma%" , nativeQuery = true)
   List<Libro> listarLibrosIdioma(@Param("idioma") String idioma);
}

