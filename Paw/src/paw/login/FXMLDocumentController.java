/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.login;

import Service.LoginServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author AYOUB
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField usern;
    @FXML
    private JFXPasswordField passw;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connexionButton(ActionEvent event) {
        LoginServices service = new LoginServices();
        
        System.out.println(service.Valide(usern.getText(), passw.getText()));
    }

    @FXML
    private void inscriptionButton(ActionEvent event) {
    }

    
}
