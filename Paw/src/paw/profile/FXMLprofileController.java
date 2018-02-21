/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.profile;

import Entity.Utilisateur;
import Service.AchatService;
import Service.AnnonceServices;
import Service.LoginServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    @FXML
    private AnchorPane modifanchor;
    @FXML
    private ImageView avatarmodif;
    @FXML
    private Label usermodif;
    @FXML
    private JFXTextField nommodif;
    @FXML
    private JFXTextField prenommodif;
    @FXML
    private JFXTextField adressemodif;
    @FXML
    private JFXTextField numeromodif;
    @FXML
    private JFXRadioButton hmodif;
    @FXML
    private JFXRadioButton fmodif;
    @FXML
    private JFXTextField emailmodif;
    @FXML
    private StackPane stack;
    @FXML
    private AnchorPane details;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        //System.out.println(session.getAvatar());
        ToggleGroup togGroup = new ToggleGroup();
        fmodif.setToggleGroup(togGroup);
        hmodif.setToggleGroup(togGroup);
        modifanchor.setVisible(false);
        try{
            avatar.setImage(new Image("http://localhost/pawUsers/"+session.getAvatar()));
            avatar.setFitWidth(175);
            avatar.setFitHeight(169);
            
            avatarmodif.setImage(new Image("http://localhost/pawUsers/"+session.getAvatar()));
            
        }
        catch(Exception e)
        {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, e);
        }
        loadDetails();
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
            loadSplashScreen("/paw/annonceperdu/user/mesAnnonces/FXMLPerduFinal.fxml");
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
    
    
    
    
    
    
//    try{
//            loadSplashScreen("/paw/ayoubAdmin/reclamation/FXMLreclamation.fxml");
//        } catch (Exception ex) {
//            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    @FXML
    private void goToListeUtilisateurs(ActionEvent event) {
        try {
            loadSplashScreen("/paw/ayoubAdmin/utilisateurs/FXMLutilisateurs.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToMesOffresAdoption(ActionEvent event) {
        try {                    
            loadSplashScreen("/paw/annonceadoption/FXMLmesoffresadoption.fxml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        nommodif.setText(session.getNom());
        prenommodif.setText(session.getPrenom());
        adressemodif.setText(session.getAddresse());
        emailmodif.setText(session.getEmail());
        numeromodif.setText(String.valueOf(session.getNumero()));
        if (session.getSexe().equals("Homme"))
        {
            hmodif.setSelected(true);
            fmodif.setSelected(false);
        }
        else{
            hmodif.setSelected(false);
            fmodif.setSelected(true);
        }
        modifanchor.setVisible(true);
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        
        UtilisateurServices s = new UtilisateurServices();
        String x="Femme";
        if(hmodif.isSelected())
        {
            x = "Homme";
        }
        if(s.modifierInfos( new Utilisateur(session.getId(), nommodif.getText(), prenommodif.getText(), adressemodif.getText(), emailmodif.getText(), null, null, null, Integer.parseInt(numeromodif.getText()), null, null, x)))
        {
            LoginServices service = new LoginServices();
            session= service.getInformation(session.getId());
            modifanchor.setVisible(false);
            loadDetails();
            JFXSnackbar snack = new JFXSnackbar(details);
            snack.show("Informations mises à jours", 3000);
        }
        else{
            
        }              
    }

    private void loadDetails() {
        nom.setText(session.getEsm());
        adresse.setText(session.getAddresse());
        email.setText(session.getEmail());
        numero.setText(String.valueOf(session.getNumero()));
        date.setText("Inscris depuis : "+String.valueOf((session.getDateInscription())));
        AnnonceServices a = new AnnonceServices();
        annonces.setText(String.valueOf(a.getMesAnnonces(session.getId()).size()));
        AchatService s = new AchatService();
        achats.setText(String.valueOf(s.nombreAchat(session.getId())));
    }
    
}
