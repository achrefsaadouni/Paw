/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author vinga
 */
 public class Panier {
    public static List<LigneAchat>panier = new ArrayList<>();
    public static List<LigneAchat> getPanier() {
         return panier;
     }
    public static void addProduit(Produit p)
    {
        
        for (LigneAchat ligneAchat : panier) {
            if (ligneAchat.getId_produit()==p.getId_produit())
            {
                if(ligneAchat.getNbr_produit()+1<=ligneAchat.getProduit().getQuantite())
                {
                ligneAchat.setNbr_produit(ligneAchat.getNbr_produit()+1);
                return ;
                }
                return;
               
            }
        }
         LigneAchat ligneAchat = new LigneAchat(p,1);
         panier.add(ligneAchat);
         
    }
    public static void deleteProduit(LigneAchat achat)
    {
        panier.remove(achat);
    }
    public static void minus(Produit p)
    {
        if (panier.get(findp(p)).getNbr_produit()!=0)
        panier.get(findp(p)).setNbr_produit(panier.get(findp(p)).getNbr_produit()-1);
            
    }
    
    public static void plus(Produit p)
    {
        if(panier.get(findp(p)).getNbr_produit()+1<=p.getQuantite())
        panier.get(findp(p)).setNbr_produit(panier.get(findp(p)).getNbr_produit()+1);
    }
    public static int findp(Produit p)
    {
        
        for(int i=0;i<panier.size();i++)
        {
            if (panier.get(i).getProduit()==p)
                return i;
        }
        return 0;
    }
    public static double prixtotal()
    {
        double prix = 0;
        for (LigneAchat ligneAchat : panier) {
            prix +=(double) (ligneAchat.getNbr_produit()*ligneAchat.getProduit().getPrix());
            
        }
        return prix;
    }
    public static boolean valide()
    {
        for (LigneAchat ligneAchat : panier) {
            if(ligneAchat.getNbr_produit()>ligneAchat.getProduit().getQuantite())
                return false;
        }
        return true;
    }
}
