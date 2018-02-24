/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
/**
 *
 * @author Lenovo
 */
//import Entity.Annonce ;
import Entity.AnnonceWalking;

import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AnnonceWalkingServices {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceWalkingServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    } 
    public void insererAnnonceWalking (AnnonceWalking a)
    {
        String req="INSERT INTO annoncewalk (age,couleur,sex,race,message_complementaire,type,date,dateWalk,dureWalk) VALUES(?,?,?,?,?,?,now(),?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.setDate(7, (java.sql.Date) a.getDateWalk());
            ste.setInt(8,a.getDureWalk());
            
                System.out.println("avant");
           
            ste.executeUpdate() ; 
            
                  System.out.println("Succes Insertion !!");
            
        } catch (SQLException ex) {
            System.out.println("Problème insertion annonce");
        }
        
    
    }
     public ObservableList<AnnonceWalking> getAll1(){
        String req="SELECT * FROM annoncewalk" ; //WHERE typeSit LIKE 'QuickVisit'" ;
        ObservableList<AnnonceWalking> list = FXCollections.observableArrayList();
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
                Timestamp date=rs.getTimestamp("date");
                Date  dateWalk= rs.getDate("dateWalk");
                int  dureWalk= rs.getInt("dureWalk");
                

               
        
              list.add(new AnnonceWalking( dateWalk ,dureWalk, id, age,  couleur, sex,  race,  message_complementaire,  type,  date));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
    
    public void updateAnnonceWalking (AnnonceWalking a, int id )
    {
    String req="UPDATE annoncewalk SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?, dateWalk=?,dureWalking=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(9,id) ;
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getType()) ; 
           ste.setDate(7, (java.sql.Date) a.getDateWalk()) ; 
           ste.setInt(8,a.getDureWalk()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
     public void DeleteAnnonceWalking (int id )
    {
    String req="DELETE  from annonceWalk where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
           
        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }
    
      }
     
      public int nombre() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `annonceWalk`";
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
