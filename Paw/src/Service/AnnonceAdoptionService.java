/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceAdoption;
import Entity.RepOffreAdoption;
import Utility.DbHandler;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYOUB
 */
public class AnnonceAdoptionService {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceAdoptionService (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
    public void inserer (AnnonceAdoption a)
    {
        String images="";
        images=a.getImages().getPath().toString();
        String req="INSERT INTO `annonce`(`age`, `couleur`, `sex`, `race`, `message_complementaire`, `type`, `date`, `type_annonce`,  `utilisateur_id`, `typeAdoption`, `debutAdoption`, `finAdoption`,`images`,`etatAdoption`) VALUES (?,?,?,?,?,?,now(),?,?,?,?,?,?,?)"; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ;
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;      
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ; 
            ste.setString(7,"Annonce_adoption") ;  
            ste.setInt(8,a.getId_utilisateur()) ;
            ste.setString(9,a.getTypeAdoption()) ; 
            ste.setDate(10, (java.sql.Date) a.getDebutAdoption()) ;  
            ste.setDate(11,(java.sql.Date) a.getFinAdoption()) ;
            ste.setString(12,images) ;
            ste.setString(13,"Disponible") ;
                                                                                                                         
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ArrayList<AnnonceAdoption> getAllDeprecated(){
        String req="SELECT * FROM Annonce where type_annonce like 'Annonce_adoption'" ;
        ArrayList<AnnonceAdoption> list = new ArrayList<>();
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
                File images=new File(rs.getString("images"));
                
                String etatAdoption=rs.getString("etatAdoption");
                String typeAdoption=rs.getString("typeAdoption");
                Date debutAdoption = rs.getDate("debutAdoption");
                Date finAdoption = rs.getDate("finAdoption");
                              
                list.add(new AnnonceAdoption(typeAdoption, debutAdoption, finAdoption, id, age, couleur, sex, race, message_complementaire, type, finAdoption, age, images,etatAdoption));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
     
    public void update (AnnonceAdoption p, int i)
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?, typeAdoption=?, debutAdoption=?,finAdoption=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setInt(1,p.getAge()) ; 
            ste.setString(2, p.getCouleur());
            ste.setString(3,p.getSex()) ;  
            ste.setString(4,p.getRace()) ;  
            ste.setString(5,p.getMessage_complementaire()) ;  
            ste.setString(6,p.getType()) ;  
            ste.setString(7,p.getTypeAdoption()) ;  
            ste.setDate(8,(java.sql.Date) p.getDebutAdoption());
            ste.setDate(9,(java.sql.Date) p.getDebutAdoption());

            ste.setInt(10, i);
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("UpdateAnnonceAdoption");
        }
    }
    
     public void Delete (int id )
    {
        String req="DELETE from annonce where id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ;    
        } 
        catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("DelereAnnonceAdoption");
        }
    
    }

    public ArrayList<AnnonceAdoption> getAnnonceAdoptionUtilisateur(int i) {
        
            String req="SELECT * FROM annonce WHERE utilisateur_id=? and type_annonce like 'Annonce_adoption'" ;
            ArrayList<AnnonceAdoption> list = new ArrayList();
            try {
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
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
                File images=new File(rs.getString("images"));
                String etatAdoption=rs.getString("etatAdoption");
                int id_utilisateur=rs.getInt("utilisateur_id");
                String typeAdoption=rs.getString("typeAdoption");
                Date debutAdoption = rs.getDate("debutAdoption");
                Date finAdoption = rs.getDate("finAdoption");
                              
                list.add(new AnnonceAdoption(typeAdoption, debutAdoption, finAdoption, id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur,etatAdoption));
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("getAnnonceAdoptionUtilisateur");
        }
        return list;
    }

    public void traiterAdoption(int i) 
    {
    String req="UPDATE annonce SET etatAdoption=? WHERE id=?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            
            ste.setString(1,"Non Disponible");
            ste.setInt(2, i);
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("traiterAdoption");
        }
    }


    public ArrayList<AnnonceAdoption> getAnnonceAdoptionDisponible() {
        String req="SELECT * FROM annonce WHERE etatAdoption like 'Disponible' and type_annonce like 'Annonce_adoption'" ;
        ArrayList<AnnonceAdoption> list = new ArrayList();
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
                //File images=new File(rs.getString("images"));
                File image=new File(rs.getString("images"));
                //String image = rs.getString("images");
                
                int id_utilisateur=rs.getInt("utilisateur_id");
                String etatAdoption=rs.getString("etatAdoption");
                String typeAdoption=rs.getString("typeAdoption");
                Date debutAdoption = rs.getDate("debutAdoption");
                Date finAdoption = rs.getDate("finAdoption");
                            
                list.add(new AnnonceAdoption(typeAdoption, debutAdoption, finAdoption, id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur,image,etatAdoption));            }

        } catch (SQLException ex) {
             Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("getAnnonceAdoptionDisponible");
        }
        return list;      
    }
    
    public ArrayList<AnnonceAdoption> getAnnonceAdoptionNonDisponible() {
        String req="SELECT * FROM annonce WHERE etatAdoption like 'Non Disponible' and type_annonce like 'Annonce_adoption'" ;
        ArrayList<AnnonceAdoption> list = new ArrayList();
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
                //File images=new File(rs.getString("images"));
                File images=new File("C://Users//AYOUB//Desktop//ayoub.png");
                int id_utilisateur=rs.getInt("utilisateur_id");
                String etatAdoption=rs.getString("etatAdoption");
                String typeAdoption=rs.getString("typeAdoption");
                Date debutAdoption = rs.getDate("debutAdoption");
                Date finAdoption = rs.getDate("finAdoption");
                list.add(new AnnonceAdoption(typeAdoption, debutAdoption, finAdoption, id, age, couleur, sex, race, message_complementaire, type, date, id_utilisateur,etatAdoption));
            }
        } catch (SQLException ex) {
             Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("getAnnonceAdoptionNonDisponible");
        }
        return list;      
    }
    
    
    
    
    
    public ArrayList<RepOffreAdoption> getRep(int i){
        String req="SELECT * FROM repAdoption where id_annonce=?" ;
        ArrayList<RepOffreAdoption> list = new ArrayList<>();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                int idannonce = rs.getInt("id_annonce");
                int id_utilisateur = rs.getInt("id_utilisateur");
                String  etat= rs.getString("etat");
                Timestamp date=rs.getTimestamp("date");
                 
                list.add(new RepOffreAdoption(id,idannonce,id_utilisateur,etat,date));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
