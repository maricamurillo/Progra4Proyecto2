/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

/**
 *
 * @author Rodrigo
 */
public class Votar {
    public Votar(String siglas, String paritdo, String usuartio, String cedula, int votos) {
        this.siglas = siglas;
        this.paritdo = paritdo;
        this.usuartio = usuartio;
        this.cedula = cedula;
        this.votos = votos;
    }
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public Votar() {
    }
    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getParitdo() {
        return paritdo;
    }

    public void setParitdo(String paritdo) {
        this.paritdo = paritdo;
    }

    public String getUsuartio() {
        return usuartio;
    }

    public void setUsuartio(String usuartio) {
        this.usuartio = usuartio;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
    
    String siglas;
    String paritdo;
    String usuartio;
    String cedula;
    int votos;
    
}
