/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author AYOUB
 */
public class RepRec {
    private int id;
    private int id_reclamation;
    private String text;
    private Date date;

    public RepRec(int id_reclamation, String text) {
        this.id_reclamation = id_reclamation;
        this.text = text;
    }

    public RepRec(int id, int id_reclamation, String text, Date date) {
        this.id = id;
        this.id_reclamation = id_reclamation;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
