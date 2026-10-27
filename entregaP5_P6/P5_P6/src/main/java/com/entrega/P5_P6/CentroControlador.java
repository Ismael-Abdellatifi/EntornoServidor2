package com.entrega.P5_P6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CentroControlador {
    //Declara una lista con los tipos de actividades disponibles.
    private static final List<String> ACTIVIDADES = List.of("taller", "conciertos", "expo");
    @GetMapping("/")
    //Este metodo nos sirve para responder con los gets y lo mostrara a la vista de inicio.
    public String inicio(Model model){
        return "inicio";
    }
    @GetMapping("/visita")
    //Este metodo nos sirve para responder con los gets y lo mostrara a la vista de visita.
    public String visita(){
        return "visita";
    }
}
