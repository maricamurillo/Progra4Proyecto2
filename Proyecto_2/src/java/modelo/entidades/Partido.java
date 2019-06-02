package modelo.entidades;

public class Partido {

    String id;
    String nombre;
    String siglas;
    String bandera;
    String observaciones;
    Candidato candidato;
    int estado;

    public Partido() {
        this.id = "";
        this.nombre = "";
        this.siglas = "";
        this.bandera = "";
        this.observaciones = "";
        this.candidato = new Candidato();
        this.estado = 0;
    }

    public Partido(String id, String nombre, String siglas, String bandera, String observaciones, Candidato candidato, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.siglas = siglas;
        this.bandera = bandera;
        this.observaciones = observaciones;
        this.candidato = candidato;
        this.estado = estado;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }   

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getSiglas() {
        return this.siglas;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getBandera() {
        return this.bandera;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Candidato getCandidato() {
        return this.candidato;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }
}