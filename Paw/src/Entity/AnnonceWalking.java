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
public class AnnonceWalking extends Annonce{
    private Date dateWalk;
    private int dureWalk;

    public AnnonceWalking() {
    }

    public AnnonceWalking(Date dateWalk, int dureWalk, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.dateWalk = dateWalk;
        this.dureWalk = dureWalk;
    }

    public Date getDateWalk() {
        return dateWalk;
    }

    public int getDureWalk() {
        return dureWalk;
    }

    public void setDateWalk(Date date_walk) {
        this.dateWalk = dateWalk;
    }

    public void setDureWalk(int dure_walk) {
        this.dureWalk = dureWalk;
    }

   
    
    
}
