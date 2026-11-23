package com.example.U4P3.repositorio;

import com.example.U4P3.model.Equipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepositorio extends JpaRepository<Equipos, Long> {
    List<Equipos> findAllByOrderByNombreAsc();

    @Query("SELECT e from Equipos e where e.buscaJugadores = true order by e.numeroJugadores asc, e.nombre asc")
    List<Equipos> buscarQueBuscanJugadores();
    @Query("SELECT e from Equipos e WHERE e.cuotaMensual = 0 order by e.nombre asc")
    List<Equipos> buscarSinCuota();
}

