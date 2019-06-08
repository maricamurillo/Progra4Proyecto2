package modelo.entidades;

public class VotacionPartido {

    Votacion votacion;
    Partido partido;
    Usuario candidato;
    String fotoCandidato;
    String tipoImagen;
    int votosObtenidos;

    public VotacionPartido() {
        this.votacion = new Votacion();
        this.partido = new Partido();
        this.candidato = new Usuario();
        this.fotoCandidato = "";
        this.tipoImagen = "";
        this.votosObtenidos = 0;
    }

    public VotacionPartido(Votacion votacion, Partido partido, Usuario candidato, String fotoCandidato, String tipoImagen, int votosObtenidos) {
        this.votacion = votacion;
        this.partido = partido;
        this.candidato = candidato;
        this.fotoCandidato = fotoCandidato;
        this.tipoImagen = tipoImagen;
        this.votosObtenidos = votosObtenidos;
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
    
    public void setCandidato(Usuario candidato) {
        this.candidato = candidato;
    }

    public Usuario getCandidato() {
        return this.candidato;
    }
    
    public void setFotoCandidato(String fotoCandidato) {
        this.fotoCandidato = fotoCandidato;
    }

    public String getFotoCandidato() {
        return this.fotoCandidato;
    }
    
    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String getTipoImagen() {
        return this.tipoImagen;
    }
    
    public void setVotosObtenidos(int votosObtenidos) {
        this.votosObtenidos = votosObtenidos;
    }

    public int getVotosObtenidos() {
        return this.votosObtenidos;
    }
}
