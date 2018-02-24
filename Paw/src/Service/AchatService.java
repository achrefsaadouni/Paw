/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Achat;
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
public class AchatService {

    final private DbHandler handler;
    protected Connection connection;
    private static AchatService achatservice;
    LigneAchatService ligneservice = LigneAchatService.getLigneService();

    public static AchatService getAchatService() {
        if (AchatService.achatservice == null) {
            achatservice = new AchatService();
        }
        return achatservice;
    }

    public AchatService() {
        handler = DbHandler.getDBHandler();
        connection = handler.getConnection();
    }

    public boolean addAchat(Achat a) {
          
        String req = "INSERT INTO `achat` (`id_client`,`prix`,`etat`) VALUES(?,?,?)";
        String k = "select id_achat from `achat` order by id_achat desc limit 1";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, a.getId_client());
            if(a.getPrix()>30)
            ste.setDouble(2, a.getPrix());
            else
            ste.setDouble(2, a.getPrix()+5);   
            ste.setString(3, a.getEtat());
            ste.executeUpdate();     
        try {
            PreparedStatement ste1 = connection.prepareStatement(k);
            ste1.executeQuery();
            ResultSet results = ste1.executeQuery();
           
            while (results.next()) {
                a.setId_achat(results.getInt("id_achat"));
            }
             a.getList().forEach((ligne) -> {
            ligne.setId_achat(a.getId_achat());
        });
        
        a.getList().forEach((ligne) -> {
            ligneservice.addLigneAchat(ligne);
        }); 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     
        return false;
    }

    public int nombreAchat(int id_client) {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `achat` where id_client=?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id_client);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }

    public void deleteAchat(Achat achat) {
        String req = "DELETE from `achat` where  id_achat =?";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, achat.getId_achat());
            for (LigneAchat ligneAchat : achat.getList()) {
               ligneservice.deleteLigneAchat(ligneAchat.getId_ligne());
            }
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problème delete LigneAchat");
        }

    }

    public ArrayList<Achat> findAll(int id) {
        String sql = "SELECT * FROM `achat` where id_client=?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            ArrayList<Achat> achats = new ArrayList<>();
            ArrayList<LigneAchat> ligneachats = null;
            Achat a;
            while (results.next()) {
                a = new Achat(results.getInt("id_achat"), results.getInt("id_client"), results.getTimestamp("date_achat"), results.getFloat("prix"), results.getString("etat"));
                ligneachats = LigneAchatService.getLigneService().findAll(a.getId_achat());
                a.setList(ligneachats);
                achats.add(a);
            }
            return achats;
        } catch (SQLException ex) {
            System.out.println("erreur affichage LigneAchat");
        }
        return null;
    }

    public ArrayList<Achat> All() {
        String sql = "SELECT * FROM `achat`";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            ArrayList<Achat> achats = new ArrayList<>();
            ArrayList<LigneAchat> ligneachats = null;
            Achat a;
            while (results.next()) {
                a = new Achat(results.getInt("id_achat"), results.getInt("id_client"), results.getTimestamp("date_achat"), results.getFloat("prix"), results.getString("etat"));
                ligneachats = LigneAchatService.getLigneService().findAll(a.getId_achat());
                a.setList(ligneachats);
                achats.add(a);
            }
            return achats;
        } catch (SQLException ex) {
            System.out.println("erreur affichage LigneAchat");
        }
        return null;
    }
    public void livrer(int id)
    {
          String req = "update  `achat` set etat='livrer' where id_achat=?";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problème update livrer");
        }
        
    }
    
   public int nombreAchatlivrer() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `achat` where etat='livrer'";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }
      public int nombreAchatpayer() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `achat` where etat='Payer'";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }
      
            public int nombreAchatnonpayer() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `achat` where etat='Non Payer'";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }
}
