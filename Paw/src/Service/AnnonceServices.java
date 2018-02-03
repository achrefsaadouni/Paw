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
        String req="INSERT INTO annonce VALUES(?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getId()) ; 
            ste.setInt(2,a.getAge()) ; 
            ste.setString(3,a.getCouleur()) ; 
            ste.setString(4,a.getSex()) ; 
             ste.setString(5,a.getRace()) ;
              ste.setString(6,a.getMessage_complementaire()) ;
               ste.setString(7,a.getTypeAnimal()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème insertion Personne");
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
                String  TypeAnimal= rs.getString("TypeAnimal");
                list.add(new Annonce(id,age,couleur,sex,race,message_complementaire,TypeAnimal));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
     
    public void updateAnnonce (Annonce a, int id )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,TypeAnimal=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             ste.setInt(7,id) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getTypeAnimal()) ; 
           
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

    
}
