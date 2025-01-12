package com.aluracursos.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        //@JsonAlias("authors") List<MetaDatosAutor> autores
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String anioNacimiento,
        @JsonAlias("death_year") String anioFallecimiento
) {
}
