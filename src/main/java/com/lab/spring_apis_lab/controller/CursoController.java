package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Curso;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final List<Curso> cursos = new ArrayList<>();

    public CursoController() {
        cursos.add(new Curso(1, "Programación II", "Dr. Ramos", 60));
        cursos.add(new Curso(2, "Bases de Datos I", "Ing. Estrada", 45));
        cursos.add(new Curso(3, "Estructuras de Datos", "Lic. García", 50));
        cursos.add(new Curso(4, "Desarrollo Web", "Ing. Morales", 40));
        cursos.add(new Curso(5, "Redes de Computadoras", "Ing. Mendoza", 55));
    }

    @GetMapping
    public List<Curso> obtenerCursos() {
        return cursos;
    }

    @GetMapping("/{id}")
    public Curso obtenerCursoPorId(@PathVariable int id) {
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        cursos.add(curso);
        return curso;
    }

    @PutMapping("/{id}")
    public Curso actualizarCurso(
            @PathVariable int id,
            @RequestBody Curso cursoActualizado) {

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId() == id) {
                cursoActualizado.setId(id);
                cursos.set(i, cursoActualizado);
                return cursoActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Curso actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Curso datos) {

        for (Curso curso : cursos) {
            if (curso.getId() == id) {

                if (datos.getNombre() != null) {
                    curso.setNombre(datos.getNombre());
                }

                if (datos.getInstructor() != null) {
                    curso.setInstructor(datos.getInstructor());
                }

                if (datos.getDuracionHoras() != 0) {
                    curso.setDuracionHoras(datos.getDuracionHoras());
                }

                return curso;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarCurso(@PathVariable int id) {
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                cursos.remove(curso);
                return "Curso eliminado correctamente";
            }
        }
        return "Curso no encontrado";
    }
}