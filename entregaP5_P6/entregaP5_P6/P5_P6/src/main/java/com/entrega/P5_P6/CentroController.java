package com.entrega.P5_P6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CentroController {
    //Lista de actividades disponibles
    private static final List<String> Actividades = List.of("taller", "concierto", "expo");
    @GetMapping("/")
    //Gestiona las peticiones get para las paginas principales.
    public String inicio(Model model){
        //Envia la lsita de actividades a la vista para poder mostrarlas.
        model.addAttribute("actividades", Actividades);
        return "inicio";
    }
    @GetMapping("/visita")
    //Gestiona las peticiones get para la pagina visita.
    public String visita(){
        return "visita";
    }
}
