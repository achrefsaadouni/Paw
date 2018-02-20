/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.ayoubAdmin.utilisateurs;

import Entity.Utilisateur;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
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
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    @FXML
    private Label nomu;
    @FXML
    private Label prenomu;
    @FXML
    private Label sexeu;
    @FXML
    private Label usernameu;
    @FXML
    private Label emailu;
    @FXML
    private Label adresseu;
    @FXML
    private Label numerou;
    @FXML
    private Label dateinscriptionu;
    @FXML
    private Label roleu;
    @FXML
    private TreeTableColumn<Utilisateur, JFXButton> consulter;
    @FXML
    private GridPane tableau;
    @FXML
    private GridPane boutons;
    @FXML
    private StackPane selectionnez;
    @FXML
    private JFXButton btnbanir;
    @FXML
    private JFXButton btn;
    @FXML
    private Pane infos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
          selectionnez.setVisible(true);
          tableau.setVisible(false);
          boutons.setVisible(false);
        
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
        
        
        consulter.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Utilisateur r = (Utilisateur) param.getValue().getValue();
                JFXButton rep = new JFXButton("Détails");
                rep.setOnAction((ActionEvent e) -> {
                    voirUtilisateur(r);
                });
                property.set(rep);
                return property;
        });

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

    private void voirUtilisateur(Utilisateur r) {
        if(r.getRole().equals("Membre"))
        {
            btn.setText("Rendre Administrateur");
            btn.setOnAction((ActionEvent) ->{
            UtilisateurServices s = new UtilisateurServices();
            if(s.rendreAdmin(r.getId()))
            {
                liste=s.getListePourAdmin(session.getId());
                initUtilisateurs();
                Utilisateur n = r ;
                n.setRole("Admin");
                voirUtilisateur(n);
                JFXSnackbar snack = new JFXSnackbar(infos);
                snack.show(r.getEsm()+" est désormais Admin", 2000);
            }
            else
            {
                JFXSnackbar snack = new JFXSnackbar(infos);
                snack.show("Erreur", 2000);
            }
            });
        }
        else
        {
            btn.setText("Rendre simple Membre");
            btn.setOnAction((ActionEvent) ->{
            UtilisateurServices s = new UtilisateurServices();
            if(s.rendreMembre(r.getId()))
            {
                liste=s.getListePourAdmin(session.getId());
                initUtilisateurs();
                Utilisateur n = r ;
                n.setRole("Membre");
                voirUtilisateur(n);
                JFXSnackbar snack = new JFXSnackbar(infos);
                snack.show(r.getEsm()+" est désormais un simple Membre", 2000);
            }
            else
            {
                JFXSnackbar snack = new JFXSnackbar(infos);
                snack.show("Erreur", 2000);
            }
            });
        }
    ////////////////////////////////////////////////////////////////////////////////    
//        if(r.getEtat().equals("Free"))
//        {
//            btnbanir.setText("Bloquer");
//            btnbanir.setOnAction((ActionEvent) ->{
//            UtilisateurServices s = new UtilisateurServices();
//            if(s.bloquer(r.getId()))
//            {
//                liste=s.getListePourAdmin(session.getId());
//                initUtilisateurs();
//                Utilisateur n = r ;
//                n.setEtat("Bloqué");
//                voirUtilisateur(n);
//                JFXSnackbar snack = new JFXSnackbar(infos);
//                snack.show("Vous avez bloqué "+r.getEsm(), 2000);
//            }
//            else
//            {
//                JFXSnackbar snack = new JFXSnackbar(infos);
//                snack.show("Erreur", 2000);
//            }
//            });
//        }
//        else
//        {
//            btnbanir.setText("Débloquer");
//            btnbanir.setOnAction((ActionEvent) ->{
//            UtilisateurServices s = new UtilisateurServices();
//            if(s.debloquer(r.getId()))
//            {
//                liste=s.getListePourAdmin(session.getId());
//                initUtilisateurs();
//                Utilisateur n = r ;
//                n.setEtat("Free");
//                voirUtilisateur(n);
//                JFXSnackbar snack = new JFXSnackbar(infos);
//                snack.show("Vous avez débloqué "+r.getEsm(), 2000);
//            }
//            else
//            {
//                JFXSnackbar snack = new JFXSnackbar(infos);
//                snack.show("Erreur", 2000);
//            }
//            });
//        }
         ////////////////////////////////////////////////////////////
        selectionnez.setVisible(false);
        tableau.setVisible(true);
        boutons.setVisible(true);
        nomu.setText(r.getNom());
        prenomu.setText(r.getPrenom());
        adresseu.setText(r.getAddresse());
        emailu.setText(r.getEmail());
        numerou.setText(String.valueOf(r.getNumero()));
        usernameu.setText(r.getUsername());
        dateinscriptionu.setText(String.valueOf(r.getDateInscription()));
        sexeu.setText(r.getSexe());
        roleu.setText(r.getRole());
    }

    
}
