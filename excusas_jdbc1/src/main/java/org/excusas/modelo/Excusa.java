package org.excusas.modelo;

import java.time.LocalDate;

public class Excusa {
    private int id;
    private String alumno;
    private String curso;
    private String excusa;
    private int diaRetraso;
    private int credibilidad;
    private LocalDate fechaEntrega;
    private boolean aprobadaPorProfesor;
    private int nivel_Drama;

    public Excusa(int id, String alumno, String curso, String excusa, int diaRetraso, int credibilidad, LocalDate fechaEntrega, boolean aprobadaPorProfesor, int nivelDrama) {
        this.id = id;
        this.alumno = alumno;
        this.curso = curso;
        this.excusa = excusa;
        this.diaRetraso = diaRetraso;
        this.credibilidad = credibilidad;
        this.fechaEntrega = fechaEntrega;
        this.aprobadaPorProfesor = aprobadaPorProfesor;
        this.nivel_Drama = nivelDrama;
    }

    public Excusa() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getExcusa() {
        return excusa;
    }

    public void setExcusa(String excusa) {
        this.excusa = excusa;
    }

    public int getDiaRetraso() {
        return diaRetraso;
    }

    public void setDiaRetraso(int diaRetraso) {
        this.diaRetraso = diaRetraso;
    }

    public int getCredibilidad() {
        return credibilidad;
    }

    public void setCredibilidad(int credibilidad) {
        this.credibilidad = credibilidad;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public boolean isAprobadaPorProfesor() {
        return aprobadaPorProfesor;
    }

    public void setAprobadaPorProfesor(boolean aprobadaPorProfesor) {
        this.aprobadaPorProfesor = aprobadaPorProfesor;
    }

    public int getNivelDrama() {
        return nivel_Drama;
    }

    public void setNivelDrama(int nivelDrama) {
        this.nivel_Drama = nivelDrama;
    }

    @Override
    public String toString() {
        return "Excusa{" +
                "id=" + id +
                ", alumno='" + alumno + '\'' +
                ", curso='" + curso + '\'' +
                ", excusa='" + excusa + '\'' +
                ", diaRetraso=" + diaRetraso +
                ", credibilidad=" + credibilidad +
                ", fechaEntrega=" + fechaEntrega +
                ", aprobadaPorProfesor=" + aprobadaPorProfesor +
                ", nivelDrama=" + nivel_Drama +
                '}';
    }
}
