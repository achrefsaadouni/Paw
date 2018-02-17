/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author AYOUB
 */
public class Connexion {
    private int id;
    private int id_utilisateur;
    private Date date;

    public Connexion(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Connexion(int id, int id_utilisateur, Date date) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
