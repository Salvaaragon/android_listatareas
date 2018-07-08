package com.salvaaragon.rememberapp;

/**
 * @author Salva Aragón
 */
public class Task {

    private String nombre;
    private String fecha;
    private String desc;

    public Task() {}

    public Task(String sNombre, String sFecha, String sDesc) {
        this.nombre = sNombre;
        this.fecha = sFecha;
        this.desc = sDesc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return desc;
    }

    public void setNombre(String sNombre) {
        this.nombre = sNombre;
    }

    public void setFecha(String sFecha) {
        this.fecha = sFecha;
    }

    public void setDescripcion(String sDesc) {
        this.desc = sDesc;
    }
}
