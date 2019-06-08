package modelo.entidades;

public class VotacionUsuario {
    Votacion votacion;
    Usuario usuario;
    int votoCompletado;
    
    public VotacionUsuario(){
        this.votacion = new Votacion();
        this.usuario = new Usuario();
        this.votoCompletado = 0;
    }
    
    public VotacionUsuario(Votacion votacion, Usuario usuario, int votoCompletado){
        this.votacion = votacion;
        this.usuario = usuario;
        this.votoCompletado = votoCompletado;
    }
    
    public void setVotacion(Votacion votacion){
        this.votacion = votacion;
    }
    
    public Votacion getVotacion(){
        return this.votacion;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void setVotoCompletado(int votoCompletado){
        this.votoCompletado = votoCompletado;
    }
    
    public int getVotoCompletado(){
        return this.votoCompletado;
    }
}
