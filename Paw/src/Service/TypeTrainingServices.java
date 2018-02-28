/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AnnonceSitting;
import Entity.TypeTraining;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TypeTrainingServices {
    protected Connection connection;
    private DbHandler handler;
    
    public TypeTrainingServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
    public void inserer (String s)
    {
        
        String req="INSERT INTO typetraining (type) VALUES(?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,s) ; 
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }
        
    }
         public ObservableList<TypeTraining> getAll1(){
        String req="SELECT * FROM typetraining " ;
        ObservableList<TypeTraining> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String  type= rs.getString("type");


               
        
              list.add(new TypeTraining( id ,type));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
        public ObservableList<String> getString(){
        String req="SELECT * FROM typetraining " ;
        ObservableList<String> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                String  type= rs.getString("type");


               
        
              list.add(type);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
        
     public void update(TypeTraining a )
    {
    String req="UPDATE typeTraining SET type=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
           ste.setString(1,a.getType()) ; 
          ste.setInt(2, a.getId());
           ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    public void Delete (int id )
    {
    String req="DELETE  from typeTraining where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
           
           
        } catch (SQLException ex) {
            System.out.println("Problème delete Annonce");
        }
        
    

    }
}
