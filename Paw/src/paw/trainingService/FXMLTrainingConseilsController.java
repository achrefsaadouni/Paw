package paw.trainingService;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import paw.profile.FXMLprofileController;

public class FXMLTrainingConseilsController implements Initializable {

    @FXML 
    private WebView web1;
    private WebEngine engine1;
    @FXML 
    private WebView web2;
    private WebEngine engine2;
    @FXML
    private JFXButton retourB;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         engine1 = web1.getEngine();
         engine1.load("http://youtube.com/embed/yMGKTrwSyuY?enablejsapi=1&origin");
         engine2 = web2.getEngine();
         engine2.load("http://youtube.com/embed/jFMA5ggFsXU?enablejsapi=1&origin");
         
//                    http://www.youtube.com/embed/M7lc1UVf-VE?enablejsapi=1&origin
    }    

    @FXML
    private void redirection(ActionEvent event) {
        try{
//            loadSplashScreen("/paw/trainingService/FXMLTrainingPrincipal.fxml");
            System.out.println("redirection in progress !!");
            Parent trainingPrincipalParent = FXMLLoader.load(getClass().getResource("FXMLTrainingPrincipal.fxml"));
            Scene trainingPrincipalScene = new Scene(trainingPrincipalParent);  
            Stage app_stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
            app_stage.setScene(trainingPrincipalScene);
            app_stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
        
        
    
}
