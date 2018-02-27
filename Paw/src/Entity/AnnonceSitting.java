/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import javafx.util.converter.LongStringConverter;



/**
 *
 * @author Lenovo
 */
public class AnnonceSitting extends Annonce{
    private Date dateSit; 
    private int dureSit;
    private String toDoList;
    private String typePet;

    public AnnonceSitting(Date dateSit, int dureSit, String toDoList, String typePet, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, java.util.Date date, int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.toDoList = toDoList;
        this.typePet = typePet;
    }

    

    public Date getDateSit() {
        return dateSit;
    }

    public int getDureSit() {
        return dureSit;
    }

    public String getToDoList() {
        return toDoList;
    }

    public String getTypePet() {
        return typePet;
    }

    public void setDateSit(Date dateSit) {
        this.dateSit = dateSit;
    }

    public void setDureSit(int dureSit) {
        this.dureSit = dureSit;
    }

    public void setToDoList(String toDoList) {
        this.toDoList = toDoList;
    }

    public void setTypePet(String typePet) {
        this.typePet = typePet;
    }

      
}