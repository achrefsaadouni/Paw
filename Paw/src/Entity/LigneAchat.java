/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 *
 * @author vinga
 */
public class LigneAchat extends RecursiveTreeObject<LigneAchat>{
    private int id_ligne;
    private Produit produit;
    private int nbr_produit;
    private int id_achat;

    public int getId_ligne() {
        return id_ligne;
    }

    public int getId_produit() {
        return produit.getId_produit();
    }

    @Override
    public String toString() {
        return "LigneAchat{" + "id_ligne=" + id_ligne + ", produit=" + produit + ", nbr_produit=" + nbr_produit + ", id_achat=" + id_achat + '}';
    }



    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public int getNbr_produit() {
        return nbr_produit;
    }

    public void setNbr_produit(int nbr_produit) {
        this.nbr_produit = nbr_produit;
    }

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public LigneAchat(int id_ligne,Produit produit ,int nbr_produit, int id_achat) {
        this.id_ligne = id_ligne;
        this.nbr_produit = nbr_produit;
        this.id_achat = id_achat;
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    public LigneAchat(int nbr_produit,Produit produit, int id_achat) {
        this.nbr_produit = nbr_produit;
        this.id_achat = id_achat;
    }
    
    
}
