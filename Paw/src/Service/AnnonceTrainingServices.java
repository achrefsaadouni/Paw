package Service;

import Entity.Annonce;
import Entity.AnnonceTraining;
import Utility.DbHandler;
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
 * @author Lenovo
 */

public class AnnonceTrainingServices {
    protected Connection connection;
    private DbHandler handler;
    
    public AnnonceTrainingServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererAnnonceTraining (AnnonceTraining a)
    {
        String req="INSERT INTO annonce (age,couleur,sex,type,race,message_complementaire,nomPet,dateTr,typeTr,type_annonce,date,utilisateur_id) VALUES(?,?,?,?,?,?,?,?,?,?,now(),?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getAge()) ; 
            ste.setString(2,a.getCouleur()) ; 
            ste.setString(3,a.getSex()) ; 
            ste.setString(4,a.getTypePet()) ; 
            ste.setString(5,a.getRace()) ;
            ste.setString(6,a.getMessage_complementaire()) ;
            ste.setString(7,a.getNomPet()) ;
            ste.setDate(8, (java.sql.Date) a.getDateTr());
            ste.setString(9,a.getTypeTr());
            ste.setString(10,a.getType());
            ste.setInt(11,a.getId_utilisateur()) ; 
            
                System.out.println("avant");
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
    }
     public ObservableList<AnnonceTraining> getAll1(){
        String req="SELECT * FROM annoncetr" ; //WHERE typeSit LIKE 'QuickVisit'" ;
        ObservableList<AnnonceTraining> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                int agePet = rs.getInt("age");
                String colorPet = rs.getString("couleur");
                String  sexePet= rs.getString("sex");
                String typePet=rs.getString("typePet");
                String  racePet= rs.getString("race");
                String  descPet= rs.getString("message_complementaire");
                String nomPet = rs.getString("nomPet");
                int id_utilisateur = rs.getInt("utilisateur_id");
                Date  dateTr= rs.getDate("dateTr");
                String  typeTr= rs.getString("typeTr");
                String  type= rs.getString("type");
                Timestamp date=rs.getTimestamp("date");
                

               
        
              list.add(new AnnonceTraining( dateTr,typeTr,typePet,nomPet,id,agePet,colorPet,sexePet,racePet,descPet,type,date,id_utilisateur));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
    
    public void updateAnnonceTraining (AnnonceTraining a, int id )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,type=?,race=?,message_complementaire=?,nomPet=?,dateTr=?,typeTR=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(10,id) ;
           ste.setInt(1,a.getAge()) ; 
           ste.setString(2,a.getCouleur()) ; 
           ste.setString(3,a.getSex()) ; 
           ste.setString(4,a.getType()) ; 
           ste.setString(5,a.getRace()) ; 
           ste.setString(6,a.getMessage_complementaire()) ; 
           ste.setString(7, a.getNomPet());
           ste.setDate(8, (java.sql.Date) a.getDateTr()) ;  
           ste.setString(9,a.getTypeTr()) ; 
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
     public void DeleteAnnonceTraining (int id )
    {
    String req="DELETE  from annoncetr where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
           
        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }
        
    

    }
    
      public ArrayList<AnnonceTraining> getAnnonceTraining() {
        String req="SELECT * FROM annonce WHERE type_annonce like 'Annonce Training'" ;
        ArrayList<AnnonceTraining> list = new ArrayList();
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
                String  typePet= rs.getString("type");
                Timestamp date=rs.getTimestamp("date");
                String  typeAnnonce= rs.getString("type_annonce");
                //File images=new File(rs.getString("images"));
                //File images=new File("C://Users//AYOUB//Desktop//ayoub.png");
                int id_utilisateur=rs.getInt("utilisateur_id");
                String nomPet=rs.getString("nomPet");
                String typeTr=rs.getString("typeTr");
                Date dateTr = rs.getDate("dateTr");
               
                            
                list.add(new AnnonceTraining(dateTr, typeTr, typePet,nomPet, id, age, couleur, sex, race, message_complementaire, typeAnnonce, date, id_utilisateur));            }

        } catch (SQLException ex) {
             Logger.getLogger(AnnonceAdoptionService.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("getAnnonceTraining");
        }
        return list;      

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
            System.out.println("erreur affichage nombre");
        }
        return y;

    }
    
}
