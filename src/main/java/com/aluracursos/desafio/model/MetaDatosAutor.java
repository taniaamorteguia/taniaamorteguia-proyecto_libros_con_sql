package com.aluracursos.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDatosAutor {
    @JsonProperty("name")
    public String nombre;
    @JsonProperty("birth_year")
    public int anioNacimiento;
    @JsonProperty("death_year")
    public int anioFallecimiento;
}
