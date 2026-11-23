package com.example.U4P3.controlador;

import com.example.U4P3.dto.EquipoFormulario;
import com.example.U4P3.servicios.EquipoServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// Controlador: recibe las peticiones, llama al servicio y elige la vista
@Controller
public class ControladorEquipo {

    private final EquipoServicio equipoServicio;

    // Inyección de dependencias por constructor
    public ControladorEquipo(EquipoServicio equipoServicio) {
        this.equipoServicio = equipoServicio;
    }

    // Muestra el listado y el formulario vacío
    @GetMapping("/")
    public String listado(Model model) {
        model.addAttribute("equipos", equipoServicio.listarTodos());
        model.addAttribute("equiposFormulario", new EquipoFormulario());
        return "index";
    }

    // Guarda el equipo si los datos son válidos
    @PostMapping("/equipos")
    public String registrar(@Valid @ModelAttribute("equiposFormulario") EquipoFormulario formulario,
                            BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("equipos", equipoServicio.listarTodos());
            return "index";
        }
        equipoServicio.registrar(formulario);
        return "redirect:/";
    }
}