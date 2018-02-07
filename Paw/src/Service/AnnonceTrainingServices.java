/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceTraining;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Lenovo
 */
public class AnnonceTrainingServices {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceTrainingServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererAnnonceTraining (AnnonceTraining a)
    {
        String req="INSERT INTO annonceTr (age,couleur,sex,race,message_complementaire,type,date,dateTr,dureTr,typeTr) VALUES(?,?,?,?,?,?,now(),?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.setDate(7, (java.sql.Date) a.getDateTr());
            ste.setInt(8,a.getDureTr());
            ste.setString(9,a.getTypeTr());
            
                System.out.println("avant");
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème insertion annonce");
        }
        
    
    }
     public ObservableList<AnnonceTraining> getAll1(){
        String req="SELECT * FROM annoncetr" ; //WHERE typeSit LIKE 'QuickVisit'" ;
        ObservableList<AnnonceTraining> list = FXCollections.observableArrayList();
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
                Date  dateTr= rs.getDate("dateTr");
                int  dureTr= rs.getInt("dureTr");
                String  typeTr= rs.getString("typeTr");
                

               
        
              list.add(new AnnonceTraining( dateTr,dureTr ,typeTr, id, age,  couleur, sex,  race,  message_complementaire,  type,  date));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
    
    public void updateAnnonceTraining (AnnonceTraining a, int id )
    {
    String req="UPDATE annoncetr SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?, dateTr=?,dureTr=?,typeTr=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(10,id) ;
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getType()) ; 
           ste.setDate(7, (java.sql.Date) a.getDateTr()) ; 
           ste.setInt(8,a.getDureTr()) ; 
           ste.setString(9,a.getTypeTr()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
     public void DeleteAnnonceTraining (int id )
    {
    String req="DELETE  from annoncetr where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
           
        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }
    
      }
    
}
