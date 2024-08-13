package com.menucarta.menucarta.controller;

import com.menucarta.menucarta.entity.Producto;
import com.menucarta.menucarta.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")//actualiza el producto
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto producto = productoService.actualizarProducto(id, productoActualizado);
        return ResponseEntity.ok(producto);
    }
     @PutMapping("/{productoId}/categoria/{categoriaId}")//actualiza la categoria del producto
    public ResponseEntity<Producto> actualizarCategoriaDelProducto(
            @PathVariable Long productoId,
            @PathVariable Long categoriaId) {

        Producto productoActualizado = productoService.actualizarCategoriaDelProducto(productoId, categoriaId);

        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

