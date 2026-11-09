package com.example.cv_portfolio.controlador;

import com.example.cv_portfolio.modelos.FormularioCV;
import com.example.cv_portfolio.modelos.ProyectoForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorEditor {
    //El maximo de proyectos lo leo de application.properties, para poder cambiarlo sin tocar codigo.
    @Value("${portfolio.max-proyectos}")
    private int maxProyectos;

    @GetMapping("/portfolio/editar")
    public String mostrarFormulario(Model model) {
        //Creo el formulario nuevo en cada peticon en variable local.
        FormularioCV formulario = new FormularioCV();
        //Empieza con una fila vacia para poder editar.
        formulario.getProyectos().add(new ProyectoForm());
        model.addAttribute("formulario", formulario);
        model.addAttribute("maxProyectos", maxProyectos);
        return "editar";
    }

    @PostMapping("/portfolio/editar")
    public String procesarFormulario(
            //Rellena el objeto con los campos que llegan al formulario.
            @ModelAttribute("formulario") FormularioCV formulario,
            // Nos sirve para guardar los errores del formulario.
            BindingResult resultado,
            //Estos parametros se utilizan si se pulso correctamente los botones que tenemos en la vista.
            @RequestParam(required = false) String anadir,
            @RequestParam(required = false) String quitar,
            Model model) {
        model.addAttribute("maxProyectos", maxProyectos);
        //Vuelve al formulario, sin tener que validar-
        if (anadir != null || quitar != null) {
            anadirOQuitarFila(formulario, anadir, quitar);
            model.addAttribute("maxProyectos", maxProyectos);
            return "editar";
        }
        //Si no son ninguno de los botones anteriores, seria generar vista previa t se validaria y si hay algun error te lo devuelve si hay en cada campo.
        validar(formulario, resultado);
        if (resultado.hasErrors()){
            return "editar";
        }
        //Las filas vacias se descartan despues de validar, para que los errores coindican.
        List<ProyectoForm> proyecto = new ArrayList<>();
        for (ProyectoForm p : formulario.getProyectos()){
            if (!filaVacia(p)){
                proyecto.add(p);
            }
        }
        model.addAttribute("proyectos", proyecto);
        return "vista-previa";
    }
    //
    private void anadirOQuitarFila(FormularioCV formulario, String anadir, String quitar) {
        List<ProyectoForm> filas = formulario.getProyectos();
        if (anadir != null) {
            //Aqui seria el limite , en el servidor ya que el formulario se puede manipular desde fuera.
            if (filas.size() < maxProyectos) {
                filas.add(new ProyectoForm());
            }
        } else if (quitar != null) {
            try {
                int indice = Integer.parseInt(quitar);
                if (indice >= 0 && indice < filas.size()) {
                    filas.remove(indice);
                }
                //Nos sirve para ignorar y no se quitaria fila.
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

        }
        //Siempre minimo con una fila visible para editar.
        if (filas.isEmpty()) {
            filas.add(new ProyectoForm());
        }
    }
    //Aqui se validan los datos personales en el formulario y los proyectos.
    private void validar(FormularioCV f, BindingResult resultado) {
        if (vacio(f.getNombre())) {
            resultado.rejectValue("nombre", "error.obligatorio");
        }
        if (vacio(f.getTitular())) {
            resultado.rejectValue("titular", "error.obligatorio");
        }
        if (vacio(f.getPresentacion())) {
            resultado.rejectValue("presentacion", "error.obligatorio");
        }
        //Aunque el correo sea opcional, si se rellena tiene que respetar este formato.
        if (!vacio(f.getEmail()) && !f.getEmail().trim().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            resultado.rejectValue("email", "error.email");
        }
        List<ProyectoForm> filas = f.getProyectos();
        for (int i = 0; i < filas.size(); i++) {
            ProyectoForm p = filas.get(i);
            //Una fila vacia no se valida, se ignoraria.
            if (filaVacia(p)) {
                continue;
            }
            //Si la fila tiene algo escrito, los cinco campos serian obligatorios.
            String base = "proyectos[" + i + "].";
            if (vacio(p.getTitulo())) {
                resultado.rejectValue(base + "titulo", "error.obligatorio");
            }
            if (vacio(p.getAnio())) {
                resultado.rejectValue(base + "anio", "error.obligatorio");
            } else {
                try {
                    Integer.parseInt(p.getAnio().trim());
                } catch (NumberFormatException e) {
                    resultado.rejectValue(base + "anio", "error.obligatorio");
                }
            }
            if (vacio(p.getCategoria())) {
                resultado.rejectValue(base + "categoria", "error.obligatorio");
            }
            if (vacio(p.getEstado())) {
                resultado.rejectValue(base + "estado", "error.obligatorio");
            }
            if (vacio(p.getDescripcion())) {
                resultado.rejectValue(base + "descripcion", "error.obligatorio");
            }
        }
    }
    //Contaria como vacio, si es null o tiene solo espacios.
    private boolean vacio(String texto){
    return texto == null || texto.isBlank();
    }
    //L fila esta vacia si no tiene ningun campo rellenado.
    private boolean filaVacia(ProyectoForm p){
        return vacio(p.getTitulo())&& vacio(p.getAnio()) && vacio(p.getCategoria()) && vacio(p.getEstado()) && vacio(p.getDescripcion());
    }
}
