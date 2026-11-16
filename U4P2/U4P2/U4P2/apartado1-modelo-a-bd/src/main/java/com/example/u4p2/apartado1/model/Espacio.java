package com.example.u4p2.apartado1.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Espacio")
@Getter
@Setter
@NoArgsConstructor
public class Espacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_espacio", length = 8, unique = true)
    private String codigo_espacio;
    @Column(name = "nombre_espacio", length = 60)
    private String nombre_espacio;
    @Column(name = "aforo_espacio")
    private int aforo;
    @Column(name = "planta_espacio")
    private int planta;
    @Column(name = "accesible_espacio")
    private boolean accesible;
}
