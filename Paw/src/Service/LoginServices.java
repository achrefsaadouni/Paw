/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Utilisateur;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AYOUB
 */
public class LoginServices {
    protected Connection connection;
    private DbHandler handler;
    
    public LoginServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public int Valide (String usern,String passw)
    {
        String req="SELECT * FROM utilisateur where username=? and password=?" ;
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,usern) ; 
            ste.setString(2,MD5(passw)) ;     
            ResultSet rs = ste.executeQuery(); 
            if (rs.next())
            {
                if (rs.getString("enabled").startsWith("0"))
                {
                    return -2;
                }
                else{
                    return rs.getInt("id");
                }
            }
            else
            {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    public Utilisateur getInformation (int i)
    {
        String req="SELECT * FROM utilisateur where id=?" ;
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,i) ;      
            ResultSet rs = ste.executeQuery(); 
            rs.next();
            int id=rs.getInt("id");
            String nom=rs.getString("nom");
            String prenom=rs.getString("prenom");
            String addresse=rs.getString("addresse");
            String email=rs.getString("email");
            String username=rs.getString("username");
            String password=rs.getString("password");
            String role=rs.getString("roles");
            String avatar=rs.getString("avatar");
            String sexe=rs.getString("sexe");
            Date dateInscription=rs.getDate("dateInscription");
            int numero=rs.getInt("numero");
            String code=rs.getString("code");
            String confirmed=rs.getString("enabled");
            return new Utilisateur(
                    id,
                    nom,
                    prenom,
                    addresse,
                    email,
                    username, 
                    password, 
                    role,
                    sexe,
                    numero, 
                    avatar,
                    dateInscription,
                    code,
                    confirmed);
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
           }
            return sb.toString();
        } 
        catch (java.security.NoSuchAlgorithmException e) {
        }
            return null;
        }
    }
