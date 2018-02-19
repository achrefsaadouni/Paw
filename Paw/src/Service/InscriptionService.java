/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AYOUB
 */
public class InscriptionService {
    protected Connection connection;
    private DbHandler handler;
    
    public InscriptionService (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public boolean EmailNonExistant(String x){
        String req="SELECT * FROM utilisateur where email like ?" ;
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,x.toLowerCase()) ;    
            ResultSet rs = ste.executeQuery(); 
            if (rs.next())
            {
                return false;
            }
            else
            {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean UsernameNonExistant(String x) {
        String req="SELECT * FROM utilisateur where username like ?" ;
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,x.toLowerCase()) ;    
            ResultSet rs = ste.executeQuery(); 
            if (rs.next())
            {
                return false;
            }
            else
            {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
}
