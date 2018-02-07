/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Guideinfo
 */

public class Notification {
    private int id;
    private int id_destinataire;
    private int id_emetteur;
    private String titre;
    private String text;
    private String type;
    private Date date;
    private String etat;

    public Notification(int id, int id_destinataire, int id_emetteur, String titre, String text, String type, Date date, String e) {
        this.id = id;
        this.id_destinataire = id_destinataire;
        this.id_emetteur = id_emetteur;
        this.titre = titre;
        this.text = text;
        this.type = type;
        this.date = date;
        this.etat=e;
    }

    public Notification(int id, int id_destinataire, int id_emetteur, String titre, String text, String type) {
        this.id = id;
        this.id_destinataire = id_destinataire;
        this.id_emetteur = id_emetteur;
        this.titre = titre;
        this.text = text;
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_destinataire() {
        return id_destinataire;
    }

    public void setId_destinataire(int id_destinataire) {
        this.id_destinataire = id_destinataire;
    }

    public int getId_emetteur() {
        return id_emetteur;
    }

    public void setId_emetteur(int id_emetteur) {
        this.id_emetteur = id_emetteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
