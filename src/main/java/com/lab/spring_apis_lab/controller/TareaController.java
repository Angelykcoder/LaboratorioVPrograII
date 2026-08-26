package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Tarea;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final List<Tarea> tareas = new ArrayList<>();

    public TareaController() {
        tareas.add(new Tarea(1, "Lab 5 Spring Boot", "Crear las 10 APIs en memoria", "En progreso"));
        tareas.add(new Tarea(2, "Estudiar BD", "Repasar comandos SQL", "Pendiente"));
        tareas.add(new Tarea(3, "Proyecto Web", "Diseñar interfaz en Figma", "Completada"));
        tareas.add(new Tarea(4, "Informe Redes", "Redactar documento de topologías", "Pendiente"));
        tareas.add(new Tarea(5, "Comprar libro", "Adquirir texto de algoritmos", "Completada"));
    }

    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea obtenerTareaPorId(@PathVariable int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        tareas.add(tarea);
        return tarea;
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(
            @PathVariable int id,
            @RequestBody Tarea tareaActualizada) {

        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == id) {
                tareaActualizada.setId(id);
                tareas.set(i, tareaActualizado);
                return tareaActualizada;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Tarea actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Tarea datos) {

        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {

                if (datos.getTitulo() != null) {
                    tarea.setTitulo(datos.getTitulo());
                }

                if (datos.getDescripcion() != null) {
                    tarea.setDescripcion(datos.getDescripcion());
                }

                if (datos.getEstado() != null) {
                    tarea.setEstado(datos.getEstado());
                }

                return tarea;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarTarea(@PathVariable int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                tareas.remove(tarea);
                return "Tarea eliminada correctamente";
            }
        }
        return "Tarea no encontrada";
    }
}