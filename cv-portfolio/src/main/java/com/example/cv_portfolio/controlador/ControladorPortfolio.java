package com.example.cv_portfolio.controlador;

import com.example.cv_portfolio.modelos.Proyecto;
import jakarta.validation.constraints.Max;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPortfolio {
    @GetMapping("/portfolio")
    public String mostrarPortfolio(
            //Por defecto dejamos marcado todas, para que salgan los proyectos en nuestra primera vista.
            @RequestParam(defaultValue = "todas")String categoria,
            //Lo poenmos como texto para convertirlo y controlar los errores que puedan haber.
            @RequestParam(defaultValue = "") String anio,
            Model model){
        //Los proyectos se crearian cada peticion por una variable local, y no guardo daros en atributos del controlador.
        List<Proyecto> proyectos=crearProyectos();
        //Si estuviera vacio y marcando algun error no filtrariamos por año.
        Integer anioNumero= null;
        boolean anioInvalido = false;
        if(!anio.isBlank()){
            try {
                anioNumero = Integer.parseInt(anio.trim());
            }catch (NumberFormatException e){
                anioInvalido=true;
            }
        }
        List<Proyecto> resultado = filtrarProyectos (proyectos, categoria, anioNumero);
        model.addAttribute("proyectos", resultado);
        model.addAttribute("total", resultado.size());
        model.addAttribute("categoria", categoria);
        model.addAttribute("anio", anio);
        model.addAttribute("anioInvalido", anioInvalido);
        return "portfolio";
    }
    //Si hay categoria y año a la vez, el proyecto tiene que cumplir con los dos requisitos.
    List<Proyecto> filtrarProyectos(List<Proyecto> proyectos, String categoria, Integer anio){
       List<Proyecto> resultado = new ArrayList<>();
       for (Proyecto p : proyectos){
           boolean categoriaOK = categoria == null || categoria.equals("todas") || p.getCategoria().equals(categoria);
           boolean anioOk = anio == null || p.getAnio() == anio;
           if (categoriaOK && anioOk){
               resultado.add(p);
           }
       }
       return resultado;
    }
    //Aqui hariamos como hicimos con el controlador de curriculum, creamos los datos mediante una lista de proyectos y los datos fijos seria las categorias.
    private List<Proyecto> crearProyectos(){
        List<Proyecto> lista = new ArrayList<>();
        lista.add(new Proyecto("P5-P6", 2026, "aplicaciones", "Finalizado", new String[]{"Java", "Spring Boot", "Thymeleaf"}, "Un proyecto con uso de las nuevas tecnologias aprendidas en clases.", "https://github.com/Ismael-Abdellatifi/EntornoServidor2/tree/main/entregaP5_P6", "/img/spring.png"));
        lista.add(new Proyecto("Excusas_JBDC1", 2026, "aplicaciones", "Finalizado", new String[]{"Java", "JDBC", "Maven"}, "Un proyecto creado para poder conectar con una base de datos", "https://github.com/Ismael-Abdellatifi/EntornoServidor2/tree/main/excusas_jdbc1", "/img/base.png"));
        lista.add(new Proyecto("Carnaval de Cadiz", 2025, "sitios", "Finalizado", new String[]{"HTML", "CSS", "Bootstrap"}, "Una pagina web dedicada al Carnaval de Cadiz", "https://github.com/Ismael-Abdellatifi/Lenguaje_Marcas/tree/main/Carnaval%20de%20Cadiz", "/img/carnaval.png"));
    return lista;
    }
}
