package com.example.U4P3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "equipos")
@Getter
@Setter
@NoArgsConstructor
public class Equipos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String ciudad;
    @Column(length = 80)
    private String estadio;
    @Column(nullable = false)
    private LocalDate fechaFundacion;
    @Column(nullable = false)
    private Integer numeroJugadores;
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal cuotaMensual;
    @Column(nullable = false)
    private boolean buscaJugadores;
}
