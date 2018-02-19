/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.Utilisateur;
import Service.InscriptionService;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLinscriptionController implements Initializable {

    @FXML
    private Label btn_exit;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numero;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField rue;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    @FXML
    private JFXRadioButton homme;
    @FXML
    private JFXRadioButton femme;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmer;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup groupsexe = new ToggleGroup();
        homme.setToggleGroup(groupsexe);
        femme.setToggleGroup(groupsexe);
        JFXSnackbar snack = new JFXSnackbar(anchor);
        
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void fileChoosing(ActionEvent event) {
    }

    @FXML
    private void inscriptionButton(ActionEvent event) {
        
        if (!nom.getText().equals("") &&
            !prenom.getText().equals("") &&
            !password.getText().equals("") && 
            !confirmer.getText().equals("") &&
            !rue.getText().equals("") &&
            !ville.getText().equals("") &&    
            !numero.getText().equals("") &&    
            !email.getText().equals("") &&
            !username.getText().equals("")
            )
        {
            InscriptionService service = new InscriptionService();
            if (service.EmailNonExistant(email.getText()))
            {
                if (service.UsernameNonExistant(username.getText()))
                {
                    if(password.getText().equals(confirmer.getText()))
                     {
                         UtilisateurServices s = new UtilisateurServices();
                         String sexe="Homme";
                         if (femme.isSelected())
                         {
                             sexe="Femme";
                         }
                         Utilisateur p = new Utilisateur(0, nom.getText(), prenom.getText(), ville.getText().toUpperCase()+", "+rue.getText(), email.getText(), username.getText(), password.getText(), "Membre", Integer.parseInt(numero.getText()), "", null, sexe);
                         s.insererUtilisateur(p);
                         Notifications.create().text("Inscription done").title("Succès").show();
                         
                     }
                     else
                     {
                         System.out.println("Password different de la confirmation");
                     }
                }
                else
                {
                     System.out.println("Username deja existant");
                }
            }
            else
            {
                System.out.println("Email deja existant");
            }
        }
        else
        {
            JFXSnackbar snack = new JFXSnackbar(anchor);
            snack.show("Veuillez remplir tout les champs", 1000);
        }
       
        
        
    }
    
}
