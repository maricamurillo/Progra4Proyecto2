package modelo.entidades;

public class Candidato {

    String cedula;
    String nombre;
    String apellido1;
    String apellido2;
    String foto;
    int estado;

    public Candidato() {
        this.cedula = "";
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.foto = "";
        this.estado = 0;
    }

    public Candidato(String cedula, String nombre, String apellido1, String apellido2, String foto, int estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.foto = foto;
        this.estado = estado;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return this.foto;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }
}
