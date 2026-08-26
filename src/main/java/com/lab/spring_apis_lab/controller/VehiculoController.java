package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Vehiculo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoController() {
        vehiculos.add(new Vehiculo(1, "Toyota", "Corolla", 2020));
        vehiculos.add(new Vehiculo(2, "Honda", "Civic", 2022));
        vehiculos.add(new Vehiculo(3, "Ford", "Mustang", 2019));
        vehiculos.add(new Vehiculo(4, "Chevrolet", "Onix", 2021));
        vehiculos.add(new Vehiculo(5, "Nissan", "Sentra", 2023));
    }

    @GetMapping
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculos;
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerVehiculoPorId(@PathVariable int id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId() == id) {
                return vehiculo;
            }
        }
        return null;
    }

    @PostMapping
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        return vehiculo;
    }

    @PutMapping("/{id}")
    public Vehiculo actualizarVehiculo(
            @PathVariable int id,
            @RequestBody Vehiculo vehiculoActualizado) {

        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getId() == id) {
                vehiculoActualizado.setId(id);
                vehiculos.set(i, vehiculoActualizado);
                return vehiculoActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Vehiculo actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Vehiculo datos) {

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId() == id) {

                if (datos.getMarca() != null) {
                    vehiculo.setMarca(datos.getMarca());
                }

                if (datos.getModelo() != null) {
                    vehiculo.setModelo(datos.getModelo());
                }

                if (datos.getAnio() != 0) {
                    vehiculo.setAnio(datos.getAnio());
                }

                return vehiculo;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarVehiculo(@PathVariable int id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId() == id) {
                vehiculos.remove(vehiculo);
                return "Vehículo eliminado correctamente";
            }
        }
        return "Vehículo no encontrado";
    }
}