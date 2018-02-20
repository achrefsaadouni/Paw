package paw.trainingService;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import paw.mainUI.FXMLCnxController;
import paw.profile.FXMLprofileController;

public class FXMLTrainingPrincipalController extends FXMLCnxController implements Initializable{

    @FXML
    private AnchorPane window;
    @FXML
    private AnchorPane reclamations;
    @FXML
    private JFXButton listButton;
    @FXML
    private AnchorPane reclamations1;
    @FXML
    private JFXButton ajoutButton;
    @FXML
    private AnchorPane reclamations11;
    @FXML
    private JFXButton conseilButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void goToListe(ActionEvent event) {
        try{
            loadSplashScreen("/paw/trainingService/FXMLTrainingListeAnnonce.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAjout(ActionEvent event) {
        try{
            loadSplashScreen("/paw/trainingService/FXMLTraining.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToConseils(ActionEvent event) {
        try{
            loadSplashScreen("/paw/trainingService/FXMLTrainingConseils.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
