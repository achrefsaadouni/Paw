/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.profile;

import Service.AchatService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import static paw.Paw.session;

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
        AchatService s = new AchatService();
        achats.setText(String.valueOf(s.nombreAchat(session.getId())));
    }    
    
}
