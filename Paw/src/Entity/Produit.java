/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.util.List;

/**
 *
 * @author vinga
 */
public class Produit extends RecursiveTreeObject<Produit> {
    private int id_produit;
    private String libelle;
    private float prix;
    private int quantite;
    private String description;
    private String image1;
    private String image2;
    private String type;

    public int getId_produit() {
        return id_produit;
    }

    public Produit(String libelle, float prix, int quantite, String description, String image1,String image2, String type) {
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.type = type;
        this.image1=image1;
        this.image2=image2;
    }

    public Produit(int id_produit, String libelle, float prix, int quantite, String description, String type) {
        this.id_produit = id_produit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.type = type;
    }



    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
    
}
