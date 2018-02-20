
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnoncePerdu;
import Entity.Produit;
import Entity.Utilisateur;
import Entity.Veterinaire;
import Entity.Vets;
//import Entity.Annonce ;
import Utility.DbHandler;
import com.mysql.jdbc.StringUtils;
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utility.Checksum;

/**
 *
 * @author Guideinfo
 */
public class AnnoncePerduServices
{
    protected Connection connection;
    private DbHandler handler;
    
    public AnnoncePerduServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
     
    
        public List<File> getFiles(String path)
        {
            List<File> res = new ArrayList<>();
            List<String>list = StringUtils.split(path,";",true);
            list.stream().map((p) -> new File(p)).forEachOrdered((file) -> {
                res.add(file);
        });
           
            return res;
        }
        
      
    
    
    
    public void insererAnnoncePerdu (AnnoncePerdu a)
    {
         String images="";

        images=a.getImages().getPath().toString();
        String req="INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date,type_annonce,colier,date_perte,lieu_perdu,utilisateur_id,images) VALUES(?,?,?,?,?,?,now(),?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getRace()) ;
            ste.setString(5,a.getMessage_complementaire()) ;
            ste.setString(6,a.getType()) ;
            ste.setString(7,"annonce_perte");
            ste.setString(8, a.getColier());
            ste.setDate(9, (java.sql.Date) a.getDate_perte());
            ste.setString(10, a.getLieu_perdu());
            ste.setInt(11, a.getId_utilisateur());
             ste.setString(12,images) ; 
                           
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
    
     
     
    public ObservableList<AnnoncePerdu> getAll1(){
        String req="SELECT * FROM annonce WHERE type_annonce LIKE 'annonce_perte'" ;
        ObservableList<AnnoncePerdu> list = FXCollections.observableArrayList();
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
                Timestamp date_perte=rs.getTimestamp("date_perte");
                String lieu_perdu=rs.getString("lieu_perdu") ;
                int id_utilisateur=rs.getInt("utilisateur_id");
                
               
        
            list.add(new AnnoncePerdu( colier,  date_perte,  lieu_perdu,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date,id_utilisateur));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
     
    public ArrayList<AnnoncePerdu> getList(){
        String req="SELECT * FROM annonce a where a.type_annonce LIKE 'annonce_perte'" ;
        ArrayList<AnnoncePerdu> list = new ArrayList();
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
                Timestamp date_perte=rs.getTimestamp("date_perte");
                String lieu_perdu=rs.getString("lieu_perdu") ;
                int id_utilisateur=rs.getInt("utilisateur_id");
                File images=new File(rs.getString("images"));
                //////////////////////////////////////////////////
                

        
             list.add(new AnnoncePerdu( colier,  date_perte,  lieu_perdu,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date,id_utilisateur,images));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       return list ; 
    }
    
    
    
     public ArrayList<Utilisateur> getUtilisateurs(int id){
        String req="SELECT * FROM utilisateur where id =?" ;
       ArrayList list = new ArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id_u=rs.getInt("id");
               String nom_u = rs.getString("nom");
               String prenom_u= rs.getString("prenom");
               int numero_u =rs.getInt("numero");
               String email_u=rs.getString("email");
               String adresse_u=rs.getString("addresse");
                list.add(new Utilisateur(id_u,nom_u,prenom_u,numero_u,email_u,adresse_u));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }    
    
    public void updateAnnoncePerdu (AnnoncePerdu a)
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?,colier=?,lieu_perdu=?  WHERE id =?" ; 
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
             ste.setString(8,a.getLieu_perdu()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
     public void DeleteAnnoncePerdu (int id )
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

       public ArrayList<AnnoncePerdu> getMesAnnoncesPerdus(int i){
        String req="SELECT * FROM annonce where type_annonce LIKE 'annonce_perte' and utilisateur_id=?" ;
        ArrayList<AnnoncePerdu> list = new ArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
               // String colier, Date date_perte,String lieu_perdu , int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date
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
                Date date_trouvee=rs.getDate("date_perte");
                Timestamp date_perte=rs.getTimestamp("date_perte");
                String lieu_perdu=rs.getString("lieu_perdu") ;
                int id_utilisateur=rs.getInt("utilisateur_id");
                 File images=new File(rs.getString("images"));
                
                //////////////////////////////////////////////////
                
            list.add(new AnnoncePerdu( colier,  date_perte,  lieu_perdu,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date,id_utilisateur,images));
        }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       return list ; 
    }
    

    
}
