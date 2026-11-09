package com.example.cv_portfolio.modelos;

public class Proyecto {
    private String titulo;
    private int anio;
    private String categoria;
    private String estado;
    private String[] tecnologias;
    private String descripcion;
    private String enlace;
    private String imagen;

    public Proyecto(String titulo, int anio, String categoria, String estado, String[] tecnologias, String descripcion, String enlace, String imagen) {
        this.titulo = titulo;
        this.anio = anio;
        this.categoria = categoria;
        this.estado = estado;
        this.tecnologias = tecnologias;
        this.descripcion = descripcion;
        this.enlace = enlace;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String[] getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String[] tecnologias) {
        this.tecnologias = tecnologias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
