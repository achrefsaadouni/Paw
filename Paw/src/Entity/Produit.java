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
    private List<File> images;
    private String type;

    public int getId_produit() {
        return id_produit;
    }

    public Produit(String libelle, float prix, int quantite, String description, List<File> images, String type) {
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.images = images;
        this.type = type;
    }

    public Produit(int id_produit, String libelle, float prix, int quantite, String description, String type) {
        this.id_produit = id_produit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", libelle=" + libelle + ", prix=" + prix + ", quantite=" + quantite + ", description=" + description + ", images=" + images + ", type=" + type + '}';
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

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
