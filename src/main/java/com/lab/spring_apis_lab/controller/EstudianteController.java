package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Estudiante;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteController() {
        estudiantes.add(new Estudiante(1, "Carlos Gómez", "Sistemas", 88.5));
        estudiantes.add(new Estudiante(2, "María López", "Industrial", 92.0));
        estudiantes.add(new Estudiante(3, "Juan Pérez", "Sistemas", 75.4));
        estudiantes.add(new Estudiante(4, "Ana Rodríguez", "Civil", 85.0));
        estudiantes.add(new Estudiante(5, "Luis Martínez", "Electrónica", 90.1));
    }

    @GetMapping
    public List<Estudiante> obtenerEstudiantes() {
        return estudiantes;
    }

    @GetMapping("/{id}")
    public Estudiante obtenerEstudiantePorId(@PathVariable int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }

    @PostMapping
    public Estudiante crearEstudiante(@RequestBody Estudiante estudiante) {
        estudiantes.add(estudiante);
        return estudiante;
    }

    @PutMapping("/{id}")
    public Estudiante actualizarEstudiante(
            @PathVariable int id,
            @RequestBody Estudiante estudianteActualizado) {

        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId() == id) {
                estudianteActualizado.setId(id);
                estudiantes.set(i, estudianteActualizado);
                return estudianteActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Estudiante actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Estudiante datos) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {

                if (datos.getNombre() != null) {
                    estudiante.setNombre(datos.getNombre());
                }

                if (datos.getCarrera() != null) {
                    estudiante.setCarrera(datos.getCarrera());
                }

                if (datos.getPromedio() != 0) {
                    estudiante.setPromedio(datos.getPromedio());
                }

                return estudiante;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarEstudiante(@PathVariable int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                estudiantes.remove(estudiante);
                return "Estudiante eliminado correctamente";
            }
        }
        return "Estudiante no encontrado";
    }
}