
package com.menucarta.menucarta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    
     @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class Producto {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String img_producto;
    private String descripcion;
    private Double precio;
}
