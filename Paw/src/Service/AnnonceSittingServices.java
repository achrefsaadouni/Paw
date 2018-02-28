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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String liste= a.getToDoList();
        System.out.println(liste.toString());
        
        String req="INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date,dateSit,dureSit,todolist,type_annonce,utilisateur_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getTypePet()) ;
            ste.setDate(7,Date.valueOf(LocalDate.now()));
            ste.setDate(8, a.getDateSit());
            ste.setInt(9,a.getDureSit());
            ste.setString(10,a.getToDoList().toString());
            ste.setString(11,a.getType());
            ste.setInt(12,a.getId_utilisateur());
            
                System.out.println("avant");
                System.out.println(a.getDureSit());
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            System.out.println("Insertion Sitting");
        }
        
    
    }
     public ObservableList<AnnonceSitting> getAll1(){
        String req="SELECT * FROM annonce WHERE type_annonce like 'Annonce Sitting'" ;
        ObservableList<AnnonceSitting> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String  type= rs.getString("type");
                String  race= rs.getString("race");
                int age = rs.getInt("age");
                String  sex= rs.getString("sex");
                String couleur = rs.getString("couleur");
                String  message_complementaire= rs.getString("message_complementaire");
                String  typeAnnonce= rs.getString("type_annonce");
                Date date=rs.getDate("date");
                int id_utilisateur=rs.getInt("utilisateur_id");
                Date  dateSit= rs.getDate("dateSit");
                int  dureSit= rs.getInt("dureSit");
                String toDoList = rs.getString("todolist");


               
        
              list.add(new AnnonceSitting( dateSit ,dureSit,toDoList,type, id, age,  couleur, sex,  race,  message_complementaire,  type,  date, id_utilisateur));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public void updateAnnonceSitting (AnnonceSitting a, int id )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?, dateSit=?,dureSit=? ,todolist=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(10,id) ;
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getTypePet()) ; 
           ste.setDate(7,a.getDateSit()) ; 
           ste.setInt(8,a.getDureSit()) ; 
           ste.setString(9,a.getToDoList()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
     public void DeleteAnnonceSitting (int id )
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

    public ArrayList<AnnonceSitting> getAnnonceSitting(int idUser) {
        String req="SELECT * FROM annonce WHERE type_annonce like 'Annonce Sitting' and utilisateur_id=?" ;
        ArrayList<AnnonceSitting> list = new ArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, idUser);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String couleur = rs.getString("couleur");
                String  sex= rs.getString("sex");
                String  race= rs.getString("race");
                String  message_complementaire= rs.getString("message_complementaire");
                String  typePet= rs.getString("type");
                Timestamp date=rs.getTimestamp("date");
                String  typeAnnonce= rs.getString("type_annonce");
                //File images=new File(rs.getString("images"));
                //File images=new File("C://Users//AYOUB//Desktop//ayoub.png");
                int id_utilisateur=rs.getInt("utilisateur_id");
                Date dateSit = rs.getDate("dateSit");
                System.out.println("");
                int dureSit = rs.getInt("dureSit");
                String toDoList=rs.getString("todolist");
               
                            
                list.add(new AnnonceSitting(dateSit, dureSit, toDoList , typePet, id, age, couleur, sex, race, message_complementaire, typeAnnonce, date, id_utilisateur));            }
                return list;

        } catch (SQLException ex) {
             Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("getAnnonceSitting Catch");
        }
        return null;
    }
    public int nombre() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `annoncetr`";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("erreur affichage nombre");
        }
        return y;

    }

    public int nombreMesAnnonces(int id) {
        int y=0;    
            String req="SELECT * FROM annonce WHERE utilisateur_id=? and type_annonce like 'Annonce Sitting'" ;
            try {
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                y++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("getAnnonceAdoptionUtilisateur");
        }
        return y;
    }
        
    
    
}
