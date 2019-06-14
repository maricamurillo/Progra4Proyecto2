package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "votacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Votacion implements Serializable {

    int id;
    Date fechaInicio;
    Date fechaApertura;
    Date fechaCierre;
    Date fechaFinal;
    int estado;

    public Votacion() {
        this.id = 0;
        this.fechaInicio = new Date();
        this.fechaApertura = new Date();
        this.fechaFinal = new Date();
        this.fechaCierre = new Date();
        this.estado = 0;
    }

    public Votacion(int id, Date fechaInicio, Date fechaApertura, Date fechaCierre, Date fechaFinal, int estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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
