package modelo.entidades;

import java.util.Date;

public class Votacion {

    String id;
    Date fechaInicio;
    Date fechaCierre;
    int estado;

    public Votacion() {
        this.id = "";
        this.fechaInicio = new Date();
        this.fechaCierre = new Date();
        this.estado = 0;
    }

    public Votacion(String id, Date fechaInicio, Date fechaCierre, int estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaCierre() {
        return this.fechaCierre;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }
}
