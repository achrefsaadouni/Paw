/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnoncePerdu;
import Entity.AnnonceTrouvee;
import Utility.Checksum;
//import Entity.Annonce ;
import Utility.DbHandler;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Guideinfo
 */
public class AnnonceTrouveServices
{
      protected Connection connection;
    private DbHandler handler;
    
    public AnnonceTrouveServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererAnnonceTrouvee (AnnonceTrouvee a)
    {
        
         String images="";

        images=a.getImages().getPath().toString();
        String req="INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date,type_annonce,colier,lieu_trouve,utilisateur_id,images,date_trouvee) VALUES(?,?,?,?,?,?,now(),?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.setString(7,"annonce_trouvee");
            ste.setString(8, a.getColier());
            ste.setString(9,a.getLieu_trouve());
            ste.setInt(10,a.getId_utilisateur()); 
             ste.setString(11,images) ;
             ste.setDate(12, (java.sql.Date) a.getDate_trouvee());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
    }
    
     public String imageSave(File file) {
           
        try {
            String imageName = Checksum.createChecksum(file.getAbsolutePath());
            String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
            String filePath = "E:\\PIDEV\\Paw\\Paw\\src\\Ressource\\imagesBoutique\\" + imageName + extension;
            File dest = new File(filePath);
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return filePath;
        } catch (Exception ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public ObservableList<AnnonceTrouvee> getAll1(){
        String req="SELECT * FROM annonce WHERE type_annonce LIKE 'annonce_trouvee'" ;
        ObservableList<AnnonceTrouvee> list = FXCollections.observableArrayList();
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
                String  colier= rs.getString("colier");         
                Date date_trouvee=rs.getDate("date_trouvee");
                String lieu_trouve=rs.getString("lieu_trouve") ;
                int id_utilisateur=rs.getInt("utilisateur_id");
                File images=new File(rs.getString("images"));
               
        
              list.add(new AnnonceTrouvee( colier,  date_trouvee,  lieu_trouve,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date,id_utilisateur,images));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
     
    public void updateAnnonceTrouvee (AnnonceTrouvee a )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?,colier=?,lieu_trouve=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(9,a.getId()) ;
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getRace()) ; 
           ste.setString(5,a.getMessage_complementaire()) ; 
           ste.setString(6,a.getType()) ; 
           ste.setString(7,a.getColier()) ; 
           ste.setString(8,a.getLieu_trouve()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
     public void DeleteAnnonceTrouvee (int id )
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
     
     public ArrayList<AnnonceTrouvee> getMesAnnoncesTrouve(int i){
        String req="SELECT * FROM annonce where type_annonce LIKE 'annonce_trouvee' and utilisateur_id=?" ;
        ArrayList<AnnonceTrouvee> list = new ArrayList();
        try 
        { 
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
                String  colier= rs.getString("colier");         
                Date date_trouvee=rs.getDate("date_trouvee");
                String lieu_trouve=rs.getString("lieu_trouve") ;

                int id_utilisateur=rs.getInt("utilisateur_id");
                  File images=new File(rs.getString("images"));
               
                //////////////////////////////////////////////////
                
            list.add(new AnnonceTrouvee( colier,  date_trouvee,  lieu_trouve,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date,id_utilisateur,images));
        }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       return list ; 
    }
    public ArrayList<AnnonceTrouvee> getList(){
        String req="SELECT * FROM annonce a where a.type_annonce LIKE 'annonce_trouvee'" ;
        ArrayList<AnnonceTrouvee> list = new ArrayList();
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
                String  colier= rs.getString("colier");         
               Date date_trouvee=rs.getDate("date_trouvee");
                String lieu_trouve=rs.getString("lieu_trouve") ;
                int id_utilisateur=rs.getInt("utilisateur_id");
                File images=new File(rs.getString("images"));
               
         //////////////////////////////////////////////////
                

        
             list.add(new AnnonceTrouvee( colier,  date_trouvee,  lieu_trouve,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date,id_utilisateur,images));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       return list ; 
    }
    
      public void updateage (int valeur,int id)
    {
    String req="UPDATE `annonce` SET age=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }  
        
          public void updatecouleur (String valeur,int id)
    {
    String req="UPDATE `annonce` SET couleur=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }      
          
           public void updatesexe (String valeur,int id)
    {
    String req="UPDATE `annonce` SET sex=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }      
        
       public void updaterace (String valeur,int id)
    {
    String req="UPDATE `annonce` SET race=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }      

         public void updatemessage (String valeur,int id)
    {
    String req="UPDATE `annonce` SET message_complementaire=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }      
    
         public void updatelieutrouve (String valeur,int id)
    {
    String req="UPDATE `annonce` SET lieu_trouve=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }      
         
         
           public void updatecolier (String valeur,int id)
    {
    String req="UPDATE `annonce` SET colier=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,valeur) ;
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }      
    


    
}
