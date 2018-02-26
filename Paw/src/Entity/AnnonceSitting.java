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
    private ArrayList<String> toDoList;

    public AnnonceSitting(Date dateSit, int dureSit, ArrayList<String> toDoList, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, java.util.Date date, int id_utilisateur, File images) {
        super(id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur, images);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.toDoList = toDoList;
    }

    public AnnonceSitting(Date dateSit, int dureSit, ArrayList<String> toDoList, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, java.util.Date date, int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.toDoList = toDoList;
    }

    public void setDateSit(Date dateSit) {
        this.dateSit = dateSit;
    }

    public void setDureSit(int dureSit) {
        this.dureSit = dureSit;
    }

    public void setToDoList(ArrayList<String> toDoList) {
        this.toDoList = toDoList;
    }

    public Date getDateSit() {
        return dateSit;
    }

    public int getDureSit() {
        return dureSit;
    }

    public ArrayList<String> getToDoList() {
        return toDoList;
    }

    

}

