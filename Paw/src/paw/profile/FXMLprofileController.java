/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.profile;

import Service.AchatService;
import Service.AnnonceServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static paw.Paw.session;
import paw.mainUI.FXMLCnxController;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLprofileController extends FXMLCnxController implements Initializable  {

    @FXML
    private ImageView avatar;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label numero;
    @FXML
    private Label date;
    @FXML
    private Label achats;
    @FXML
    private Label annonces;
    @FXML
    private AnchorPane window;
    @FXML
    private Label nom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nom.setText(session.getNom().toUpperCase()+" "+session.getPrenom());
        adresse.setText(session.getAddresse());
        email.setText(session.getEmail());
        numero.setText(String.valueOf(session.getNumero()));
        date.setText("Inscris depuis : "+String.valueOf((session.getDateInscription())));
        AnnonceServices a = new AnnonceServices();
        annonces.setText(String.valueOf(a.getMesAnnonces(session.getId()).size()));
        AchatService s = new AchatService();
        achats.setText(String.valueOf(s.nombreAchat(session.getId())));
    }    

    @FXML
    private void goToAchat(MouseEvent event) {
        try {
            
            loadSplashScreen("/paw/boutique/user/achat/FXMLMesAchat.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAnnonce(MouseEvent event) {
        try{
            loadSplashScreen("/paw/ayoubAdmin/reclamation/FXMLreclamation.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToCours(MouseEvent event) {
        try {
           
            loadSplashScreen("/paw/reclamation/FXMLReclamation.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToStatistiques(ActionEvent event) {
        try {
            loadSplashScreen("/paw/ayoubAdmin/statistiques/FXMLstatistiques.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToListeAdoption(ActionEvent event) {
        try {                    
            loadSplashScreen("/paw/annonceadoption/FXMLliste.fxml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
