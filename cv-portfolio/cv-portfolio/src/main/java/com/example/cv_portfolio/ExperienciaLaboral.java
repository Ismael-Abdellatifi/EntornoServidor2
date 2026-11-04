package com.example.cv_portfolio;

public class ExperienciaLaboral {
    private String nombreEmpresa;
    private String puesto;
    private String periodo;

    public ExperienciaLaboral(String nombreEmpresa, String puesto, String periodo) {
        this.nombreEmpresa = nombreEmpresa;
        this.puesto = puesto;
        this.periodo = periodo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
