/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.LigneAchat;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author vinga
 */
public class LigneAchatService {
  
    final private DbHandler handler;
    protected Connection connection;
    private static LigneAchatService ligneservice;
   
    
    public static LigneAchatService getLigneService() {
        if (LigneAchatService.ligneservice == null) {
            ligneservice = new LigneAchatService();
        }
         return ligneservice;
     }
    
    
    public LigneAchatService() {
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
    public void addLigneAchat(LigneAchat a)
    {
        ProduitService produitservice= ProduitService.getProduitService();
        String req="INSERT INTO `ligneachat` (`id_produit`,`nbr_produit`,`id_achat`) VALUES(?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getId_produit()) ; 
            ste.setInt(2,a.getNbr_produit()) ; 
            ste.setInt(3,a.getId_achat()) ; 
            ste.executeUpdate() ; 
            produitservice.updatequantite(a.getId_produit(), (a.getProduit().getQuantite()-a.getNbr_produit()));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void deleteLigneAchat (int id )
    {
    String req="DELETE  from ligneachat where  id_ligne =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème delete LigneAchat");
        }
    
    }
     
        public ArrayList<LigneAchat> findAll(int id_achat) {
        String sql = "SELECT * FROM `ligneachat` where id_achat=?";
           ProduitService serviceproduit = ProduitService.getProduitService();
         try {
             PreparedStatement statement = this.connection.prepareStatement(sql);
             statement.setInt(1,id_achat) ;
             ResultSet results =  statement.executeQuery();
             ArrayList<LigneAchat> ligneachats = new ArrayList<>();
             LigneAchat a;
             while (results.next()) {
                 a = new LigneAchat(results.getInt("id_ligne"),serviceproduit.rechercher(results.getInt("id_produit")),results.getInt("nbr_produit"),id_achat);
                 ligneachats.add(a);
             }
             return ligneachats;
         } catch (SQLException ex) {
             System.out.println("erreur affichage LigneAchat");
         }
        return null;
    }
        
}
