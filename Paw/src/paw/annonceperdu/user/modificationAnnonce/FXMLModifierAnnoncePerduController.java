/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu.user.modificationAnnonce;

import Entity.AnnoncePerdu;
import Entity.Produit;
import Entity.Reclamation;
import Entity.Utilisateur;
import Service.AnnoncePerduServices;
import Service.ProduitService;
import Service.ReclamationServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.converter.NumberStringConverter;
import paw.MyNotifications;

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
    private TreeTableColumn<AnnoncePerdu,Number> age;

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

     private AnnoncePerduServices annonceservice;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     initAnnoncePerdu() ; 
    }    

    private void initAnnoncePerdu() {
         consultertable.setEditable(true);

        annonceservice = AnnoncePerduServices.getannonceService() ;
      
      AnnoncePerduServices servicePerdu = new AnnoncePerduServices();
        liste= servicePerdu.getAll1();
        System.out.println(liste.size());
        UtilisateurServices serviceUtil = new UtilisateurServices();

       age.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            AnnoncePerdu annoncePerdu = (AnnoncePerdu) param.getValue().getValue();
            property.setValue(annoncePerdu.getAge());
            return property;
        });

       age.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new NumberStringConverter()));
        age.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, Number>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, Number> event) {
                TreeItem<AnnoncePerdu> curseur = consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                
                annonceservice.updateage(event.getNewValue().intValue(), curseur.getValue().getId());
                System.out.println();
                MyNotifications.infoNotification("Modification", "quantite edite avec Succès");
            }
        });
    
    
        couleur.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getCouleur()));
            return property;
        });
         couleur.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        couleur.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, String> event) {
                TreeItem<AnnoncePerdu> curseur =consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                if (event.getNewValue().isEmpty())
                {
                    MyNotifications.infoNotification("Modification", "couleur Ne peut pas etre Vide");
                }else{   
                annonceservice.updatecouleur(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "description edite avec Succès");
                }
            }
        });

        
        
            sexe.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getSex()));
            return property;
        });
            
            sexe.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        sexe.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, String> event) {
                TreeItem<AnnoncePerdu> curseur =consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                if (event.getNewValue().isEmpty())
                {
                    MyNotifications.infoNotification("Modification", "sexe Ne peut pas etre Vide");
                }else{   
                annonceservice.updatesexe(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "sexe edite avec Succès");
                }
            }
        });

        
            
             
             
     race.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getRace()));
            return property;
        });
     
      race.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
       race.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, String> event) {
                TreeItem<AnnoncePerdu> curseur =consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                if (event.getNewValue().isEmpty())
                {
                    MyNotifications.infoNotification("Modification", "race Ne peut pas etre Vide");
                }else{   
                annonceservice.updaterace(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "race edite avec Succès");
                }
            }
        });

     msg.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getMessage_complementaire()));
            return property;
        });
     
     msg.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
       msg.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, String> event) {
                TreeItem<AnnoncePerdu> curseur =consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                if (event.getNewValue().isEmpty())
                {
                    MyNotifications.infoNotification("Modification", "le message complementaire Ne peut pas etre Vide");
                }else{   
                annonceservice.updatemessage(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "Le message complementaire est edite avec Succès");
                }
            }
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
    
        colier.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
      colier.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, String> event) {
                TreeItem<AnnoncePerdu> curseur =consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                if (event.getNewValue().isEmpty())
                {
                    MyNotifications.infoNotification("Modification", "le message complementaire Ne peut pas etre Vide");
                }else{   
                annonceservice.updatecolier(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "Le message complementaire est edite avec Succès");
                }
            }
        });
     
     lieup.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getLieu_perdu()));
            return property;
        });
     
    lieup.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
      lieup.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<AnnoncePerdu, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<AnnoncePerdu, String> event) {
                TreeItem<AnnoncePerdu> curseur =consultertable.getTreeItem(event.getTreeTablePosition().getRow());
                if (event.getNewValue().isEmpty())
                {
                    MyNotifications.infoNotification("Modification", "le message complementaire Ne peut pas etre Vide");
                }else{   
                annonceservice.updatelieuPerte(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "Le message complementaire est edite avec Succès");
                }
            }
        });
     
    
    
    ObservableList<AnnoncePerdu> annonceperdus = FXCollections.observableArrayList(liste);
        TreeItem<AnnoncePerdu> root = new RecursiveTreeItem<>(annonceperdus, RecursiveTreeObject::getChildren);
        consultertable.setRoot(root);
       consultertable.setShowRoot(false);
    
    
    }

    @FXML
    private void delete(ActionEvent event) {
   
    // consultertable.getSelectionModel().getSelectedItems().removeAll(liste) ; 
    }
    
}
