/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Guideinfo
 */
public class AnnoncePerdu extends Annonce
{
 private String colier  ; 
 private Date date_perte ; 

    public AnnoncePerdu() {
      
    }

    public AnnoncePerdu(String colier, Date date_perte, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.colier = colier;
        this.date_perte = date_perte;
    }

    public String getColier() {
        return colier;
    }

    public void setColier(String colier) {
        this.colier = colier;
    }

    public Date getDate_perte() {
        return date_perte;
    }

    public void setDate_perte(Date date_perte) {
        this.date_perte = date_perte;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.colier);
        hash = 13 * hash + Objects.hashCode(this.date_perte);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnnoncePerdu other = (AnnoncePerdu) obj;
        if (!Objects.equals(this.colier, other.colier)) {
            return false;
        }
        if (!Objects.equals(this.date_perte, other.date_perte)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnnoncePerdu{" +super.toString() +"colier=" + colier + ", date_perte=" + date_perte + '}';
    }
 
    
    
 
 
 
}
