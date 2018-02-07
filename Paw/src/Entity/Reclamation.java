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
public class Reclamation {
    private int id;
    private int utilisateur;
    private String objet; 
    private String text;
    private String type; //remerciment ou reclamation
    private Date date;

    public Reclamation(int utilisateur, String objet, String text, String type) {
        this.utilisateur = utilisateur;
        this.objet = objet;
        this.text = text;
        this.type = type;
    }

    public Reclamation(int id, int utilisateur, String objet, String text, String type, Date date) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.objet = objet;
        this.text = text;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
