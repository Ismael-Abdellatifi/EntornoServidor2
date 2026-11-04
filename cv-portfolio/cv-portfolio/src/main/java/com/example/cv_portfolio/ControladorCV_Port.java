package com.example.cv_portfolio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCV_Port {
    @Value("${cv.nombre}")
    private String nombre;

    @GetMapping("/curriculum")
    public String mostrarCurriculum(Model model) {
        Habilidad[] habilidades = {
                new Habilidad("Windows", "Medio"),
                new Habilidad("Linux", "Medio"),
                new Habilidad("Java", "Medio"),
                new Habilidad("SQL", "Medio"),
                new Habilidad("HTML Y CSS", "Medio"),
                new Habilidad("Spring Boot", "Medio"),
                new Habilidad("JavaScript", "Medio"),
                new Habilidad("GitHub", "Medio"),
        };

        Estudio[] estudios = {
                new Estudio("DAW", "IES.VELAZQUEZ", null),
                new Estudio("SMR", "IES.RAMON Y CAJAL", 2025),
                new Estudio("IPC", "IES.HELIOPOLIS", 2021),
                new Estudio("ESO", "IES.MURILLO", 2018)
        };

        ExperienciaLaboral[] experencias = {
                new ExperienciaLaboral("Grupo FA", "Auxiliar de Seguridad", "2023-Actualidad"),
                new ExperienciaLaboral("JJNS Servicios", "Auxiliar de Seguridad", "2026"),
                new ExperienciaLaboral("FCT-SMR", "Tecnico de Ordenadores", "2024"),
                new ExperienciaLaboral("Unium Servicios", "Auxiliar de Seguridad", "2024"),
                new ExperienciaLaboral("Burger King", "Personal de Equipo", "2023"),
                new ExperienciaLaboral("Sitel", "Teleoperador de Atencion y Ventas", "2022"),
                new ExperienciaLaboral("FCT-IPC-Industrias Lekue", "Operario de Almacen y Atencion al Publico", "2021")
        };
        Idioma[] idiomas = {
                new Idioma("Ingles", "basico"),
                new Idioma("Frances", "basico"),
                new Idioma("Español", "nativo")

        };
        model.addAttribute("nombre", nombre);
        model.addAttribute("titular", "Estudiante de DAW");
        model.addAttribute("presentacion", "Estudiante de DAW con experencia en emsamblar equipos.Pero me interesa mucho el desarollo.");
        model.addAttribute("contacto", "ismaelabca@gmail.com");
        model.addAttribute("habilidad", habilidades);
        model.addAttribute("estudio", estudios);
        model.addAttribute("experiencia", experencias);
        model.addAttribute("idioma", idiomas);


        return "curriculum";
    }

}
