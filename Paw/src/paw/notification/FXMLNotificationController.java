/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.notification;

import Entity.Notification;
import Service.NotificationServices;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLNotificationController implements Initializable {

    @FXML
    private JFXTextField destinataireInsert;
    @FXML
    private JFXTextField emetteurInsert;
    @FXML
    private JFXTextField titreInsert;
    @FXML
    private JFXTextField texteInsert;
    @FXML
    private ChoiceBox<String> choixInsert;
    @FXML
    private JFXTextField destinataireModif;
    @FXML
    private JFXTextField emetteurModif;
    @FXML
    private JFXTextField titreModif;
    @FXML
    private JFXTextField texteModif;
    @FXML
    private ChoiceBox<String> choixModif;
    @FXML
    private JFXTextField idModif;
    @FXML
    private JFXTextField idSupression;
    @FXML
    private TableView<Notification> tableView;
    @FXML
    private TableColumn<Notification, String> idCol;
    @FXML
    private TableColumn<Notification, String> destinataireCol;
    @FXML
    private TableColumn<Notification, String> emetteurCol;
    @FXML
    private TableColumn<Notification, String>titreCol;
    @FXML
    private TableColumn<Notification, String> texteCol;
    @FXML
    private TableColumn<Notification, String> dateCol;
    @FXML
    private TitledPane INSERTION;
    @FXML
    private TableColumn<Notification, String> typeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choixInsert.getItems().setAll("DEMANDE ADOPTION","DEMANDE ACCOUPLEMENT");
        choixInsert.setValue("DEMANDE ADOPTION");
        choixModif.getItems().setAll("DEMANDE ADOPTION","DEMANDE ACCOUPLEMENT");
        choixModif.setValue("DEMANDE ADOPTION");
        initCol();
        loadTable();
    }    

    @FXML
    private void actionInsertion(ActionEvent event) {
        NotificationServices service = new NotificationServices();
        service.insererNotification(new Notification(0,Integer.parseInt(destinataireInsert.getText()), Integer.parseInt(emetteurInsert.getText()),titreInsert.getText(), texteInsert.getText(), choixInsert.getValue()));
        destinataireInsert.setText("");
        emetteurInsert.setText("");
        texteInsert.setText("");
        titreInsert.setText("");
        choixInsert.setValue("");
        loadTable();
    }

    @FXML
    private void actionModification(ActionEvent event) {
        NotificationServices service = new NotificationServices();
        
        service.updateNotification(new Notification(0, Integer.parseInt(destinataireInsert.getText()), Integer.parseInt(emetteurInsert.getText()), titreInsert.getText(), texteInsert.getText(), choixInsert.getValue()),Integer.parseInt(idModif.getText()));
        destinataireModif.setText("");
        emetteurModif.setText("");
        texteModif.setText("");
        titreModif.setText("");
        choixModif.setValue("");
        loadTable();
    }

    @FXML
    private void actionSupression(ActionEvent event) {
        NotificationServices service = new NotificationServices();
        service.DeleteNotification(Integer.parseInt(idSupression.getText()));
        idSupression.setText("");
        loadTable();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        destinataireCol.setCellValueFactory(new PropertyValueFactory<>("id_destinataire"));
        emetteurCol.setCellValueFactory(new PropertyValueFactory<>("id_emetteur"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        texteCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    private void loadTable() {
        NotificationServices service = new NotificationServices();
        tableView.getItems().setAll(service.getAll());  
    }
    
}
