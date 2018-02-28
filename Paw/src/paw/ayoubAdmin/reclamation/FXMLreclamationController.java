/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.ayoubAdmin.reclamation;

import Entity.Reclamation;
import Entity.RepRec;
import Entity.Utilisateur;
import Service.ReclamationServices;
import Service.RepRecServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import com.jfoenix.validation.RequiredFieldValidator;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import paw.mainUI.FXMLCnxController;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLreclamationController extends FXMLCnxController implements Initializable {
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
    @FXML
    private Label titrer;
    @FXML
    private Label textr;
    @FXML
    private Label dater;
    @FXML
    private Label utilisateurr;
    @FXML
    private JFXTextArea reponser;
    @FXML
    private AnchorPane rep;
    @FXML
    private Separator separator;
    @FXML
    private Label reptext;
    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private Label lab;
    @FXML
    private Label datereponse;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXTextField filtre;
    @FXML
    private AnchorPane window;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationServices serviceRec = new ReclamationServices();
        
        RequiredFieldValidator rf = new RequiredFieldValidator();
        rf.setMessage("Vous devez répondre à la réclamation.");
        reponser.getValidators().add(rf);
        reponser.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    reponser.validate();
                }
            }
        });
        
        
        rep.setVisible(false);
        
        initReclamation();
    }    

    private void initReclamation() {
        ReclamationServices serviceRec = new ReclamationServices();
        liste= serviceRec.getAll();
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
        
        repondre.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Reclamation r = (Reclamation) param.getValue().getValue();
            if (r.getEtat().equals("Non traitée"))
            {
                JFXButton rep = new JFXButton("Répondre");
                rep.setStyle("-fx-background-color:white;");
                rep.setOnAction((ActionEvent e) -> {
                    reponseReclamation(r);
                });
                property.set(rep);
                return property;
            }
            else
            {
                JFXButton rep = new JFXButton("Voir réponse");
                rep.setStyle("-fx-background-color:white;");

                rep.setOnAction((ActionEvent e) -> {
                    voirReponseReclamation(r);
                });

                property.set(rep);
                return property;
            }
            
        });

        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(liste);
        TreeItem<Reclamation> root = new RecursiveTreeItem<>(reclamations, RecursiveTreeObject::getChildren);
        ReclamationTable.setRoot(root);
        ReclamationTable.setShowRoot(false);
        
        filtre.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ReclamationTable.setPredicate(new Predicate<TreeItem<Reclamation>>() {
                    @Override
                    public boolean test(TreeItem<Reclamation> t) {
                        Boolean flag =  t.getValue().getText().contains(newValue)
                                ||t.getValue().getType().contains(newValue)
                                ||t.getValue().getDate().toString().contains(newValue)
                                ||t.getValue().getObjet().contains(newValue);
                        return flag;
                    }
                });
            }
        
        });
    }

    private void reponseReclamation(Reclamation r) {
            Utilisateur u = null;
            UtilisateurServices serviceUtil = new UtilisateurServices();
            u = serviceUtil.rechercher(r.getUtilisateur());
            
            dater.setText(String.valueOf(r.getDate()).substring(0,16));
            titrer.setText(r.getType()+" : "+r.getObjet());
            textr.setText(r.getText());
            utilisateurr.setText(u.getEsm());
            reponser.setText("");
            lab.setText(String.valueOf(r.getId()));
            reponser.setVisible(true);
            btnAnnuler.setLayoutX(150);
            reptext.setVisible(false);
            datereponse.setVisible(false);
            btnEnregistrer.setVisible(true);
            separator.setVisible(false);
            rep.setVisible(true);
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        if (reponser.validate())
        {
            RepRecServices s=new RepRecServices();
            s.insererRepReclamation(new RepRec (Integer.parseInt(lab.getText()),reponser.getText()));
            ReclamationServices r= new ReclamationServices();
            r.traiterReclamation(Integer.parseInt(lab.getText()));
            initReclamation();
            rep.setVisible(false);
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        rep.setVisible(false);
    }

    private void voirReponseReclamation(Reclamation r) {
            Utilisateur u = null;
            UtilisateurServices serviceUtil = new UtilisateurServices();
            RepRecServices reponseService = new RepRecServices();
            u = serviceUtil.rechercher(r.getUtilisateur());
            
            dater.setText(String.valueOf(r.getDate()).substring(0,16));
            titrer.setText(r.getType()+" : "+r.getObjet());
            textr.setText(r.getText());
            RepRec x=reponseService.getReponse(r.getId());
            datereponse.setText("Réponse ecrite le : "+String.valueOf(x.getDate()).substring(0, 16));
            utilisateurr.setText(u.getEsm());
            reponser.setVisible(false);
            btnAnnuler.setLayoutX(325);
            btnEnregistrer.setVisible(false);
            datereponse.setVisible(true);
            separator.setVisible(true);
            reptext.setVisible(true);
            reptext.setText(x.getText());
            rep.setVisible(true);
    }

    private void gotoAjout(ActionEvent event) {
        try {
            loadSplashScreen("/paw/annonceadoption/FXMLajouter.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}
