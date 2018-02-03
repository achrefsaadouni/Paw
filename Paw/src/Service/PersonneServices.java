/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Personne;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AYOUB
 */
public class PersonneServices {
    protected Connection connection;
    private DbHandler handler;
    
    public PersonneServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererPersonne (Personne p)
    {
        String req="INSERT INTO personne VALUES(?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,p.getId()) ; 
            ste.setString(2,p.getNom()) ; 
            ste.setString(3,p.getPrenom()) ; 
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème insertion Personne");
        }
        
    
    }
    
    public ObservableList<Personne> getAll(){
        String req="SELECT * FROM personne" ;
        ObservableList<Personne> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                
                list.add(new Personne(id,nom,prenom));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Personne");
        }
        return list;
    }
     
    public void updatePersonne (Personne p, int id )
    {
    String req="UPDATE personne SET nom=?,prenom=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setString(1,p.getNom()) ; 
            ste.setString(2,p.getPrenom()) ; 
            ste.setInt(3,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème update Personne");
        }
    
    }
    
     public void DeletePersonne (int id )
    {
    String req="DELETE  from personne where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème delete Personne");
        }
    
      }
}
