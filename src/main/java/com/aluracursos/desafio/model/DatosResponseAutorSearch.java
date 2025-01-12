package com.aluracursos.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosResponseAutorSearch {
    @JsonProperty("authors")
    public List<MetaDatosAutor> autores;
}
