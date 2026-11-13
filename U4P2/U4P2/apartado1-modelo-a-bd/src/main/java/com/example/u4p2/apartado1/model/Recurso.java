package com.example.u4p2.apartado1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Recursos")
@Getter
@Setter
@NoArgsConstructor
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_recurso", unique = true, length = 12)
    private String codigo_recurso;
    @Column(name = "nombre_recurso", length = 80)
    private String nombre_recurso;
    @Column(name = "descripcion_recurso", length = 250, nullable = false)
    private String descripcion;
    @Column(name = "coste_reposicion_recurso", scale = 2, precision = 8)
    private BigDecimal coste_reposicion;
    @Column(name = "prestable_recurso")
    private boolean prestable;
}