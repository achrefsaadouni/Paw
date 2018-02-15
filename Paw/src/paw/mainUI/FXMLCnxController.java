/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.Utilisateur;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    public AnchorPane window;
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

        
            username.setText(session.getUsername());
            email.setText(session.getEmail());
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
         try {   
            AnchorPane menu = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLMenu.fxml"));       
            window.setOnMouseClicked(e -> {
                if (drawer.isHidden() || drawer.isHiding()) {} 
                else {
                    transition.setRate(transition.getRate()*-1);
                    transition.play();
                    drawer.toggle(); 
                }
            });
            for(Node node : menu.getChildren()){
                if (node.getAccessibleText()!=null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
                        switch(node.getAccessibleText()){
                            case "veterinaire" : {
                                try {
                                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/veterinaires/FXMLVeterinaires.fxml"));
                                    window.getChildren().setAll(pane);
                                    break;
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                }
            }
            drawer.setSidePane(menu);
            drawer.setMouseTransparent(true);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                transition.setRate(transition.getRate()*-1);
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

    @FXML
    private void goToProfile(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/profile/FXMLprofile.fxml"));
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

    @FXML
    private void closeDrawer(JFXDrawerEvent event) {
        drawer.setMouseTransparent(true);
    }

    @FXML
    private void openDrawer(JFXDrawerEvent event) {
    drawer.setMouseTransparent(false);
    }

    
    
}
