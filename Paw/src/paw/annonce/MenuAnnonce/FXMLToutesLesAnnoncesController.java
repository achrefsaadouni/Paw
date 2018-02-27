/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonce.MenuAnnonce;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import paw.mainUI.FXMLCnxController;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class FXMLToutesLesAnnoncesController extends FXMLCnxController{

    @FXML
    private Label nom12;
    @FXML
    private JFXButton los;
    @FXML
    private JFXButton trou;
    @FXML
    private JFXButton tr;
    @FXML
    private JFXButton acc;
    @FXML
    private AnchorPane admin_window;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToMesAnnoncesPErdus(ActionEvent event) {
        loadSplashScreenAdmin("/paw/annonceperdu/user/modificationAnnonce/FXMLModifierAnnoncePerdu.fxml");
    }

    @FXML
    private void goTomesAnnoncesTrouvee(ActionEvent event) {
        loadSplashScreenAdmin("/paw/annoncetrouvee/admin/FXMLAdmin.fxml");
    }

    @FXML
    private void goToTraining(ActionEvent event) {
        
    }

    @FXML
    private void goToAccouplement(ActionEvent event) {
    }

    @FXML
    private void goToAdoption(ActionEvent event) {
    }
    
}
