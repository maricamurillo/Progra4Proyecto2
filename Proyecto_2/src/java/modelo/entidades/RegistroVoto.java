package modelo.entidades;

import java.util.Date;

public class RegistroVoto {

    Votacion votacion;
    Partido partido;
    Usuario votante;
    Date fechaVoto;
    int estado;
    
    public RegistroVoto(){
        this.votacion = new Votacion();
        this.partido = new Partido();
        this.votante = new Usuario();
        this.fechaVoto = new Date();
        this.estado = 0;
    }
    
    public RegistroVoto(Votacion votacion, Partido partido, Usuario votante, Date fechaVoto, int estado)
    {
        this.votacion = votacion;
        this.partido = partido;
        this.votante = votante;
        this.fechaVoto = fechaVoto;
        this.estado = estado;
    }
    
    public void setVotacion(Votacion votacion) {
        this.votacion = votacion;
    }

    public Votacion getVotacion() {
        return this.votacion;
    }
    
    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Partido getPartido() {
        return this.partido;
    }
    
    public void setVotante(Usuario votante) {
        this.votante = votante;
    }

    public Usuario getVotante() {
        return this.votante;
    }
    
    public void setFechaVoto(Date fechaVoto) {
        this.fechaVoto = fechaVoto;
    }

    public Date getFechaVoto() {
        return this.fechaVoto;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }
}
