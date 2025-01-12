package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.*;
import com.aluracursos.desafio.repository.AutorRepository;
import com.aluracursos.desafio.repository.LibroRepository;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros 
                    2 - Buscar autores
                    3 - Mostrar libros buscadas
                    4 - Buscar autores vivos en determinado año
                    5 - Listar libros por idioma
                                 
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarAutor();
                    break;
                case 3:
                    mostrarLibrosBuscadas();
                    break;
                case 4:
                    buscarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public void buscarLibro(){
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosBaseLibros.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isEmpty()) {
            System.out.println("el libro no fue encontrado");
        } else {
            Libro libro = new Libro(libroBuscado);
            libroRepository.save(libro);
            System.out.println(libro.toString());
        }
    }

    public void buscarAutor(){
        System.out.println("Escribe el nombre del autor que deseas buscar");
        var autorLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + autorLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosBaseAutor.class);
        Optional<DatosResponseAutorSearch> autorBuscado = datosBusqueda.resultados().stream()
                .filter(a -> a.autores.stream().findFirst().get().nombre.toUpperCase().contains(autorLibro.toUpperCase()))
                .findFirst();
        if (autorBuscado.isEmpty()) {
            System.out.println("el autor no fue encontrado");
        } else {
            Autor autor = new Autor(autorBuscado);
            autorRepository.save(autor);
            System.out.println(autor.toString());
        }
    }


    public void mostrarLibrosBuscadas(){
        List<Libro> historialBuscados = libroRepository.encontrarLibrosBuscados();
        System.out.println("*** Libros buscados ***");
        historialBuscados.forEach(l -> System.out.println(l.getTitulo()));
        System.out.println("***********************");
    }

    public void buscarAutoresVivosPorAnio(){
        System.out.println("Por favor digite un año para ver la lista de autores vivos:");
        var anio = teclado.nextLine();
        List<Autor> autoresVivos = autorRepository.autoresVivosParaFecha(Integer.parseInt(anio));
        System.out.println("*** La lista de autores vivos es: ***");
        autoresVivos.forEach(a -> System.out.println(a.toString()));
        System.out.println("************************************");
    }

    public void listarLibrosIdioma(){
        System.out.println("Por favor digite un idioma para ver la lista de libros disponibles en ese idioma:");
        var idioma = teclado.nextLine();
        List<Libro> listarLibrosIdioma = libroRepository.listarLibrosIdioma(idioma);
        System.out.println("*** La lista de libros por idioma es: ***");
        listarLibrosIdioma.forEach(l -> System.out.println(l.toString()));
        System.out.println("*****************************************");
    }



}
