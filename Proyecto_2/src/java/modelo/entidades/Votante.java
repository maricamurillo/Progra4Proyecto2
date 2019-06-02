package modelo.entidades;

public class Votante {

    String cedula;
    String nombre;
    String apellido1;
    String apellido2;
    String clave;
    boolean cambioClave;
    int estado;

    public Votante() {
        this.cedula = "";
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.clave = "";
        this.cambioClave = false;
        this.estado = 0;
    }

    public Votante(String cedula, String nombre, String apellido1, String apellido2, String clave, boolean cambioClave, int estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.clave = clave;
        this.cambioClave = cambioClave;
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

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return this.clave;
    }
    
    public void setCambioClave(boolean cambioClave) {
        this.cambioClave = cambioClave;
    }

    public boolean getCambioClave() {
        return this.cambioClave;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }
}
