package com.example.cv_portfolio.controlador;

import com.example.cv_portfolio.modelos.Estudio;
import com.example.cv_portfolio.modelos.ExperienciaLaboral;
import com.example.cv_portfolio.modelos.Habilidad;
import com.example.cv_portfolio.modelos.Idioma;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCurriculum {
    //El nombre se lee desde appliaction.properties, para poder cambiarlo sin tocar codigo como tal.
    @Value("${cv.nombre}")
    private String nombre;

    @GetMapping("/curriculum")
    public String mostrarCurriculum(Model model) {
        //Creo los datos dentro del metodo y asi el controlador no guarda nada en la peticion.
        Habilidad[] habilidades = {
                new Habilidad("Windows", "alto"),
                new Habilidad("Linux", "alto"),
                new Habilidad("Java", "medio"),
                new Habilidad("SQL", "medio"),
                new Habilidad("HTML Y CSS", "medio"),
                new Habilidad("Spring Boot", "medio"),
                new Habilidad("JavaScript", "bajo"),
                new Habilidad("GitHub", "medio"),
        };
        //En los aniosCursados es null,significa que sigo estudiando en esa linea y en la vista se mostrara encurso envede su año de finalizacion.
        Estudio[] estudios = {
                new Estudio("DAW", "IES.VELAZQUEZ", null),
                new Estudio("SMR", "IES.RAMON Y CAJAL", 2025),
                new Estudio("IPC", "IES.HELIOPOLIS", 2021),
                new Estudio("ESO", "IES.MURILLO", 2018)
        };
        //Si dejara la ArrayList vacia , saldria y mensaje que busco mi primera experiencia laboral.
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
                new Idioma("Ingles", "medio"),
                new Idioma("Frances", "bajo"),
                new Idioma("Español", "alto")

        };
        //Añado todos los atributos al modelo y entrecomillados para que thymleaf los pueda leer correctamente y no de error.
        model.addAttribute("nombre", nombre);
        model.addAttribute("titular", "Estudiante de DAW");
        model.addAttribute("presentacion", "Estudiante de DAW con experencia en SMR, con montaje y mantenimiento de equipos informacitos ,Pero con muchas ganas de seguir aprendiendo Desarrolo Web.");
        model.addAttribute("contacto", "ismaelabca17@gmail.com");
        model.addAttribute("habilidad", habilidades);
        model.addAttribute("estudio", estudios);
        model.addAttribute("experiencia", experencias);
        model.addAttribute("idioma", idiomas);


        return "curriculum";
    }

}
