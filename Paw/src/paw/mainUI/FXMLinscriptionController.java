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
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
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
    
    private File file;

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
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
       
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            Image im = new Image("file:///" + file.toPath().toString());
            imajout1.setImage(im);
          
        }
    }

    @FXML
    private void inscriptionButton(ActionEvent event) {
        if (!file.exists()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image ou bien des  donnes concernant l'annonce ", ButtonType.CLOSE);
            alert.show();
            upload.requestFocus();
        }
        else
        {
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
                             Utilisateur p = new Utilisateur(0, nom.getText(), prenom.getText(), ville.getText().toUpperCase()+", "+rue.getText(), email.getText(), username.getText(), password.getText(), "Membre", Integer.parseInt(numero.getText()), file.getPath(), null, sexe);
                             s.insererUtilisateur(p);
                             Notifications.create().text("Inscription done").title("Succès").show();
                                nom.setText("");
                                prenom.setText("");
                                password.setText("");
                                confirmer.setText("");
                                rue.setText("");
                                ville.setText("") ;    
                                numero.setText(""); 
                                email.setText("");
                                username.setText("");  
                         }
                         else
                         {
                             JFXSnackbar snack = new JFXSnackbar(anchor);
                             snack.show("Password different de la confirmation", 1000);
                         }
                    }
                    else
                    {
                        JFXSnackbar snack = new JFXSnackbar(anchor);
                        snack.show("Username deja existant", 1000);
                    }
                }
                else
                {
                    JFXSnackbar snack = new JFXSnackbar(anchor);
                    snack.show("Email deja existant", 1000);

                }
            }
            else
            {
                JFXSnackbar snack = new JFXSnackbar(anchor);
                snack.show("Veuillez remplir tout les champs", 1000);
            }
        }
            
    }
    
}
