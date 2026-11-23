package com.example.U4P3.servicios;

import com.example.U4P3.dto.EquipoFormulario;
import com.example.U4P3.model.Equipos;
import com.example.U4P3.repositorio.EquipoRepositorio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServicio {
    private final EquipoRepositorio equipoRepositorio;
    public EquipoServicio(EquipoRepositorio equipoRepositorio){
        this.equipoRepositorio = equipoRepositorio;
    }
    @Transactional(readOnly = true)
    public List<Equipos> listarTodos(){
        return equipoRepositorio.findAllByOrderByNombreAsc();
    }
    @Transactional(readOnly = true)
    public List <Equipos> listarQueBuscanJugadores(){
        return equipoRepositorio.buscarQueBuscanJugadores();
    }
    @Transactional(readOnly = true)
    public List<Equipos> listarSinCuota(){
        return equipoRepositorio.buscarSinCuota();
    }
    @Transactional
    public Equipos registrar(EquipoFormulario formulario){
        Equipos equipos = new Equipos();
        equipos.setNombre(formulario.getNombre().trim());
        equipos.setCiudad(formulario.getCiudad().trim());
        String estadio = formulario.getEstadio();
        equipos.setEstadio(estadio == null || estadio.isBlank()? null : estadio.trim());
        equipos.setFechaFundacion(formulario.getFechaFundacion());
        equipos.setNumeroJugadores(formulario.getNumeroJugadores());
        equipos.setCuotaMensual(formulario.getCuotaMensual());
        equipos.setBuscaJugadores(formulario.isBuscaJugadores());
        return equipoRepositorio.save(equipos);
    }
}
