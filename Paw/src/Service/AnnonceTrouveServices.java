/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceTrouvee;
//import Entity.Annonce ;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
        String req="INSERT INTO annonce (age,couleur,sex,race,message_complementaire,type,date,type_annonce,colier,date_trouvee,lieu_perdu) VALUES(?,?,?,?,?,?,now(),?,?,now(),?)" ; 
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
            ste.setString(9,a.getLieu_perdu());
            
                            System.out.println("avant");
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème insertion annonce");
        }
        
    
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
                String lieu_perdu=rs.getString("lieu_perdu") ;

               
        
              list.add(new AnnonceTrouvee( colier,  date_trouvee,  lieu_perdu,  id, age,  couleur, sex,  race,  message_complementaire,  type,  date));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Annonce");
        }
        return list;
    }
     
    public void updateAnnonceTrouvee (AnnonceTrouvee a, int id )
    {
    String req="UPDATE annonce SET age=?,couleur=?,sex=?,race=?,message_complementaire=?,type=?,colier=?,lieu_perdu=?  WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setInt(9,id) ;
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

    
}
