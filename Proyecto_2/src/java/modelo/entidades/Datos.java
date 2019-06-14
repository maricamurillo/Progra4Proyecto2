package modelo.entidades;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "datos")
@XmlAccessorType(XmlAccessType.FIELD)

public class Datos {

    @XmlElement(name = "usuario")
    private List<Usuario> usuarios;

    @XmlElement(name = "partido")
    private List<Partido> partidos;

    @XmlElement(name = "votacion")
    private List<Votacion> votaciones;

    @XmlElement(name = "administrador")
    private List<Administrador> administradores;

    @XmlElement(name = "votacionusuario")
    private List<VotacionUsuario> votacionUsuario;

    @XmlElement(name = "votacionpartido")
    private List<VotacionPartido> votacionPartido;

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Votacion> getVotaciones() {
        return votaciones;
    }

    public void setVotaciones(List<Votacion> votaciones) {
        this.votaciones = votaciones;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public List<VotacionUsuario> getVotacionUsuario() {
        return votacionUsuario;
    }

    public void setVotacionUsuario(List<VotacionUsuario> votacionUsuario) {
        this.votacionUsuario = votacionUsuario;
    }

    public List<VotacionPartido> getVotacionPartido() {
        return votacionPartido;
    }

    public void setVotacionPartido(List<VotacionPartido> votacionPartido) {
        this.votacionPartido = votacionPartido;
    }
}
