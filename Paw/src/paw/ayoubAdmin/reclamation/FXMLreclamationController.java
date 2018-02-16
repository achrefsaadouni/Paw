/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.ayoubAdmin.reclamation;

import Entity.Produit;
import Entity.Reclamation;
import Entity.Utilisateur;
import Service.ReclamationServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
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
import com.jfoenix.controls.JFXTreeTableView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import paw.MyNotifications;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLreclamationController implements Initializable {
    List<Reclamation> liste;
        @FXML
    private JFXTreeTableView<Reclamation> ReclamationTable;
    @FXML
    private TreeTableColumn<Reclamation, String> objet;
    @FXML
    private TreeTableColumn<Reclamation, String> text;
    @FXML
    private TreeTableColumn<Reclamation, String> type;
    @FXML
    private TreeTableColumn<Reclamation, String> date;
    @FXML
    private TreeTableColumn<Reclamation,JFXButton> repondre;
    
//    @FXML
//    private JFXTreeTableView<Reclamation> ReclamationTable;
//    @FXML
//    private TreeTableColumn<Reclamation, JFXButton> repondre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationServices serviceRec = new ReclamationServices();
        liste= serviceRec.getAll();
        
        initReclamation();
    }    

    private void initReclamation() {
        ReclamationServices serviceRec = new ReclamationServices();
        UtilisateurServices serviceUtil = new UtilisateurServices();

        text.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reclamation r = (Reclamation) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(r.getText());
            return property;
        });
        objet.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reclamation r = (Reclamation) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(r.getObjet());
            return property;
        });
        type.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reclamation r = (Reclamation) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(r.getType());
            return property;
        });
        date.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Reclamation r = (Reclamation) param.getValue().getValue();
            Utilisateur u = null;
            u = serviceUtil.rechercher(r.getUtilisateur());
            property.set(String.valueOf(r.getDate()).substring(0, 16));
            return property;
        });
        
//        repondre.setCellValueFactory(param -> {
//            SimpleObjectProperty property = new SimpleObjectProperty();
//            Produit produit = (Produit) param.getValue().getValue();
//           Reclamation r = (Reclamation) param.getValue().getValue();
////          Utilisateur u = null;
////          u = serviceUtil.rechercher(r.getUtilisateur());
//            JFXButton supp = new JFXButton("Répondre");
//            supp.setStyle("-fx-background-color:white;");
//            supp.setOnAction((ActionEvent e) -> {
//                produitservice.deleteProduit(r.getUtilisateur());
//                refresh();
//            });
//
//            property.set(supp);
//            return property;
//        });
//        repondre.setCellValueFactory(param -> {
//            Simple property = new SimpleStringProperty();
//            Reclamation r = (Reclamation) param.getValue().getValue();
//            Utilisateur u = null;
//            u = serviceUtil.rechercher(r.getUtilisateur());
//            property.set();
//            return property;
//        });

        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(liste);
        TreeItem<Reclamation> root = new RecursiveTreeItem<>(reclamations, RecursiveTreeObject::getChildren);
        ReclamationTable.setRoot(root);
        ReclamationTable.setShowRoot(false);
    }

    
    
    
}
