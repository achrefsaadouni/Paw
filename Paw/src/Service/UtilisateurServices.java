/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Utilisateur;
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
        String req="INSERT INTO utilisateur (nom,prenom,email,username,password,addresse,numero,role,sexe,dateInscription,avatar) VALUES(?,?,?,?,?,?,?,?,?,now(),?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,p.getNom().toLowerCase()) ; 
            ste.setString(2,p.getPrenom().toLowerCase()) ;
            ste.setString(3,p.getEmail().toLowerCase()) ; 
            ste.setString(4,p.getUsername().toLowerCase()) ; 
            ste.setString(5,DigestUtils.shaHex(p.getPassword())) ; 
            ste.setString(6,p.getAddresse()) ; 
            ste.setInt(7,p.getNumero()) ; 
            ste.setString(8,p.getRole()) ;
            ste.setString(9,p.getSexe()) ; 
            ste.setString(10,p.getAvatar()) ;
            
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
                String role = rs.getString("role");
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
    String req="UPDATE utilisateur SET nom=?,prenom=?, email=?, username=?, password=?,addresse=?,numero=?,role=? WHERE id =?" ; 
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
                String role = rs.getString("role");
                String sexe = rs.getString("sexe");
                int numero = rs.getInt("numero");
                return new Utilisateur(id, nom, prenom, email, username, password,addresse,numero,role,sexe);
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
                String role = rs.getString("role");
                int numero = rs.getInt("numero");
                String avatar = rs.getString("avatar");
                Date dateInscription = rs.getDate("dateInscription");
                String sexe=rs.getString("sexe");
                list.add(new Utilisateur(id, nom, prenom, addresse, email, username, password, role, numero, avatar, dateInscription, sexe));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Boolean rendreAdmin(int id) {
        String req="UPDATE utilisateur SET role='Admin' WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ;  
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public Boolean rendreMembre(int id) {
        String req="UPDATE utilisateur SET role='Membre' WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public boolean bloquer(int id) {
        String req="UPDATE utilisateur SET etat='Bloqué' WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public boolean debloquer(int id) {
       String req="UPDATE utilisateur SET etat='Free' WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false ;
        }
    }

    public Boolean modifierInfos(Utilisateur u) {
        String req="UPDATE utilisateur SET email=?, nom=?, prenom=?, addresse=?, sexe=?,numero=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1, u.getEmail());
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setString(4, u.getAddresse());
            ste.setString(5, u.getSexe());
            System.out.println(u.getNumero());
            ste.setInt(6,u.getNumero()) ;
            ste.setInt(7,u.getId()) ;
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
        String req="SELECT extract(month from systimestamp-dateInscription) as terma FROM utilisateur" ;
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
        String sql = "SELECT count(*) as nbr FROM `utilisateur`";
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
