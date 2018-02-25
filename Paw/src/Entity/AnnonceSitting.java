/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;



/**
 *
 * @author Lenovo
 */
public class AnnonceSitting extends Annonce{
    private Date dateSit; 
    private String typeSit;

    public AnnonceSitting() {
    }
    
    public AnnonceSitting(Date date_sit, String typeSit, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
        this.dateSit = date_sit;
        this.typeSit = typeSit;
    }

    public AnnonceSitting(int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        super(id, age, couleur, sex, race, message_complementaire, type, date);
    }

  

    

    public Date getDateSit() {
        return dateSit;
    }

    public String getTypeSit() {
        return typeSit;
    }

    public void setDate_sit(Date date_sit) {
        this.dateSit = date_sit;
    }

    public void setTypeSit(String typeSit) {
        this.typeSit = typeSit;
    }

    

}

