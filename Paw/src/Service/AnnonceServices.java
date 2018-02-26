/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Annonce;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AmineBenKhelifa
 */
public class AnnonceServices {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererAnnonce (Annonce a)
    {
        String req="INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date) VALUES(?,?,?,?,?,?,now())" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème insertion annonce");
        }
        
    
    }
    
    public ObservableList<Annonce> getAll(){
        String req="SELECT * FROM annonce" ;
        ObservableList<Annonce> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String couleur = rs.getString("couleur");
                String  sex= rs.getString("sex");
                String  race= rs.getString("race");
                String  message_complementaire= rs.getString("message_complementaire");
                String  type= rs.getString("type");
                Timestamp date= rs.getTimestamp("date");
                int id_u = rs.getInt("utilisateur_id");
                list.add(new Annonce(id,age,couleur,sex,race,message_complementaire,type,date,id_u));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }

    public ArrayList<Annonce> getMesAnnonces(int i){
        String req="SELECT * FROM annonce where utilisateur_id=? " ;
        ArrayList<Annonce> list =new ArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String couleur = rs.getString("couleur");
                String  sex= rs.getString("sex");
                String  race= rs.getString("race");
                String  message_complementaire= rs.getString("message_complementaire");
                String  type= rs.getString("type");
                Timestamp date= rs.getTimestamp("date");
                int id_u = rs.getInt("utilisateur_id");
                list.add(new Annonce(id,age,couleur,sex,race,message_complementaire,type,date,id_u));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public void updateAnnonce (Annonce a, int id )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             ste.setInt(7,id) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getType()) ; 
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème update Annonce");
        }
    
    }
    
     public void DeleteAnnonce (int id )
    {
    String req="DELETE  from annonce where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
           
        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }
    
      }

    public int getNbrMesAnnoncesLAF(int i) {
        String req="SELECT * FROM annonce where utilisateur_id=? " ;
        int y = 0;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                y++;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return y;
    }

    
}
