/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceAccouplement;
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
 * @author gmehd
 */
public class AnnonceAccouplementServices {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceAccouplementServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererAnnonceAccouplement (AnnonceAccouplement a)
    {
        String req="INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date,type_annonce,type_poil,vaccin,dossier) VALUES(?,?,?,?,?,?,now(),?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.setString(7,"annonce_accouplement");
            ste.setString(8, a.getType_poil());
            ste.setString(9, a.getVaccin());
            ste.setString(10, a.getDossier());
            
                            System.out.println("avant");
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
    }
    
    public ObservableList<AnnonceAccouplement> getAll1(){
        String req="SELECT * FROM annonce WHERE type_annonce LIKE 'annonce_accouplement'" ;
        ObservableList<AnnonceAccouplement> list = FXCollections.observableArrayList();
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
                String  type_annonce= rs.getString("type_annonce");
                String  type_poil= rs.getString("type_poil");
                String  vaccin= rs.getString("vaccin");
                String  dossier= rs.getString("dossier");
                
                
                
               
        
              list.add(new AnnonceAccouplement( type_poil,  vaccin,  dossier,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
     
    public void updateAnnonceAccouplement (AnnonceAccouplement a, int id )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?,type_poil=?,vaccin=?,dossier=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getType()) ; 
           ste.setString(7,a.getType_poil()) ; 
           ste.setString(8,a.getVaccin()) ;
           ste.setString(9,a.getDossier()) ;
           
           ste.setInt(10,id) ;
           ste.executeUpdate() ; 
            System.out.println("Services mrigla frère");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
     public void DeleteAnnonceAccouplement (int id )
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
