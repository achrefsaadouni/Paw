/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.Utilisateur;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import static paw.Paw.session;

/**
 *
 * @author AYOUB
 */
public class FXMLCnxController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private JFXDrawer drawer;     
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private ImageView settings;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView notification;
    @FXML
    private ImageView chat;
    @FXML
    private JFXHamburger hamburger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            username.setText(session.getUsername());
            email.setText(session.getEmail());
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            AnchorPane menu = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLMenu.fxml"));         
            drawer.setSidePane(menu);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                transition.setRate(transition.getRate()* -1);
                transition.play();
                drawer.toggle();
            });
        } catch (IOException ex) {
            Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void initialisation(Utilisateur x) {
        session=x;  
    }

//    private void goToGris(ActionEvent event) throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLGris.fxml"));
//        window.getChildren().setAll(pane);
//    }
//
//    private void goToRed(ActionEvent event) throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLRed.fxml"));
//        window.getChildren().setAll(pane);
//    }

    @FXML
    private void goToProfile(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLRed.fxml"));
        window.getChildren().setAll(pane);
    }

    @FXML
    private void goToNotification(MouseEvent event) {
    }

    @FXML
    private void goToSettings(MouseEvent event) {
    }

    @FXML
    private void goToChat(MouseEvent event) {
    }

    
}
