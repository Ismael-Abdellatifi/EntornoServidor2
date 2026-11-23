package com.example.U4P3.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EquipoFormulario {
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 60 caracteres")
    private String nombre;
    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad no puede superar los 50 caracteres")
    private String ciudad;
    @Size(max = 100, message = "El estadio no puede superar los 80 caracteres")
    private String estadio;
    @NotNull(message = "La fecha de fundacion es obligatoria")
    @PastOrPresent(message = "La fecha de fundacion no puede ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFundacion;
    @NotNull(message = "Indica el numero de jugadores")
    @Min(value = 11, message = "Un equipo necesita al menos 11 juadores")
    @Max(value = 30, message = "La plantilla no puede superar los 30 jugadores")
    private Integer numeroJugadores;
    @NotNull(message = "Indica la cuota mensual")
    @DecimalMin(value = "0.00", message = "La cuota no puede ser negativa")
    @DecimalMax(value = "200.00", message = "La cuota mensual no puede superar 200")
    @Digits(integer = 3, fraction = 2, message = "Usa como maximo 2 decimales")
    private BigDecimal cuotaMensual;

    private boolean buscaJugadores = false;
}
