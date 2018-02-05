/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Veterinaire;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author gmehd
 */
public class VeterinaireServices {
    protected Connection connection;
    private DbHandler handler;
    
    public VeterinaireServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererVeterinaire (Veterinaire p)
    {
        String req="INSERT INTO Veterinaire (nom,prenom,adresse,region,numero,email) VALUES(?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,p.getNom()) ; 
            ste.setString(2,p.getPrenom()) ;
            ste.setString(3,p.getAdresse()) ;
            ste.setString(4,p.getRegion()) ;
            ste.setInt(5,p.getNumero()) ; 
            ste.setString(6,p.getEmail()) ; 
            
            
            
            //
            
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
    }
    
    public ObservableList<Veterinaire> getAll(){
        String req="SELECT * FROM Veterinaire" ;
        ObservableList<Veterinaire> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            System.out.println("    Hani hne mriguel");
            while (rs.next())
            {
                System.out.println("    fel lawel");
                
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String region = rs.getString("region");
                int numero = rs.getInt("numero");
                String email = rs.getString("email");
                
                
                System.out.println("    Hani hne mriguel");
                list.add(new Veterinaire(id, nom, prenom, adresse, region, numero, email));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Veterinaire");
        }
        return list;
    }
     
    public void updateVeterinaire (Veterinaire p, int id )
    {
    String req="UPDATE Veterinaire SET nom=?,prenom=?, adresse=?, region=?, numero=?,email=? WHERE id=?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setString(1,p.getNom()) ; 
            ste.setString(2,p.getPrenom()) ;
            ste.setString(3,p.getAdresse()) ;
            ste.setString(4,p.getRegion()) ;
            ste.setInt(5,p.getNumero()) ; 
            ste.setString(6,p.getEmail()) ;
            ste.setInt(7,id) ;
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème update Veterinaire");
        }
    
    }
    
     public void DeleteVeterinaire (int id )
    {
    String req="DELETE  from Veterinaire where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème delete Veterinaire");
        }
    
      }
}
