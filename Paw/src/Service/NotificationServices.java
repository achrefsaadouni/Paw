/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Notification;
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
public class NotificationServices {
    protected Connection connection;
    private DbHandler handler;
    
    public NotificationServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererNotification (Notification p)
    {
        String req="INSERT INTO notification (id_destinataire,id_emetteur,titre,text,type,date) VALUES(?,?,?,?,?,now())" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,p.getId_destinataire()) ; 
            ste.setInt(2,p.getId_emetteur()) ;
            ste.setString(3,p.getTitre()) ; 
            ste.setString(4,p.getText()) ; 
            ste.setString(5,p.getType()) ;         
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
    }
    
    public ObservableList<Notification> getAll(){
        String req="SELECT * FROM notification" ;
        ObservableList<Notification> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                int id_destinataire = rs.getInt("id_destinataire");
                int id_emetteur = rs.getInt("id_emetteur");
                String titre = rs.getString("titre");
                String text= rs.getString("text");
                String type = rs.getString("type");
                Timestamp date = rs.getTimestamp("date");
                String etat = rs.getString("etat");
                list.add(new Notification(id,id_destinataire,id_emetteur,titre,text,type,date,etat));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
     
    public void updateNotification (Notification p, int id )
    {
    String req="UPDATE notification SET id_destinataire=?,id_emetteur=?, titre=?, text=?, type=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setInt(1,p.getId_destinataire()) ; 
            ste.setInt(2,p.getId_emetteur()) ; 
            ste.setString(3,p.getTitre()) ; 
            ste.setString(4,p.getText()) ; 
            ste.setString(5,p.getType()) ;  
            ste.setInt(6,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     public void DeleteNotification (int id )
    {
        String req="DELETE  from notification where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ;    
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
}
