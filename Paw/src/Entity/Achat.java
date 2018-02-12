/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vinga
 */
public class Achat extends RecursiveTreeObject<Achat>{
    
    private int id_achat;
    private int id_client;
    private List<LigneAchat> list;
    private Date date_achat;
    private float prix;
    private String etat;

    public List<LigneAchat> getList() {
        return list;
    }

    public void setList(List<LigneAchat> list) {
        this.list = list;
    }

    public Achat(int id_achat, int id_client, Date date_achat, float prix, String etat) {
        this.id_achat = id_achat;
        this.id_client = id_client;
        this.date_achat = date_achat;
        this.prix = prix;
        this.etat = etat;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Achat(int id_achat, int id_client, List<LigneAchat> list, Date date_achat, float prix,String etat) {
        this.id_achat = id_achat;
        this.id_client = id_client;
        this.list = list;
        this.date_achat = date_achat;
        this.prix = prix;
        this.etat=etat;
    }


    public Achat(int id_client, float prix,String etat) {
        this.id_client = id_client;
        this.prix = prix;
        this.etat=etat;
    }

    
    
    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    public Achat(int id_client, Date date_achat) {
        this.id_client = id_client;
        this.date_achat = date_achat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
}
