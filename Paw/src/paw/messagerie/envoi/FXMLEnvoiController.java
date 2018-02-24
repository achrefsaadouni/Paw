/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.messagerie.envoi;

import Entity.Messagerie;
import Entity.Notification;
import Entity.Utilisateur;
import Service.MessagerieService;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLEnvoiController implements Initializable {

   @FXML
    private Label label;
    UtilisateurServices s = new UtilisateurServices();
    List<Utilisateur> m;
    @FXML
    private ComboBox<String> comboemail;
    @FXML
    private JFXTextArea ContenuMsg;
    
    private int user_id;
    
    
   

    @FXML
    public void ajouter(ActionEvent event) {

        int index = comboemail.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            int id = m.get(index).getId();
            Messagerie p = new Messagerie(ContenuMsg.getText(), user_id, id, false, false);
            MessagerieService ps = new MessagerieService();
            ps.ajouterMessage(p);
         //   Notification.createNotification("Succès", "Votre message a bien été envoyé");
          //  redirection(event);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       user_id = 2;   //session;getid
        m = s.findAll();
        Utilisateur u=null;
        for (Utilisateur k : m) {
            if (k.getId() != user_id) {
                comboemail.getItems().addAll(k.getEmail());
                u=k;
            }

        }
        m.remove(u);
        
    }

    public void redirection(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/paw/messagerie/inbox/FXMLView_inbox.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } catch (IOException ex) {
          //  Logger.getLogger(EnvoieController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

}
