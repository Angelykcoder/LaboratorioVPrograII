package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Pelicula;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1, "Inception", "Christopher Nolan", 2010));
        peliculas.add(new Pelicula(2, "Interstellar", "Christopher Nolan", 2014));
        peliculas.add(new Pelicula(3, "Pulp Fiction", "Quentin Tarantino", 1994));
        peliculas.add(new Pelicula(4, "The Matrix", "Lana y Lilly Wachowski", 1999));
        peliculas.add(new Pelicula(5, "Avatar", "James Cameron", 2009));
    }

    @GetMapping
    public List<Pelicula> obtenerPeliculas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPeliculaPorId(@PathVariable int id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                return pelicula;
            }
        }
        return null;
    }

    @PostMapping
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        peliculas.add(pelicula);
        return pelicula;
    }

    @PutMapping("/{id}")
    public Pelicula actualizarPelicula(
            @PathVariable int id,
            @RequestBody Pelicula peliculaActualizada) {

        for (int i = 0; i < peliculas.size(); i++) {
            if (peliculas.get(i).getId() == id) {
                peliculaActualizada.setId(id);
                peliculas.set(i, peliculaActualizada);
                return peliculaActualizada;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pelicula actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Pelicula datos) {

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {

                if (datos.getTitulo() != null) {
                    pelicula.setTitulo(datos.getTitulo());
                }

                if (datos.getDirector() != null) {
                    pelicula.setDirector(datos.getDirector());
                }

                if (datos.getAnioEstreno() != 0) {
                    pelicula.setAnioEstreno(datos.getAnioEstreno());
                }

                return pelicula;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarPelicula(@PathVariable int id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                peliculas.remove(pelicula);
                return "Película eliminada correctamente";
            }
        }
        return "Película no encontrada";
    }
}