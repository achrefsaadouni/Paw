/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AnnonceTraining  extends Annonce{
    private Date dateTr;
    private String typeTr ;
    private String typePet ;
    private String nomPet ;
    
    //private enum typeTr {
    //PuppyTr,BeginnerTr, AdvancedTr
    //}

    public AnnonceTraining() {
    }

    public AnnonceTraining(Date dateTr, String typeTr, String typePet, String nomPet, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.dateTr = dateTr;
        this.typeTr = typeTr;
        this.typePet = typePet;
        this.nomPet = nomPet;
    }

    

    public Date getDateTr() {
        return dateTr;
    }

    public String getTypeTr() {
        return typeTr;
    }

    public String getTypePet() {
        return typePet;
    }

    public String getNomPet() {
        return nomPet;
    }

    public void setDateTr(Date dateTr) {
        this.dateTr = dateTr;
    }

    public void setTypeTr(String typeTr) {
        this.typeTr = typeTr;
    }

    public void setTypePet(String typePet) {
        this.typePet = typePet;
    }

    public void setNomPet(String nomPet) {
        this.nomPet = nomPet;
    }

    
    

    
}
