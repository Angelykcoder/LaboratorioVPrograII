package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Empleado;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final List<Empleado> empleados = new ArrayList<>();

    public EmpleadoController() {
        empleados.add(new Empleado(1, "Mario Ramírez", "Desarrollador Java", 12000.00));
        empleados.add(new Empleado(2, "Laura Castillo", "Diseñadora UX", 9500.00));
        empleados.add(new Empleado(3, "Roberto Blanco", "Analista de Datos", 11000.00));
        empleados.add(new Empleado(4, "Diana Torres", "Gerente de Proyecto", 18000.00));
        empleados.add(new Empleado(5, "Fernando Ruiz", "Soporte Técnico", 7500.00));
    }

    @GetMapping
    public List<Empleado> obtenerEmpleados() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleadoPorId(@PathVariable int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        empleados.add(empleado);
        return empleado;
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(
            @PathVariable int id,
            @RequestBody Empleado empleadoActualizado) {

        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == id) {
                empleadoActualizado.setId(id);
                empleados.set(i, empleadoActualizado);
                return empleadoActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Empleado actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Empleado datos) {

        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {

                if (datos.getNombre() != null) {
                    empleado.setNombre(datos.getNombre());
                }

                if (datos.getPuesto() != null) {
                    empleado.setPuesto(datos.getPuesto());
                }

                if (datos.getSalario() != 0) {
                    empleado.setSalario(datos.getSalario());
                }

                return empleado;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarEmpleado(@PathVariable int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                empleados.remove(empleado);
                return "Empleado eliminado correctamente";
            }
        }
        return "Empleado no encontrado";
    }
}