/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.veterinaires;

import Entity.Veterinaire;
import Service.VeterinaireServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLVeterinairesController implements Initializable {

    @FXML
    private Label nom1;
    @FXML
    private Label adr1;
    @FXML
    private Label mail1;
    @FXML
    private Label nom2;
    @FXML
    private Label adr2;
    @FXML
    private Label mail2;
    @FXML
    private Label nom3;
    @FXML
    private Label adr3;
    @FXML
    private Label mail3;
    @FXML
    private Label nom4;
    @FXML
    private Label adr4;
    @FXML
    private Label mail4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VeterinaireServices service = new VeterinaireServices();
        ArrayList<Veterinaire> list = service.getList();
        nom1.setText(list.get(0).getNom());
    }    
    
}
