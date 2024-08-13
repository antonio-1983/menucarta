package com.menucarta.menucarta.controller;

import com.menucarta.menucarta.entity.Categoria;
import com.menucarta.menucarta.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    public Categoria guardarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")//actualiza la categoria
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizada) {
    Categoria categoria = categoriaService.actualizarCategoria(id, categoriaActualizada);
        if (categoria != null) {
        return ResponseEntity.ok(categoria);
        } else {
        return ResponseEntity.notFound().build();
        }
    }
     @PutMapping("/{categoriaId}/producto/{productoId}")//le asigna un producto a la categoria
    public ResponseEntity<Categoria> asignarProductoACategoria(
            @PathVariable Long categoriaId,
            @PathVariable Long productoId) {

        Categoria categoriaActualizada = categoriaService.asignarProductoACategoria(categoriaId, productoId);

        if (categoriaActualizada != null) {
            return ResponseEntity.ok(categoriaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

