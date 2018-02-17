/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Connexion;
import Entity.Reclamation;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AYOUB
 */
public class ConnexionServices {
    protected Connection connection;
    private DbHandler handler;
    
    public ConnexionServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererReclamation (Connexion p)
    {
        String req="INSERT INTO connexion (id_utilisateur,date) VALUES(?,now())" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,p.getId_utilisateur()) ;          
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateConnexion (int id )
    {
        String req="UPDATE connexion SET date=now() WHERE id_utilisateur=?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, id);
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ObservableList<Connexion> getAll(){
        String req="SELECT * FROM Connexion" ;
        ObservableList<Connexion> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                Timestamp date = rs.getTimestamp("date");
                int utilisateur= rs.getInt("id_utilisateur");
                              
                list.add(new Connexion(id,utilisateur,date));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
}
