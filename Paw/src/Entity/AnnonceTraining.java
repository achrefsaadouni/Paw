/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AnnonceTraining  extends Annonce{
    private Date dateTr;
    private int dureTr;
    private String typeTr ;
    //private enum typeTr {
    //PuppyTr,BeginnerTr, AdvancedTr
    //}

    public AnnonceTraining() {
    }

    public AnnonceTraining(Date dateTr, int dureTr, String typeTr, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.dateTr = dateTr;
        this.dureTr = dureTr;
        this.typeTr = typeTr;
    }

    public Date getDateTr() {
        return dateTr;
    }

    public int getDureTr() {
        return dureTr;
    }

    public String getTypeTr() {
        return typeTr;
    }

    public void setDateTr(Date dateTr) {
        this.dateTr = dateTr;
    }

    public void setDureTr(int dureTr) {
        this.dureTr = dureTr;
    }

    public void setTypeTr(String typeTr) {
        this.typeTr = typeTr;
    }
    
    

    
}
