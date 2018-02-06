/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.utilisateur;

import Entity.Utilisateur;
import Entity.Utilisateur;
import Service.UtilisateurServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLutilisateurController implements Initializable {

    @FXML
    private JFXTextField prenomInsertion;
    @FXML
    private JFXTextField nomInsertion;
    @FXML
    private JFXTextField idSupression;
    @FXML
    private TableView<Utilisateur> tableView;
    @FXML
    private TableColumn<Utilisateur,String> idCol;
    @FXML
    private TableColumn<Utilisateur,String> nomCol;
    @FXML
    private TableColumn<Utilisateur,String>prenomCol;
    @FXML
    private JFXTextField emailInsertion;
    @FXML
    private JFXTextField usernameInsertion;
    @FXML
    private JFXPasswordField passwordInsertion;
    @FXML
    private JFXTextField addresseInsertion;
    @FXML
    private JFXTextField numeroInsertion;
    @FXML
    private ChoiceBox<String> roleInsertion;
    @FXML
    private JFXTextField nomInsertion1;
    @FXML
    private JFXTextField prenomInsertion1;
    @FXML
    private JFXTextField emailInsertion1;
    @FXML
    private JFXTextField usernameInsertion1;
    @FXML
    private JFXPasswordField passwordInsertion1;
    @FXML
    private JFXTextField addresseInsertion1;
    @FXML
    private JFXTextField numeroInsertion1;
    @FXML
    private ChoiceBox<String> roleInsertion1;
    @FXML
    private JFXTextField idInsertion1;
    @FXML
    private TableColumn<Utilisateur,String>emailCol;
    @FXML
    private TableColumn<Utilisateur,String>usernameCol;
    @FXML
    private TableColumn<Utilisateur,String>passwordCol;
    @FXML
    private TableColumn<Utilisateur,String>addresseCol;
    @FXML
    private TableColumn<Utilisateur,String>numeroCol;
    @FXML
    private TableColumn<Utilisateur,String>roleCol;

    @FXML
    void actionInsertion(ActionEvent event) {
        if ((!"".equals(emailInsertion.getText()))&&(!"".equals(nomInsertion.getText()))&& (!"".equals(prenomInsertion.getText()))&&(!"".equals(usernameInsertion.getText()))&&(!"".equals(passwordInsertion.getText()))&&(!"".equals(addresseInsertion.getText()))&&(!"".equals(numeroInsertion.getText()))&&(!"".equals(roleInsertion.getValue())))
        {
            UtilisateurServices service = new UtilisateurServices();
            service.insererUtilisateur(new Utilisateur(0,nomInsertion.getText(),prenomInsertion.getText(),emailInsertion.getText(),usernameInsertion.getText(),passwordInsertion.getText(),addresseInsertion.getText(),Integer.parseInt(numeroInsertion.getText()),roleInsertion.getValue()));
            emailInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            usernameInsertion.setText("");
            passwordInsertion.setText("");
            addresseInsertion.setText("");
            numeroInsertion.setText("");
            roleInsertion.setValue("MEMBRE");
            loadTable();
        }
    }

    @FXML
    void actionModification(ActionEvent event) {
        if ((!"".equals(idInsertion1.getText()))&&(!"".equals(emailInsertion1.getText()))&&(!"".equals(nomInsertion1.getText()))&& (!"".equals(prenomInsertion1.getText()))&&(!"".equals(usernameInsertion1.getText()))&&(!"".equals(passwordInsertion1.getText()))&&(!"".equals(addresseInsertion1.getText()))&&(!"".equals(numeroInsertion1.getText())))
        {
            UtilisateurServices service = new UtilisateurServices();
            service.updateUtilisateur(new Utilisateur(0,nomInsertion1.getText(),prenomInsertion1.getText(),emailInsertion1.getText(),usernameInsertion1.getText(),passwordInsertion1.getText(),addresseInsertion1.getText(),Integer.parseInt(numeroInsertion1.getText()),roleInsertion1.getValue()),Integer.parseInt(idInsertion1.getText()));
            emailInsertion1.setText("");
            nomInsertion1.setText("");
            prenomInsertion1.setText("");
            usernameInsertion1.setText("");
            passwordInsertion1.setText("");
            addresseInsertion1.setText("");
            numeroInsertion1.setText("");
            roleInsertion1.setValue("MEMBREdada");
            loadTable();
        }
    }

    @FXML
    void actionSupression(ActionEvent event) {
        if (!"".equals(idSupression.getText())){
            UtilisateurServices service = new UtilisateurServices();
            service.DeleteUtilisateur(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleInsertion.getItems().setAll("MEMBRE","ADMIN");
        roleInsertion.setValue("MEMBRE");
        roleInsertion1.getItems().setAll("MEMBRE","ADMIN");
        roleInsertion1.setValue("MEMBRE");
        initCol();
        loadTable();
        
    }    
    

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        addresseCol.setCellValueFactory(new PropertyValueFactory<>("addresse"));
        numeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadTable() {
        UtilisateurServices service = new UtilisateurServices();
        tableView.getItems().setAll(service.getAll());    
    }
}
