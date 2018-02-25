package paw.trainingService;

import Entity.AnnonceTraining;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static paw.Paw.session;


public class FXMLModificationAnnonceController implements Initializable {

    @FXML
    private GridPane tableau;
    @FXML
    private JFXTextField animal;
    @FXML
    private JFXTextField race;
    @FXML
    private JFXTextField sexe;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField couleur;
    @FXML
    private JFXTextField nom;
    
    @FXML
    private JFXButton ModifButton;
    @FXML
    private JFXButton SuppButton;
    @FXML
    private JFXTextField desc;
    @FXML
    private JFXDatePicker dateTr;
    @FXML
    private JFXComboBox<String> typeTr;

    AnnonceTraining annonce;  

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeTr.getItems().add("Puppy Training");
        typeTr.getItems().add("Beginner Training");
        typeTr.getItems().add("Advanced Training");
        
        
        
        System.out.println(session.getId());
        
    }    

    @FXML
    private void modifAnnonce(ActionEvent event) {
        
    }

    @FXML
    private void suppAnnonce(ActionEvent event) {
    }
    
    
    
}
