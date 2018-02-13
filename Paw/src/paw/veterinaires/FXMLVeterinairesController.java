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
    @FXML
    private Label tel1;
    @FXML
    private Label tel2;
    @FXML
    private Label tel3;
    @FXML
    private Label tel4;
    @FXML
    private Label reg1;

    @FXML
    private Label reg2;

    @FXML
    private Label reg3;

    @FXML
    private Label reg4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VeterinaireServices service = new VeterinaireServices();
        ArrayList<Veterinaire> list = service.getList();
        
        nom1.setText(list.get(0).getNom()+" "+list.get(0).getPrenom());
        mail1.setText(list.get(0).getEmail());
        tel1.setText(String.valueOf(list.get(0).getNumero()));
        adr1.setText(list.get(0).getAdresse());
        reg1.setText(list.get(0).getRegion());
        
        nom2.setText(list.get(1).getNom()+" "+list.get(1).getPrenom());
        mail2.setText(list.get(1).getEmail());
        tel2.setText(String.valueOf(list.get(1).getNumero()));
        adr2.setText(list.get(1).getAdresse());
        reg2.setText(list.get(1).getRegion());
        
        nom3.setText(list.get(2).getNom()+" "+list.get(2).getPrenom());
        mail3.setText(list.get(2).getEmail());
        tel3.setText(String.valueOf(list.get(2).getNumero()));
        adr3.setText(list.get(2).getAdresse());
        reg3.setText(list.get(2).getRegion());
        
        nom4.setText(list.get(3).getNom()+" "+list.get(3).getPrenom());
        mail4.setText(list.get(3).getEmail());
        tel4.setText(String.valueOf(list.get(3).getNumero()));
        adr4.setText(list.get(3).getAdresse());
        reg4.setText(list.get(3).getRegion());
    }    
    
}
