/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.veterinaire;

import Entity.Veterinaire;
import Service.VeterinaireServices;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLVeterinaireController implements Initializable {

    @FXML
    private JFXTextField nomInsertion;
    @FXML
    private JFXTextField prenomInsertion;
    @FXML
    private JFXTextField adresseInsertion;
    @FXML
    private JFXTextField regionInsertion;
    @FXML
    private JFXTextField numeroInsertion;
    @FXML
    private JFXTextField emailInsertion;
    @FXML
    private JFXTextField nomInsertion1;
    @FXML
    private JFXTextField prenomInsertion1;
    @FXML
    private JFXTextField adresseInsertion1;
    @FXML
    private JFXTextField regionInsertion1;
    @FXML
    private JFXTextField numeroInsertion1;
    @FXML
    private JFXTextField emailInsertion1;
    @FXML
    private JFXTextField idInsertion1;
    @FXML
    private JFXTextField idSupression;
    @FXML
    private TableView<Veterinaire> tableView;
    @FXML
    private TableColumn<Veterinaire, String> idCol;
    @FXML
    private TableColumn<Veterinaire, String> nomCol;
    @FXML
    private TableColumn<Veterinaire, String> prenomCol;
    @FXML
    private TableColumn<Veterinaire, String> adresseCol;
    @FXML
    private TableColumn<Veterinaire, String> regionCol;
    @FXML
    private TableColumn<Veterinaire, String> numeroCol;
    @FXML
    private TableColumn<Veterinaire, String> emailCol;

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void actionInsertion(ActionEvent event) {
        if ((!"".equals(emailInsertion.getText()))&&(!"".equals(nomInsertion.getText()))&& (!"".equals(prenomInsertion.getText()))&&(!"".equals(regionInsertion.getText()))&&(!"".equals(adresseInsertion.getText()))&&(!"".equals(numeroInsertion.getText())))
        {
            VeterinaireServices service = new VeterinaireServices();
            service.insererVeterinaire(new Veterinaire(0,nomInsertion.getText(),prenomInsertion.getText(),adresseInsertion.getText(),regionInsertion.getText(),Integer.parseInt(numeroInsertion.getText()),emailInsertion.getText()));
            emailInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            regionInsertion.setText("");
            adresseInsertion.setText("");
            numeroInsertion.setText("");
            loadTable();
        }
    }

    @FXML
    private void actionModification(ActionEvent event) {
        if ((!"".equals(idInsertion1.getText()))&&(!"".equals(emailInsertion1.getText()))&&(!"".equals(nomInsertion1.getText()))&& (!"".equals(prenomInsertion1.getText()))&&(!"".equals(regionInsertion1.getText()))&&(!"".equals(adresseInsertion1.getText()))&&(!"".equals(numeroInsertion1.getText())))
        {
            VeterinaireServices service = new VeterinaireServices();
            //service.updateVeterinaire(new Veterinaire(0,nomInsertion1.getText(),prenomInsertion1.getText(),adresseInsertion1.getText(),regionInsertion1.getText(),Integer.parseInt(numeroInsertion1.getText()),emailInsertion1.getText()),Integer.parseInt(idInsertion1.getText()));
            emailInsertion1.setText("");
            nomInsertion1.setText("");
            prenomInsertion1.setText("");
            regionInsertion1.setText("");
            adresseInsertion1.setText("");
            numeroInsertion1.setText("");
            loadTable();
        }
    }

    @FXML
    private void actionSupression(ActionEvent event) {
        if (!"".equals(idSupression.getText())){
            VeterinaireServices service = new VeterinaireServices();
            service.DeleteVeterinaire(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        loadTable();
        
    }


 private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        numeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadTable() {
        VeterinaireServices service = new VeterinaireServices();
        tableView.getItems().setAll(service.getAll());    
    }   
    
}
