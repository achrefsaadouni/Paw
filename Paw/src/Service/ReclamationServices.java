/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Reclamation;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AYOUB
 */
public class ReclamationServices {
    
    protected Connection connection;
    private DbHandler handler;
    
    public ReclamationServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererReclamation (Reclamation p)
    {
        String req="INSERT INTO Reclamation (id_utilisateur,objet,text,type,date) VALUES(?,?,?,?,now())" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,p.getUtilisateur()) ; 
            ste.setString(2,p.getObjet()) ;
            ste.setString(3,p.getText()) ; 
            ste.setString(4,p.getType()) ;           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ObservableList<Reclamation> getAll(){
        String req="SELECT * FROM Reclamation" ;
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String objet = rs.getString("objet");
                String text= rs.getString("text");
                String type = rs.getString("type");
                Timestamp date = rs.getTimestamp("date");
                int utilisateur= rs.getInt("id_utilisateur");
                String etat = rs.getString("etat");
                              
                list.add(new Reclamation(id,utilisateur, objet, text, type, date,etat));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
     
    public void updateReclamation (Reclamation p, int id )
    {
    String req="UPDATE Reclamation SET objet=?, text=?, type=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setString(1,p.getObjet()) ; 
            ste.setString(2, p.getText());
            ste.setString(3,p.getType()) ;  
            ste.setInt(4, id);
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     public void DeleteReclamation (int id )
    {
        String req="DELETE  from Reclamation where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ;    
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }

    public ArrayList<Reclamation> getReclamationUtilisateur(int i) {
        String req="SELECT * FROM Reclamation WHERE id_utilisateur=?" ;
        ArrayList<Reclamation> list = new ArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String objet = rs.getString("objet");
                String text= rs.getString("text");
                String type = rs.getString("type");
                String etat = rs.getString("etat");
                java.sql.Timestamp date = rs.getTimestamp("date");
                              
                list.add(new Reclamation(id,i, objet, text, type, date,etat));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public void traiterReclamation(int i) 
    {
    String req="UPDATE Reclamation SET etat=? WHERE id=?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            
            ste.setString(1, "Traitée");
            ste.setInt(2, i);
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public double getRemerciment() {
        String req="SELECT count(*) as a FROM Reclamation WHERE type=?" ;
        double x=0;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, "Remerciment");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;
    }

    public double getReclamation() {
        String req="SELECT count(*) as a FROM Reclamation WHERE type=?" ;
        double x=0;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, "Reclamation");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;    
    }

    public double getTraitee() {
        String req="SELECT count(*) as a FROM Reclamation WHERE etat=?" ;
        double x=0;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, "Traitée");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;    
    }

    public double getNonTraitee() {
        String req="SELECT count(*) as a FROM Reclamation WHERE etat=?" ;
        double x=0;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, "Non traitée");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;        }
}
