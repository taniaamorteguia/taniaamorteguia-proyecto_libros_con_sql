package com.aluracursos.desafio.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    @Column(unique = true)
    public String titulo;
    public String autor;
    public String idiomas;
    public Double descargas;

    public Libro() {
    }

    public Libro(Optional<DatosLibros> datosLibros) {
        this.titulo = datosLibros.get().titulo();
        this.autor = datosLibros.get().autor().toString();
        this.idiomas = datosLibros.get().idiomas().toString();
        this.descargas = datosLibros.get().numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro{\n" +
                "   Id= " + Id + "\n" +
                "   titulo= " + titulo + "\n" +
                "   autor= " + autor + "\n" +
                "   idiomas= " + idiomas + "\n" +
                "   descargas= " + descargas + "\n" +
                '}';
    }
}
