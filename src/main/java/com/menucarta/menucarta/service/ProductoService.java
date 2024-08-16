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
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
     @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();

        // Actualiza solo si el nuevo valor no es null
        if (productoActualizado.getNombre() != null) {
            producto.setNombre(productoActualizado.getNombre());
        }
        if (productoActualizado.getImg_producto() != null) {
            producto.setImg_producto(productoActualizado.getImg_producto());
        }
        if (productoActualizado.getDescripcion() != null) {
            producto.setDescripcion(productoActualizado.getDescripcion());
        }
        if (productoActualizado.getPrecio() != null) {
            producto.setPrecio(productoActualizado.getPrecio());
        }
        if (productoActualizado.getCategoria() != null) {
            producto.setCategoria(productoActualizado.getCategoria());
        }
        return productoRepository.save(producto);
        }
        else 
        {
            throw new RuntimeException("Producto no encontrado");
        }
    }  
    public Producto actualizarCategoriaDelProducto(Long productoId, Long categoriaId) {//actualiza la categoria del producto
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);

            if (categoriaOptional.isPresent()) {
                producto.setCategoria(categoriaOptional.get());
                return productoRepository.save(producto);
            } else {
                throw new RuntimeException("Categoría no encontrada");
            }
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }
    
}

