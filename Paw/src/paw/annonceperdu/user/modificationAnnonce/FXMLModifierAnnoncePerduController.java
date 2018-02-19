/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu.user.modificationAnnonce;

import Entity.AnnoncePerdu;
import Entity.Reclamation;
import Entity.Utilisateur;
import Service.AnnoncePerduServices;
import Service.ReclamationServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLModifierAnnoncePerduController implements Initializable {

     List<AnnoncePerdu> liste;
    @FXML
    private JFXTreeTableView<AnnoncePerdu> consultertable;

    @FXML
    private TreeTableColumn<AnnoncePerdu, String> age;

    @FXML
    private TreeTableColumn<AnnoncePerdu, String> couleur;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> sexe;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> race;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> msg;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> date;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> colier;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> datep;

    @FXML
    private TreeTableColumn<AnnoncePerdu,String> lieup;
    @FXML
    private TreeTableColumn<AnnoncePerdu, JFXButton> modifier;
    @FXML
    private TreeTableColumn<AnnoncePerdu, JFXButton> supprimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     initAnnoncePerdu() ; 
    }    

    private void initAnnoncePerdu() {
      
      AnnoncePerduServices servicePerdu = new AnnoncePerduServices();
        liste= servicePerdu.getAll1();
        System.out.println(liste.size());
        UtilisateurServices serviceUtil = new UtilisateurServices();

        age.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getAge()));
            return property;
        });
    
    
        couleur.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getCouleur()));
            return property;
        });
            sexe.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getSex()));
            return property;
        });
             
             
     race.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getRace()));
            return property;
        });
     msg.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getMessage_complementaire()));
            return property;
        });
     date.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getDate()));
            return property;
        });
     datep.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getDate_perte()));
            return property;
        });
    
    
     colier.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getColier()));
            return property;
        });
    
     lieup.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getLieu_perdu()));
            return property;
        });
     
    
    
    ObservableList<AnnoncePerdu> annonceperdus = FXCollections.observableArrayList(liste);
        TreeItem<AnnoncePerdu> root = new RecursiveTreeItem<>(annonceperdus, RecursiveTreeObject::getChildren);
        consultertable.setRoot(root);
       consultertable.setShowRoot(false);
    
    
    }
    
}
