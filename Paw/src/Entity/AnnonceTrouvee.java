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
public class AnnonceTrouvee extends Annonce {
    
    private String colier ; 
    private Date date_trouvee ; 
    private String lieu_perdu ; 

    public AnnonceTrouvee(String colier, Date date_trouvee, String lieu_perdu, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.colier = colier;
        this.date_trouvee = date_trouvee;
        this.lieu_perdu = lieu_perdu;
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

    public String getLieu_perdu() {
        return lieu_perdu;
    }

    public void setLieu_perdu(String lieu_perdu) {
        this.lieu_perdu = lieu_perdu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.colier);
        hash = 61 * hash + Objects.hashCode(this.date_trouvee);
        hash = 61 * hash + Objects.hashCode(this.lieu_perdu);
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
        if (!Objects.equals(this.lieu_perdu, other.lieu_perdu)) {
            return false;
        }
        if (!Objects.equals(this.date_trouvee, other.date_trouvee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnnonceTrouvee{" +super.toString()+  "colier=" + colier + ", date_trouvee=" + date_trouvee + ", lieu_perdu=" + lieu_perdu + '}';
    }


    
    
    
    
    
}
