package com.example.u4p2.apartado2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Material_Aula")
@Getter
@Setter
@NoArgsConstructor
public class Material_Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "referencia_material", length = 12, unique = true)
    private String referencia;
    @Column(name = "nombre_material", length = 80)
    private String nombre_material;
    @Column(name = "descripcion_material", length = 250)
    private String descripcion;
    @Column(name = "coste_reposicion", precision = 8, scale = 2)
    private BigDecimal coste_reposicion;
    @Column(name = "unidades_material")
    private Short unidades;
}
