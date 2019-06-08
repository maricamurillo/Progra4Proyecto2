package modelo.entidades;

public class Partido {

    String siglas;
    String nombre;
    String bandera;
    String tipoImagen;
    String observaciones;

    public Partido() {
        this.siglas = "";
        this.nombre = "";
        this.bandera = "";
        this.tipoImagen = "";
        this.observaciones = "";
    }

    public Partido(String siglas, String nombre, String bandera, String tipoImagen, String observaciones) {
        this.siglas = siglas;
        this.nombre = nombre;
        this.bandera = bandera;
        this.tipoImagen = tipoImagen;
        this.observaciones = observaciones;
    }
    
    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getSiglas() {
        return this.siglas;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getBandera() {
        return this.bandera;
    }
    
    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String getTipoImagen() {
        return this.tipoImagen;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return this.observaciones;
    }
}