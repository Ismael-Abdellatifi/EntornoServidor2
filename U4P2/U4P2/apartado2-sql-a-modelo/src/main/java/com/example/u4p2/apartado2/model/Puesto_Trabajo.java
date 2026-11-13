package com.example.u4p2.apartado2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Puesto_Trabajo")
@Getter
@Setter
@NoArgsConstructor
public class Puesto_Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_puesto", length = 10, unique = true)
    private String codigo_puesto;
}
