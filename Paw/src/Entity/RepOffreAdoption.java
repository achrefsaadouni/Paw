/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author AYOUB
 */
public class RepOffreAdoption {
    private int id ;
    private int id_annonce;
    private int id_utilisateur;
    private String etat ;   // Confirmée  ou  Non

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public RepOffreAdoption(int id, int id_annonce, int id_utilisateur, String etat) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.id_utilisateur = id_utilisateur;
        this.etat = etat;
    }
    
}
