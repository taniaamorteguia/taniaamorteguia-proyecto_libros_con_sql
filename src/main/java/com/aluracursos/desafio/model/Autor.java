package com.aluracursos.desafio.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "autor")
public class Autor {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    @Column(unique = true)
    public String nombre;
    public int anioNacimiento;
    public int anioFallecimiento;

    public Autor() {
    }

    public Autor(Optional<DatosResponseAutorSearch> autorBuscado) {
        this.nombre = autorBuscado.get().autores.get(0).nombre;
        this.anioNacimiento = Integer.parseInt(String.valueOf(autorBuscado.get().autores.get(0).anioNacimiento));
        this.anioFallecimiento = Integer.parseInt(String.valueOf(autorBuscado.get().autores.get(0).anioFallecimiento));
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(int anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    @Override
    public String toString() {
        return "Autor{" + "\n" +
                "   Id=" + Id + "\n" +
                "   nombre=" + nombre + "\n" +
                "   anioNacimiento=" + anioNacimiento + "\n" +
                "   anioFallecimiento=" + anioFallecimiento + "\n" +
                '}';
    }
}
