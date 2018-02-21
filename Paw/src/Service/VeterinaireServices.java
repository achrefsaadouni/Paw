/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Veterinaire;
import Entity.Vets;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gmehd
 */
public class VeterinaireServices {

    protected Connection connection;
    private DbHandler handler;

    public VeterinaireServices() {
        handler = DbHandler.getDBHandler();
        connection = handler.getConnection();
    }

    public void insererVeterinaire(Veterinaire p) {
        String req = "INSERT INTO Veterinaire (nom,prenom,adresse,region,numero,email) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getAdresse());
            ste.setString(4, p.getRegion());
            ste.setInt(5, p.getNumero());
            ste.setString(6, p.getEmail());

            //
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ObservableList<Veterinaire> getAll() {
        String req = "SELECT * FROM Veterinaire";
        ObservableList<Veterinaire> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String region = rs.getString("region");
                int numero = rs.getInt("numero");
                String email = rs.getString("email");

                list.add(new Veterinaire(id, nom, prenom, adresse, region, numero, email));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Veterinaire");
        }
        return list;
    }


    public ArrayList<Vets> getList() {
        String req = "SELECT * FROM Veterinaire";
        ArrayList list = new ArrayList();
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String region = rs.getString("region");
                int numero = rs.getInt("numero");
                String email = rs.getString("email");

                list.add(new Veterinaire(id, nom, prenom, adresse, region, numero, email));
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Veterinaire");
        }
        ArrayList<Vets> listeRates = new ArrayList();
        for (Iterator<Veterinaire> iterator = list.iterator(); iterator.hasNext();) {
            Veterinaire x = iterator.next();
            listeRates.add(new Vets(x, getVote(x.getId())));
        }
        return listeRates;
    }

    public void updateVeterinaire(Veterinaire p, int id) {
        String req = "UPDATE Veterinaire SET nom=?,prenom=?, adresse=?, region=?, numero=?,email=? WHERE id=?";
        try {
            PreparedStatement ste = connection.prepareStatement(req);

            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getAdresse());
            ste.setString(4, p.getRegion());
            ste.setInt(5, p.getNumero());
            ste.setString(6, p.getEmail());
            ste.setInt(7, id);

            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problème update Veterinaire");
        }

    }

    public void DeleteVeterinaire(int id) {
        String req = "DELETE  from Veterinaire where  id =?";
        try {
            PreparedStatement ste = connection.prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problème delete Veterinaire");
        }

    }

    private Float getVote(int id) {
        String req = "SELECT avg(valeur) FROM rating where id_veterinaire=?";
        Float y = 0f;
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                y = rs.getFloat(1);
            }

        } catch (SQLException ex) {
            System.out.println("Problème importation liste Veterinaire");
        }
        return y;
    }
    
    public void setRating(int a, int b , int c) {
        String req = "INSERT INTO rating(id_veterinaire, id_utilisateur, valeur) VALUES(?,?,?)";
        try {
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, a);
            ste.setInt(2, b);
            ste.setInt(3, c);
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problème rating");
        }

    }
}
