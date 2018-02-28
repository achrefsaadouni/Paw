/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.veterinaires;

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
public class FXMLveterinairePrincipaleController extends FXMLCnxController{

    @FXML
    private Label nom12;
    @FXML
    private JFXButton vets;
    @FXML
    private JFXButton conseil;
    @FXML
    private JFXButton CONS;
    @FXML
    private JFXButton conseil1;
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
    private void GOtoVet(ActionEvent event) {
           loadSplashScreenAdmin("/paw/veterinaires/admin/modifsupp/FXMLModifierSupprimerVeterinaire.fxml");
    }

    @FXML
    private void gotoConseil(ActionEvent event) {
           loadSplashScreenAdmin("/paw/conseil/admin/FXMLModifierSupprimerConseil.fxml");
    }

    @FXML
    private void ajouterVET(ActionEvent event) {
        loadSplashScreenAdmin("/paw/veterinaires/admin/FXMLAjoutVeterinaire.fxml");
    }

    @FXML
    private void ajouterConseil(ActionEvent event) {
        loadSplashScreenAdmin("/paw/conseil/admin/FXMLAjoutConseil.fxml");
    }
    
}
