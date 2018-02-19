/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Utilisateur;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AYOUB
 */
public class UtilisateurServices {
    protected Connection connection;
    private DbHandler handler;
    
    public UtilisateurServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererUtilisateur (Utilisateur p)
    {
        String req="INSERT INTO utilisateur (nom,prenom,email,username,password,addresse,numero,role,sexe,dateInscription,avatar) VALUES(?,?,?,?,?,?,?,?,?,now(),?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,p.getNom()) ; 
            ste.setString(2,p.getPrenom()) ;
            ste.setString(3,p.getEmail()) ; 
            ste.setString(4,p.getUsername()) ; 
            ste.setString(5,DigestUtils.shaHex(p.getPassword())) ; 
            ste.setString(6,p.getAddresse()) ; 
            ste.setInt(7,p.getNumero()) ; 
            ste.setString(8,p.getRole()) ;
            ste.setString(9,p.getSexe()) ; 
            ste.setString(10,p.getAvatar()) ;
            
              ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
    }
    
    public ObservableList<Utilisateur> getAll(){
        String req="SELECT * FROM utilisateur" ;
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String addresse = rs.getString("addresse");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                int numero = rs.getInt("numero");
                list.add(new Utilisateur(id, nom, prenom, email, username, password,addresse,numero,role));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
     
    public void updateUtilisateur (Utilisateur p, int id )
    {
    String req="UPDATE utilisateur SET nom=?,prenom=?, email=?, username=?, password=?,addresse=?,numero=?,role=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setString(1,p.getNom()) ; 
            ste.setString(2,p.getPrenom()) ; 
            ste.setString(3,p.getEmail()) ; 
            ste.setString(4,p.getUsername()) ; 
            ste.setString(5,DigestUtils.shaHex(p.getPassword())) ; 
            ste.setString(6,p.getAddresse()) ; 
            ste.setString(8,p.getRole()) ; 
            ste.setInt(7,p.getNumero()) ;
            ste.setInt(9,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
     public void DeleteUtilisateur (int id )
    {
    String req="DELETE  from utilisateur where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
      }
     
     
       public Utilisateur rechercher(int id){
        String req="SELECT * FROM utilisateur where id=?" ;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String addresse = rs.getString("addresse");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String sexe = rs.getString("sexe");
                int numero = rs.getInt("numero");
                return new Utilisateur(id, nom, prenom, email, username, password,addresse,numero,role,sexe);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
