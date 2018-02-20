/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.ayoubAdmin.utilisateurs;

import Entity.Reclamation;
import Entity.Utilisateur;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import static paw.Paw.session;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLutilisateursController implements Initializable {

    private ArrayList<Utilisateur> liste;
    @FXML
    private JFXTreeTableView<Utilisateur> UtilisateursTable;
    @FXML
    private TreeTableColumn<Utilisateur, String> nom;
    @FXML
    private TreeTableColumn<Utilisateur, String> prenom;
    @FXML
    private TreeTableColumn<Utilisateur, String> username;
    @FXML
    private TreeTableColumn<Utilisateur, String> email;
    @FXML
    private JFXTextField filtre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurServices serviceUtilisateur = new UtilisateurServices();
        liste=serviceUtilisateur.getListePourAdmin(session.getId());
        
        
        initUtilisateurs();
    }    

    private void initUtilisateurs() {
        UtilisateurServices service = new UtilisateurServices();
        liste= service.getListePourAdmin(session.getId());

        nom.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Utilisateur r = (Utilisateur) param.getValue().getValue();
            property.set(r.getNom());
            return property;
        });
        prenom.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Utilisateur r = (Utilisateur) param.getValue().getValue();
            property.set(r.getPrenom());
            return property;
        });
        email.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Utilisateur r = (Utilisateur) param.getValue().getValue();
            property.set(r.getEmail());
            return property;
        });
        username.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Utilisateur r = (Utilisateur) param.getValue().getValue();
            property.set(r.getUsername());
            return property;
        });
//        
//        repondre.setCellValueFactory(param -> {
//            SimpleObjectProperty property = new SimpleObjectProperty();
//            Reclamation r = (Reclamation) param.getValue().getValue();
//            if (r.getEtat().equals("Non traitée"))
//            {
//                JFXButton rep = new JFXButton("Répondre");
//                rep.setStyle("-fx-background-color:white;");
//                rep.setOnAction((ActionEvent e) -> {
//                    reponseReclamation(r);
//                });
//                property.set(rep);
//                return property;
//            }
//            else
//            {
//                JFXButton rep = new JFXButton("Voir réponse");
//                rep.setStyle("-fx-background-color:white;");
//
//                rep.setOnAction((ActionEvent e) -> {
//                    voirReponseReclamation(r);
//                });
//
//                property.set(rep);
//                return property;
//            }
//            
//        });

        ObservableList<Utilisateur> users = FXCollections.observableArrayList(liste);
        TreeItem<Utilisateur> root = new RecursiveTreeItem<>(users, RecursiveTreeObject::getChildren);
        UtilisateursTable.setRoot(root);
        UtilisateursTable.setShowRoot(false);   
        
        filtre.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                UtilisateursTable.setPredicate(new Predicate<TreeItem<Utilisateur>>(){
                    @Override
                    public boolean test(TreeItem<Utilisateur> t) {
                        Boolean flag =  t.getValue().getNom().contains(newValue)
                                        ||t.getValue().getPrenom().contains(newValue)
                                        ||t.getValue().getEmail().contains(newValue)
                                        ||t.getValue().getUsername().contains(newValue);
                        return flag;
                    }
                    
                });
            }
        
        });
    }
    
}
