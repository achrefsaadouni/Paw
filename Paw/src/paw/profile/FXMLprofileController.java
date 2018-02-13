/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.profile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLprofileController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label numero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
