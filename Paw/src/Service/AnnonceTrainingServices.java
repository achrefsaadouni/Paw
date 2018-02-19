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
        String req="INSERT INTO annonce (age,couleur,sex,type,race,message_complementaire,nomPet,dateTr,typeTr,type_annonce,date) VALUES(?,?,?,?,?,?,?,?,?,?,now())" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getTypePet()) ; 
            ste.setString(5,a.getRace()) ;
            ste.setString(6,a.getMessage_complementaire()) ;
            ste.setString(7,a.getNomPet()) ;
            ste.setDate(8, (java.sql.Date) a.getDateTr());
            ste.setString(9,a.getTypeTr());
            ste.setString(10,a.getType());
            
                System.out.println("avant");
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
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
                int agePet = rs.getInt("age");
                String colorPet = rs.getString("couleur");
                String  sexePet= rs.getString("sex");
                String typePet=rs.getString("typePet");
                String  racePet= rs.getString("race");
                String  descPet= rs.getString("message_complementaire");
                String nomPet = rs.getString("nomPet");
                Date  dateTr= rs.getDate("dateTr");
                String  typeTr= rs.getString("typeTr");
                String  type= rs.getString("type");
                Timestamp date=rs.getTimestamp("date");
                

               
        
              list.add(new AnnonceTraining( dateTr,typeTr,typePet,nomPet,id,agePet,colorPet,sexePet,racePet,descPet,type,date));
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
