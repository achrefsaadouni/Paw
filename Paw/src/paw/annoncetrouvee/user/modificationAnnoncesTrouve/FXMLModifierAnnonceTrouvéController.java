/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package paw.annoncetrouvee.user.modificationAnnoncesTrouve;

import Entity.AnnoncePerdu;
import Entity.AnnonceTrouvee;
import Entity.Utilisateur;
import Service.AnnoncePerduServices;
import Service.AnnonceTrouveServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
/*public class FXMLModifierAnnonceTrouvéController implements Initializable {

   List<AnnonceTrouvee> liste;
    @FXML
    private JFXTreeTableView<AnnonceTrouvee> consultertable;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,Number> age;

    @FXML
    private TreeTableColumn<AnnonceTrouvee, String> couleur;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> sexe;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> race;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> msg;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> date;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> colier;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> datep;

    @FXML
    private TreeTableColumn<AnnonceTrouvee,String> lieup;

     private AnnonceTrouveServices annonceservice;
    @FXML
    private TreeTableColumn<AnnoncePerdu, JFXButton> supprimer;
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
                MyNotifications.infoNotification("Modification", "quantite modifier avec Succès");
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
                MyNotifications.infoNotification("Modification", "description modifier avec Succès");
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
                MyNotifications.infoNotification("Modification", "sexe modifier avec Succès");
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
                MyNotifications.infoNotification("Modification", "race modifier avec Succès");
                }
            }
        });

       supprimer.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            AnnoncePerdu r = (AnnoncePerdu) param.getValue().getValue();
                JFXButton rep = new JFXButton("Supprimer");
                 rep.setStyle("-fx-background-color:red;");
                rep.setOnAction((ActionEvent e) -> {
                    delete(r.getId());
                });
                property.set(rep);
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
                MyNotifications.infoNotification("Modification", "Le message complementaire est modifier avec Succès");
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
                    MyNotifications.infoNotification("Modification", "le colier  Ne peut pas etre Vide");
                }else{   
                annonceservice.updatecolier(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "Le colier est modifier avec Succès");
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
                    MyNotifications.infoNotification("Modification", "le lieu de perte  Ne peut pas etre Vide");
                }else{   
                annonceservice.updatelieuPerte(event.getNewValue(), curseur.getValue().getId());
                MyNotifications.infoNotification("Modification", "Le lieu de perte est modifier avec Succès");
                }
            }
        });
     
    
    
    ObservableList<AnnoncePerdu> annonceperdus = FXCollections.observableArrayList(liste);
        TreeItem<AnnoncePerdu> root = new RecursiveTreeItem<>(annonceperdus, RecursiveTreeObject::getChildren);
        consultertable.setRoot(root);
       consultertable.setShowRoot(false);
    
    
    }

    private void delete(int id) {
        
                AnnoncePerduServices as = new AnnoncePerduServices();
                as.DeleteAnnoncePerdu(id);
                
    }
    
  */  
//}
