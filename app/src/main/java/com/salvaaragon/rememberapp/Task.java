package com.salvaaragon.rememberapp;

/**
 * @author Salva Aragón
 */
public class Task {

    private int id;
    private String nombre;
    private String fecha;
    private String desc;

    public Task() {}

    public Task(int id, String sNombre, String sFecha, String sDesc) {
        this.id = id;
        this.nombre = sNombre;
        this.fecha = sFecha;
        this.desc = sDesc;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
