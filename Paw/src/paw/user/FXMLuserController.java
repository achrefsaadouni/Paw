/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.user;

import Entity.Personne;
import Service.PersonneServices;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLuserController implements Initializable {


    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXTextField idInsertion;

    @FXML
    private JFXTextField prenomInsertion;

    @FXML
    private JFXTextField nomInsertion;

    @FXML
    private JFXTextField idModification;

    @FXML
    private JFXTextField prenomModification;

    @FXML
    private JFXTextField nomModification;

    @FXML
    private JFXTextField idSupression;

    @FXML
    private TableView<Personne> tableView;

    @FXML
    private TableColumn<Personne, String> idCol;

    @FXML
    private TableColumn<Personne, String> nomCol;

    @FXML
    private TableColumn<Personne, String> prenomCol;

    @FXML
    void actionInsertion(ActionEvent event) {
        if ((!"".equals(idInsertion.getText()))&&(!"".equals(nomInsertion.getText()))&& (!"".equals(prenomInsertion.getText())))
        {
            PersonneServices ps = new PersonneServices();
            ps.insererPersonne(new Personne(Integer.parseInt(idInsertion.getText()),prenomInsertion.getText(),nomInsertion.getText()));
            idInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            loadTable();
        }
        else
        {
            
        }
    }

    @FXML
    void actionModification(ActionEvent event) {
        if ((!"".equals(idModification.getText()))&&(!"".equals(nomModification.getText()))&& (!"".equals(prenomModification.getText())))
        {
            PersonneServices ps = new PersonneServices();
            ps.updatePersonne(new Personne(Integer.parseInt(idModification.getText()),prenomModification.getText(),nomModification.getText()),Integer.parseInt(idModification.getText()));
            idModification.setText("");
            nomModification.setText("");
            prenomModification.setText("");
            loadTable();
        }
        else
        {
            
        }
    }

    @FXML
    void actionSupression(ActionEvent event) {
        if (!"".equals(idSupression.getText())){
            PersonneServices ps = new PersonneServices();
            ps.DeletePersonne(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();
        
    }    
    

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    }

    private void loadTable() {
        PersonneServices ps = new PersonneServices();
        tableView.getItems().setAll(ps.getAll());    
    }
}
