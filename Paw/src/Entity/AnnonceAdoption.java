/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;
import java.util.Date;

/**
 *
 * @author AYOUB
 */
public class AnnonceAdoption extends Annonce {
    private String typeAdoption ; //temporaire ou permanante
    private Date debutAdoption ;
    private Date finAdoption ;
    private String etatAdoption;
    
    //id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur, images

    public AnnonceAdoption(String typeAdoption, Date debutAdoption, Date finAdoption, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date, int id_utilisateur, File images,String etatAdoption) {
        super(id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur, images);
        this.typeAdoption = typeAdoption;
        this.debutAdoption = debutAdoption;
        this.finAdoption = finAdoption;
        this.etatAdoption = etatAdoption;
    }

    public String getTypeAdoption() {
        return typeAdoption;
    }

    public void setTypeAdoption(String typeAdoption) {
        this.typeAdoption = typeAdoption;
    }

    public Date getDebutAdoption() {
        return debutAdoption;
    }

    public void setDebutAdoption(Date debutAdoption) {
        this.debutAdoption = debutAdoption;
    }

    public Date getFinAdoption() {
        return finAdoption;
    }

    public void setFinAdoption(Date finAdoption) {
        this.finAdoption = finAdoption;
    }

    public String getEtatAoption() {
        return etatAdoption;
    }

    public void setEtatAoption(String etatAoption) {
        this.etatAdoption = etatAoption;
    }
    
    
}
