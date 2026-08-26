package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Libro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final List<Libro> libros = new ArrayList<>();

    public LibroController() {
        libros.add(new Libro(1, "Cien Años de Soledad", "Gabriel García Márquez", 450.00));
        libros.add(new Libro(2, "Don Quijote de la Mancha", "Miguel de Cervantes", 520.00));
        libros.add(new Libro(3, "1984", "George Orwell", 380.00));
        libros.add(new Libro(4, "El Principito", "Antoine de Saint-Exupéry", 250.00));
        libros.add(new Libro(5, "Rayuela", "Julio Cortázar", 410.00));
    }

    @GetMapping
    public List<Libro> obtenerLibros() {
        return libros;
    }

    @GetMapping("/{id}")
    public Libro obtenerLibroPorId(@PathVariable int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        libros.add(libro);
        return libro;
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(
            @PathVariable int id,
            @RequestBody Libro libroActualizado) {

        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == id) {
                libroActualizado.setId(id);
                libros.set(i, libroActualizado);
                return libroActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Libro actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Libro datos) {

        for (Libro libro : libros) {
            if (libro.getId() == id) {

                if (datos.getTitulo() != null) {
                    libro.setTitulo(datos.getTitulo());
                }

                if (datos.getAutor() != null) {
                    libro.setAutor(datos.getAutor());
                }

                if (datos.getPrecio() != 0) {
                    libro.setPrecio(datos.getPrecio());
                }

                return libro;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarLibro(@PathVariable int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libros.remove(libro);
                return "Libro eliminado correctamente";
            }
        }
        return "Libro no encontrado";
    }
}