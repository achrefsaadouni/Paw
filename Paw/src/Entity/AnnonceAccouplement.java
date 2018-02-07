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
 * @author gmehd
 */
public class AnnonceAccouplement extends Annonce {
    private String type_poil  ; 
    private String vaccin ; 
    private String dossier ;


    public AnnonceAccouplement(String type_poil, String vaccin, String dossier, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.type_poil = type_poil;
        this.vaccin = vaccin;
        this.dossier = dossier;
    }

    public String getType_poil() {
        return type_poil;
    }

    public void setType_poil(String type_poil) {
        this.type_poil = type_poil;
    }

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.type_poil);
        hash = 59 * hash + Objects.hashCode(this.vaccin);
        hash = 59 * hash + Objects.hashCode(this.dossier);
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
        final AnnonceAccouplement other = (AnnonceAccouplement) obj;
        if (!Objects.equals(this.type_poil, other.type_poil)) {
            return false;
        }
        if (!Objects.equals(this.vaccin, other.vaccin)) {
            return false;
        }
        if (!Objects.equals(this.dossier, other.dossier)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accouplement{" + "type_poil=" + type_poil + ", vaccin=" + vaccin + ", dossier=" + dossier + '}';
    }
    
    
    
    
}
