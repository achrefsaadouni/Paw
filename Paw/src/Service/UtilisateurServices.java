/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Utilisateur;
import Utility.Checksum;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author AYOUB
 */
public class UtilisateurServices {
    protected Connection connection;
    private DbHandler handler;
    
    public UtilisateurServices (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    public void insererUtilisateur (Utilisateur p)
    {
        String image = "";
        String req="INSERT INTO utilisateur (nom,prenom,email,username,password,addresse,numero,role,sexe,dateInscription,avatar,code,confirmed,username_canonical,email_canonical,enabled,roles) VALUES(?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,1,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,p.getNom().toLowerCase()) ; 
            ste.setString(2,p.getPrenom().toLowerCase()) ;
            ste.setString(3,p.getEmail().toLowerCase()) ; 
            ste.setString(4,p.getUsername().toLowerCase()) ; 
            ste.setString(5,MD5(p.getPassword())) ; 
            ste.setString(6,p.getAddresse()) ; 
            ste.setInt(7,p.getNumero()) ; 
            ste.setString(8,"a:0:{}") ;
            ste.setString(9,p.getSexe()) ; 
            ste.setString(10,p.getAvatar()) ;
            ste.setString(11,"Free"+p.getCode()) ; 
            ste.setString(12,p.getConfirmed()) ;
            ste.setString(13,p.getUsername().toLowerCase()) ;
            ste.setString(14,p.getEmail().toLowerCase()) ;
            ste.setString(15,"a:0:{}");
            
              ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
    }
    
    public ObservableList<Utilisateur> getAll(){
        String req="SELECT * FROM utilisateur" ;
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String addresse = rs.getString("addresse");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("roles");
                int numero = rs.getInt("numero");
                list.add(new Utilisateur(id, nom, prenom, email, username, password,addresse,numero,role));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
     
    
        public List<Utilisateur> findAll(){
        String req="SELECT * FROM utilisateur" ;
        List<Utilisateur> list = FXCollections.observableArrayList();
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String username = rs.getString("username");
                list.add(new Utilisateur(id, nom, prenom, email, username, null,null,0,null));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public void updateUtilisateur (Utilisateur p, int id )
    {
    String req="UPDATE utilisateur SET nom=?,prenom=?, email=?, username=?, password=?,addresse=?,numero=?,roles=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setString(1,p.getNom()) ; 
            ste.setString(2,p.getPrenom()) ; 
            ste.setString(3,p.getEmail()) ; 
            ste.setString(4,p.getUsername()) ; 
            ste.setString(5,DigestUtils.shaHex(p.getPassword())) ; 
            ste.setString(6,p.getAddresse()) ; 
            ste.setString(8,p.getRole()) ; 
            ste.setInt(7,p.getNumero()) ;
            ste.setInt(9,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
     public void DeleteUtilisateur (int id )
    {
    String req="DELETE  from utilisateur where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
      }
     
     
       public Utilisateur rechercher(int id){
        String req="SELECT * FROM utilisateur where id=?" ;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String addresse = rs.getString("addresse");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("roles");
                String sexe = rs.getString("sexe");
                int numero = rs.getInt("numero");
                return new Utilisateur(id, nom, prenom, email, username, password,addresse,numero,role,sexe);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
       
       public Utilisateur find(int id){
        String req="SELECT * FROM utilisateur where id=?" ;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String addresse = rs.getString("addresse");
                String email = rs.getString("email");
                String avatar = rs.getString("avatar");
                String sexe = rs.getString("sexe");
                int numero = rs.getInt("numero");
                return new Utilisateur(nom, prenom, addresse, email, sexe, numero, avatar);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
       
       public ArrayList<Utilisateur> getListePourAdmin(int i){
        String req="SELECT * FROM utilisateur where id not like ?" ;
        ArrayList<Utilisateur> list = new ArrayList();
        
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String addresse = rs.getString("addresse");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("roles");
                int numero = rs.getInt("numero");
                String avatar = rs.getString("avatar");
                Date dateInscription = rs.getDate("dateInscription");
                String sexe=rs.getString("sexe");
                String code=rs.getString("code");
                String confirmed=rs.getString("confirmed");
                list.add(new Utilisateur(id, nom, prenom, addresse, email, username, password, role, sexe, numero, avatar, dateInscription,code,confirmed));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Boolean rendreAdmin(int id) {
        String req="UPDATE utilisateur SET roles=? , role=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
            ste.setString(2, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
            ste.setInt(3,id) ;
            ste.executeUpdate() ;  
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public Boolean rendreMembre(int id) {
        String req="UPDATE utilisateur SET roles=? , role=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, "a:0:{}");
            ste.setString(2, "a:0:{}");
            ste.setInt(3,id) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public boolean bloquer(int id,String code) {
        String req="UPDATE utilisateur SET enabled=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;   
            ste.setInt(1,0);
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public boolean debloquer(int id,String code) {
       String req="UPDATE utilisateur SET enabled=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,1);
            ste.setInt(2,id) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public Boolean modifierInfos(Utilisateur u) {
        String req="UPDATE utilisateur SET nom=?, prenom=?, addresse=?, sexe=?,numero=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, u.getNom());
            ste.setString(2, u.getPrenom());
            ste.setString(3, u.getAddresse());
            ste.setString(4, u.getSexe());
            ste.setInt(5,u.getNumero()) ;
            ste.setInt(6,u.getId()) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }
    
    public void utilisateurMois(int i)
    {
        ArrayList<String> y = new ArrayList();
        String req="SELECT extract(month from systimestamp-dateInscription) as t FROM utilisateur" ;
        try 
        { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            //ste.setInt(1, i);
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                System.out.println(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public int nombre() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `utilisateur` where role='Membre'";
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

    public int nombreNonConfirmes() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `utilisateur` where confirmed='0' and roles=?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, "a:0:{}");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }
    public int nbrConfirmes() {
        int y = 0;
        String sql = "SELECT count(*) as nbr FROM `utilisateur` where confirmed='1' and roles=?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, "a:0:{}");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                y = results.getInt("nbr");
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage nombre");
        }
        return y;
    }
    public void confirmer(int id) {
        String req="UPDATE utilisateur SET code='Free', confirmed='yes' WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 

        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
