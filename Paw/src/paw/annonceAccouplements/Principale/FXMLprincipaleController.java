/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceAccouplements.Principale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import paw.mainUI.FXMLCnxController;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class FXMLprincipaleController extends  FXMLCnxController {

    @FXML
    private AnchorPane window;
    @FXML
    private AnchorPane reclamations;
    @FXML
    private AnchorPane reclamations1;
    @FXML
    private AnchorPane reclamations11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToListeaccouplement(ActionEvent event) {
        loadSplashScreen("/paw/annonceAccouplements/user/toutesLesAnnonces/FXMLToutesLesAccouplements.fxml");
    }

    @FXML
    private void goToAjoutaccouplement(ActionEvent event) {
         loadSplashScreen("/paw/annonceAccouplements/user/FXMLAnnonceAccouplements.fxml");
    }
    
}
