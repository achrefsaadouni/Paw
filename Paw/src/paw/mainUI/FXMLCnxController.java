/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static paw.Paw.session;

/**
 *
 * @author AYOUB
 */
public class FXMLCnxController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private ImageView avatar1;
    @FXML
    private AnchorPane window;
     
    @FXML
    private Label username;
    @FXML
    private Label email;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(session.getUsername());
        email.setText(session.getEmail());
    }    


    @FXML
    private void goToGris(ActionEvent event) throws IOException {
        //window.getChildren().setAll(FXMLLoader.load(getClass().getResource("FXMLutilisateur.fxml")));
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLGris.fxml"));
        window.getChildren().setAll(pane);
    }

    @FXML
    private void goToRed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLRed.fxml"));
        window.getChildren().setAll(pane);
    }

    @FXML
    private void goToProfile(MouseEvent event) {
    }

    @FXML
    private void goToNotification(MouseEvent event) {
    }

    public void initialisation(Utilisateur x) {
        session=x;  
    }

    
}
