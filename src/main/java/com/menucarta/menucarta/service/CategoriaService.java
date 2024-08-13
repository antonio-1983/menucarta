package com.menucarta.menucarta.service;

import com.menucarta.menucarta.entity.Categoria;
import com.menucarta.menucarta.entity.Producto;
import com.menucarta.menucarta.repository.CategoriaRepository;
import com.menucarta.menucarta.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

      @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
    Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
    if (categoriaOptional.isPresent()) {
        Categoria categoria = categoriaOptional.get();

        if (categoriaActualizada.getNombre() != null) {
            categoria.setNombre(categoriaActualizada.getNombre());
        }
        if (categoriaActualizada.getImg() != null) {
            categoria.setImg(categoriaActualizada.getImg());
        }

        return categoriaRepository.save(categoria);
        } else {
        return null;
        }
    }
    public Categoria asignarProductoACategoria(Long categoriaId, Long productoId) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            Optional<Producto> productoOptional = productoRepository.findById(productoId);

            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                producto.setCategoria(categoria);  // Asociar la categoría al producto
                categoria.getProductos().add(producto);  // Agregar el producto a la lista de productos de la categoría
                productoRepository.save(producto);  // Guardar el producto con la nueva categoría
                return categoriaRepository.save(categoria);  // Guardar la categoría actualizada
            } else {
                throw new RuntimeException("Producto no encontrado");
            }
        } else {
            throw new RuntimeException("Categoría no encontrada");
        }
    }
}

