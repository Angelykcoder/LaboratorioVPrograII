package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {
        clientes.add(new Cliente(1, "Alejandro Ruiz", "alejandro@email.com", "555-0101"));
        clientes.add(new Cliente(2, "Beatriz Méndez", "beatriz@email.com", "555-0102"));
        clientes.add(new Cliente(3, "Cristian Aguilar", "cristian@email.com", "555-0103"));
        clientes.add(new Cliente(4, "Daniela Solís", "daniela@email.com", "555-0104"));
        clientes.add(new Cliente(5, "Esteban Vega", "esteban@email.com", "555-0105"));
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(
            @PathVariable int id,
            @RequestBody Cliente clienteActualizado) {

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clienteActualizado.setId(id);
                clientes.set(i, clienteActualizado);
                return clienteActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Cliente datos) {

        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {

                if (datos.getNombre() != null) {
                    cliente.setNombre(datos.getNombre());
                }

                if (datos.getEmail() != null) {
                    cliente.setEmail(datos.getEmail());
                }

                if (datos.getTelefono() != null) {
                    cliente.setTelefono(datos.getTelefono());
                }

                return cliente;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                clientes.remove(cliente);
                return "Cliente eliminado correctamente";
            }
        }
        return "Cliente no encontrado";
    }
}