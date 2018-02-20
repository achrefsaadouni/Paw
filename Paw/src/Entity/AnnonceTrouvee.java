/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Guideinfo
 */
public class AnnonceTrouvee extends Annonce {
    
    private String colier ; 
    private Date date_trouvee ; 
    private String lieu_trouve ; 
    
    

    
    public AnnonceTrouvee(String colier, Date date_trouvee, String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date,int id_utilisateur,File images) {
        super(id, age, couleur, sex, race, message_complementaire, type, date,id_utilisateur,images);
        this.colier = colier;
        this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }
 public AnnonceTrouvee(String colier, Date date_trouvee, String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date,int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, date,id_utilisateur);
        this.colier = colier;
        this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }

    public AnnonceTrouvee(String colier, Date date_trouvee, String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.colier = colier;
        this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }


     

    public String getColier() {
        return colier;
    }

    public void setColier(String colier) {
        this.colier = colier;
    }

    public Date getDate_trouvee() {
        return date_trouvee;
    }

    public void setDate_trouvee(Date date_trouvee) {
        this.date_trouvee = date_trouvee;
    }

    public String getLieu_trouve() {
        return lieu_trouve;
    }

    public void setLieu_trouve(String lieu_trouve) {
        this.lieu_trouve = lieu_trouve;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.colier);
        hash = 61 * hash + Objects.hashCode(this.date_trouvee);
        hash = 61 * hash + Objects.hashCode(this.lieu_trouve);
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
        final AnnonceTrouvee other = (AnnonceTrouvee) obj;
        if (!Objects.equals(this.colier, other.colier)) {
            return false;
        }
        if (!Objects.equals(this.lieu_trouve, other.lieu_trouve)) {
            return false;
        }
        if (!Objects.equals(this.date_trouvee, other.date_trouvee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnnonceTrouvee{" +super.toString()+  "colier=" + colier + ", date_trouvee=" + date_trouvee + ", lieu_perdu=" + lieu_trouve + '}';
    }


    
    
    
    
    
}
