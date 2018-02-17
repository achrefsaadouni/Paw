/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Reclamation;
import Entity.RepRec;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author AYOUB
 */
public class RepRecServices {
    protected Connection connection;
    private DbHandler handler;
    
    public RepRecServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
    public void insererRepReclamation (RepRec a)
    {
        String req="INSERT INTO reponsereclamation (id_reclamation,text,date) VALUES(?,?,now())" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,a.getId_reclamation()) ; 
            ste.setString(2,a.getText()) ; 

            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public RepRec getReponse(int i) {
        String req="SELECT * FROM reponsereclamation WHERE id_reclamation=?" ;
        RepRec x= null;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String text= rs.getString("text");
                java.sql.Timestamp date = rs.getTimestamp("date");
                              
                x=new RepRec(id,i, text,date);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;
    }
    
}
