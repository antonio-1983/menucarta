package com.menucarta.menucarta.repository;

import com.menucarta.menucarta.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

