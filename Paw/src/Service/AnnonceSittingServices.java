/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceSitting;

//import Entity.Annonce ;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Lenovo
 */
public class AnnonceSittingServices {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceSittingServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
   public void insererAnnonceSitting (AnnonceSitting a)
    {
        ArrayList<String> liste= a.getToDoList();
        System.out.println(liste.toString());
        
        String req="INSERT INTO annoncesit (age,couleur,sex,race,message_complementaire,type,date,dateSit,dureSit,todolist) VALUES(?,?,?,?,?,?,now(),?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.setDate(7, a.getDateSit());
            ste.setInt(8,a.getDureSit());
//            ste.setString(9,a.getToDoList());
            
                System.out.println("avant");
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            System.out.println("Insertion");
        }
        
    
    }
     public ObservableList<AnnonceSitting> getAll1(){
        String req="SELECT * FROM annoncesit" ; //WHERE typeSit LIKE 'QuickVisit'" ;
        ObservableList<AnnonceSitting> list = FXCollections.observableArrayList();
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
                Date date=rs.getDate("date");
                Date  dateSit= rs.getDate("dateSit");
                String  typeSit= rs.getString("dureSit");
                

               
        
//              list.add(new AnnonceSitting( dateSit ,typeSit,toDoList, id, age,  couleur, sex,  race,  message_complementaire,  type,  date));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public void updateAnnonceSitting (AnnonceSitting a, int id )
    {
    String req="UPDATE annoncesit SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?, dateSit=?,typeSit=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(9,id) ;
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getType()) ; 
           ste.setDate(7,a.getDateSit()) ; 
           ste.setInt(8,a.getDureSit()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
     public void DeleteAnnonceSitting (int id )
    {
    String req="DELETE  from annoncesit where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
           
        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }
    
      }
        
    
    
}
