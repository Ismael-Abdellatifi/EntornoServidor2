package com.example.cv_portfolio.modelos;

public class Estudio {
    private String nombre;
    private String centro;
    private Integer aniosCursados;


    public Estudio(String nombre, String centro, Integer aniosCursados) {
        this.nombre = nombre;
        this.centro = centro;
        this.aniosCursados = aniosCursados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public Integer getAniosCursados() {
        return aniosCursados;
    }

    public void setAniosCursados(Integer aniosCursados) {
        this.aniosCursados = aniosCursados;
    }
}
