package modelo.entidades;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Serializable {

    String cedula;
    String nombre;
    String apellido1;
    String apellido2;
    String clave;
    int estado;

    public Usuario() {
        this.cedula = "";
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.clave = "";
        this.estado = 0;
    }

    public Usuario(String cedula, String nombre, String apellido1, String apellido2, String clave, int estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.clave = clave;
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

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }
}
