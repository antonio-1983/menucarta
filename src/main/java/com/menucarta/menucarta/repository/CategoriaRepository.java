package com.menucarta.menucarta.repository;

import com.menucarta.menucarta.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

