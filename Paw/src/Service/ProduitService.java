/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Produit;
import Utility.DbHandler;
import com.mysql.jdbc.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Checksum;

/**
 *
 * @author vinga
 */
public class ProduitService {
    
    final private DbHandler handler;
    protected Connection connection;
    private static ProduitService produitservice;
    
    
    public static ProduitService getProduitService() {
        if (ProduitService.produitservice == null) {
            produitservice = new ProduitService();
        }
         return produitservice;
     }
    
    
    public ProduitService() {
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
    }
    
    public void addProduit(Produit p)
    {
        String images="";
        images = p.getImages().stream().map((i) -> imageSave(i)+";").reduce(images, String::concat);
        String req="INSERT INTO `produit` (`libelle`,`prix`,`quantite`,`description`,`type`,`images`) VALUES(?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setString(1,p.getLibelle()) ;
            ste.setFloat(2,p.getPrix());
            ste.setInt(3,p.getQuantite()) ; 
            ste.setString(4,p.getDescription()) ; 
            ste.setString(5,p.getType()) ; 
            ste.setString(6,images) ; 
            ste.executeUpdate() ; 
        } catch (SQLException ex) {
            System.out.println("Problème insertion Produit");
        }
        
    }
    
    public void deleteProduit (int id )
    {
    String req="DELETE  from produit where  id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
            ste.setInt(1,id) ;
            Produit p = rechercher(id);
            ste.executeUpdate() ; 
            Files.delete(p.getImages().get(0).toPath());
            Files.delete(p.getImages().get(1).toPath());
        } catch (NoSuchFileException x) {
           System.err.format("no such file or directory");
       } catch (DirectoryNotEmptyException x) {
           System.err.format("not empty");
       } catch (IOException x) {
           System.err.println(x);
        }catch (SQLException ex1) {
            System.out.println("Problème delete produit");
        }
    
    }
    

     public void updateProduit (Produit p)
    {
    String req="UPDATE personne SET libelle=?,prix=?,quantite=?,description=?,type=?,images=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = connection.prepareStatement(req) ;
             
            ste.setString(1,p.getLibelle()) ;
            ste.setFloat(2,p.getPrix());
            ste.setInt(3,p.getQuantite()) ; 
            ste.setString(4,p.getDescription()) ; 
            ste.setString(5,p.getType()) ; 
            ste.setString(6,"aa") ;
            ste.setInt(7,p.getId_produit());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println("Problème update Personne");
        }
    
    }
     
        public ArrayList<Produit> findAll() {
        String sql = "SELECT * FROM `produit`";
         try {
             PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet results =  statement.executeQuery();
             ArrayList<Produit> produits = new ArrayList<>();
             Produit produit;
             while (results.next()) {
                 produit = new Produit(results.getInt("id"),results.getString("libelle"),results.getFloat("prix"),results.getInt("quantite"),results.getString("description"),results.getString("type"));
                 produit.setImages(getFiles(results.getString("images")));
                 produits.add(produit);
             }
             return produits;
         } catch (SQLException ex) {
             System.out.println("erreur affichage produit");
         }
        return null;
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
    public Produit rechercher(int id)
    {
        Produit produit =null;
        String sql = "SELECT * FROM `produit` where id=?";
         try {
             PreparedStatement statement = this.connection.prepareStatement(sql);
              statement.setInt(1,id) ;
             ResultSet results =  statement.executeQuery();
             
             while (results.next()) {
                 produit = new Produit(results.getInt("id"),results.getString("libelle"),results.getFloat("prix"),results.getInt("quantite"),results.getString("description"),results.getString("type"));
                 produit.setImages(getFiles(results.getString("images")));
             }
             
         } catch (SQLException ex) {
             System.out.println("erreur affichage produit");
         }
        return produit;
    }
    
}
