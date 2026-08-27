package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {
        pedidos.add(new Pedido(1, "Alejandro Ruiz", 1250.50, "Enviado"));
        pedidos.add(new Pedido(2, "Beatriz Méndez", 450.00, "Pendiente"));
        pedidos.add(new Pedido(3, "Cristian Aguilar", 3200.00, "Entregado"));
        pedidos.add(new Pedido(4, "Daniela Solís", 890.75, "Procesando"));
        pedidos.add(new Pedido(5, "Esteban Vega", 150.00, "Cancelado"));
    }

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(
            @PathVariable int id,
            @RequestBody Pedido pedidoActualizado) {

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == id) {
                pedidoActualizado.setId(id);
                pedidos.set(i, pedidoActualizado);
                return pedidoActualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pedido actualizarParcialmente(
            @PathVariable int id,
            @RequestBody Pedido datos) {

        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {

                if (datos.getCliente() != null) {
                    pedido.setCliente(datos.getCliente());
                }

                if (datos.getTotal() != 0) {
                    pedido.setTotal(datos.getTotal());
                }

                if (datos.getEstado() != null) {
                    pedido.setEstado(datos.getEstado());
                }

                return pedido;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                pedidos.remove(pedido);
                return "Pedido eliminado correctamente";
            }
        }
        return "Pedido no encontrado";
    }
}