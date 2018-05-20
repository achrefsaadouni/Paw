/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceAccouplement;
import Utility.Checksum;
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
 * @author gmehd
 */
public class AnnonceAccouplementServices {

    protected Connection connection;
    private DbHandler handler;

    public AnnonceAccouplementServices() {
        handler = DbHandler.getDBHandler();
        connection = handler.getConnection();
    }

    public void insererAnnonceAccouplement(AnnonceAccouplement a) {
        String images = "";
       images = imageSave(a.getImages());
        String req = "INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date,type_annonce,lieu_trouve,type_poil,vaccin,dossier,images,utilisateur_id) VALUES(?,?,?,?,?,?,now(),?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, a.getAge());
            ste.setString(2, a.getCouleur());
            ste.setString(3, a.getSex());
            ste.setString(4, a.getRace());
            ste.setString(5, a.getMessage_complementaire());
            ste.setString(6, a.getType());
            ste.setString(7, "annonce_accouplement");
            ste.setString(8,a.getLieu());
            ste.setString(9, a.getType_poil());
            ste.setString(10, a.getVaccin());
            ste.setString(11, a.getDossier());
            ste.setString(12, images);
            ste.setInt(13, a.getId_utilisateur());
            
            
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
    public String imageSave(File file) {
           
        try {
            String imageName = Checksum.createChecksum(file.getAbsolutePath());
            String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
            String filePath = "E:\\xampp\\htdocs\\paw_web\\web\\images\\pawPets\\" + imageName + extension;
            
            
            //
            File dest = new File(filePath);
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return imageName + extension;
        } catch (Exception ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public ObservableList<AnnonceAccouplement> getAll1() {
        String req = "SELECT * FROM annonce WHERE type_annonce LIKE 'annonce_accouplement'";
        ObservableList<AnnonceAccouplement> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String couleur = rs.getString("couleur");
                String sex = rs.getString("sex");
                String race = rs.getString("race");
                String message_complementaire = rs.getString("message_complementaire");
                String type = rs.getString("type");
                Timestamp date = rs.getTimestamp("date");
                String type_annonce = rs.getString("type_annonce");
                String lieu = rs.getString("lieu_trouve");
                String type_poil = rs.getString("type_poil");
                String vaccin = rs.getString("vaccin");
                String dossier = rs.getString("dossier");
                File images=new File(rs.getString("images"));
                int id_utilisateur=rs.getInt("utilisateur_id");
                
                list.add(new AnnonceAccouplement(type_poil, vaccin, dossier, lieu, id, age, couleur, sex, race, message_complementaire, type, date, images, id_utilisateur));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
    
    public ArrayList<AnnonceAccouplement> getList() {
        String req = "SELECT * FROM annonce WHERE type_annonce LIKE 'annonce_accouplement'";
        ArrayList<AnnonceAccouplement> list = new ArrayList();
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String couleur = rs.getString("couleur");
                String sex = rs.getString("sex");
                String race = rs.getString("race");
                String message_complementaire = rs.getString("message_complementaire");
                String type = rs.getString("type");
                Timestamp date = rs.getTimestamp("date");
                String type_annonce = rs.getString("type_annonce");
                String lieu = rs.getString("lieu_trouve");
                String type_poil = rs.getString("type_poil");
                String vaccin = rs.getString("vaccin");
                String dossier = rs.getString("dossier");
                File images=new File(rs.getString("images"));
                int id_utilisateur=rs.getInt("utilisateur_id");
                
                list.add(new AnnonceAccouplement(type_poil, vaccin, dossier, lieu, id, age, couleur, sex, race, message_complementaire, type, date, images, id_utilisateur));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
    
    public ArrayList<AnnonceAccouplement> getMesAnnoncesAccouplements(int i){
        String req="SELECT * FROM annonce where type_annonce LIKE 'annonce_accouplement' and utilisateur_id=?" ;
        ArrayList<AnnonceAccouplement> list = new ArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
               // String colier, Date date_perte,String lieu_Accouplement , int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date
               int id = rs.getInt("id");
                int age = rs.getInt("age");
                String couleur = rs.getString("couleur");
                String  sex= rs.getString("sex");
                String  race= rs.getString("race");
                String  message_complementaire= rs.getString("message_complementaire");
                String  type= rs.getString("type");
                Timestamp date=rs.getTimestamp("date");
                String  type_annonce= rs.getString("type_annonce");
                String lieu=rs.getString("lieu_trouve") ;
             
                String  type_poil= rs.getString("type_poil");
                String  vaccin= rs.getString("vaccin");
                String  dossier= rs.getString("dossier");
                
                int id_utilisateur=rs.getInt("utilisateur_id");
                File images=new File(rs.getString("images"));
                
                //////////////////////////////////////////////////
                
            list.add(new AnnonceAccouplement(type_poil, vaccin, dossier, lieu, id, age, couleur, sex, race, message_complementaire, type, date, images, id_utilisateur));
        }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       return list ; 
    }

    public void updateAnnonceAccouplement(AnnonceAccouplement a, int id) {
        String req = "UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?,lieu_trouve=?,type_poil=?,vaccin=?,dossier=?  WHERE id =?";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            
            ste.setInt(1, a.getAge());
            ste.setString(2, a.getCouleur());
            ste.setString(3, a.getSex());
            ste.setString(4, a.getRace());
            ste.setString(5, a.getMessage_complementaire());
            ste.setString(6, a.getType());
            ste.setString(7, a.getLieu());
            ste.setString(8, a.getType_poil());
            ste.setString(9, a.getVaccin());
            ste.setString(10, a.getDossier());

            ste.setInt(11, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void DeleteAnnonceAccouplement(int id) {
        String req = "DELETE  from annonce where  id =?";
        try {
            PreparedStatement ste = connection.prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }

    }
 public int nombre() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `annonce` where type_annonce LIKE 'annonce_accouplement'";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }
 public int getAnnonceAcc(int id) {
        String req="SELECT count(*) as a FROM annonce WHERE type_annonce like 'annonce_accouplement' where id=?" ;
        int x=0;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;

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

    public int nombreMesAnnonces(int id) {
        int y=0;    
            String req="SELECT * FROM annonce WHERE utilisateur_id=? and type_annonce like 'annonce_accouplement'" ;
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
