package paw.trainingService;

import Entity.AnnonceTraining;
import Service.AnnonceTrainingServices;
import Service.TypeTrainingServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import paw.FXMLSplashController;
import static paw.Paw.session;
import paw.mainUI.FXMLCnxController;
import paw.profile.FXMLprofileController;

public class FXMLTrainingController extends FXMLCnxController implements Initializable {

    @FXML
    private TextField racePet;
    @FXML
    private TextField nomPet;
    @FXML
    private TextField agePet;
    @FXML
    private DatePicker dateTr;
    @FXML
    private RadioButton sexeM;
    @FXML
    private RadioButton sexeF;
    @FXML
    private JFXComboBox<String> typeTr;
    @FXML
    private JFXComboBox<String> typePet;
    @FXML
    private JFXTextField colorPet;
    
    ToggleGroup sexe = new ToggleGroup();
    @FXML
    private JFXButton validerB;
    @FXML
    private JFXButton annulerB;
    @FXML
    private JFXTextField descPet;
    @FXML
    private ImageView imgTr3;
    @FXML
    private JFXButton retourB;
    @FXML
    private AnchorPane window;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeTrainingServices ss = new TypeTrainingServices();
        typeTr.setItems(ss.getString());
        typePet.getItems().add("Chien");
        typePet.getItems().add("Chat");
        typePet.getItems().add("Autre");
        sexeM.setToggleGroup(sexe);
        sexeF.setToggleGroup(sexe);
        System.out.println(session.getId());

        
    }    

    @FXML
    private void ValiderTraining(ActionEvent event) {
        if ((typeTr.getValue().isEmpty())
                 || (colorPet.getText().trim().equals(""))|| (agePet.getText().trim().equals(""))||(!isInteger(agePet))
                || (racePet.getText().trim().equals(""))|| (descPet.getText().trim().equals(""))|| (typePet.getValue().isEmpty()))
                 {
                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
                    fail.setHeaderText("erreur");
                    fail.setContentText("Vous avez oublier de remplir un ou plusieurs champs");
                    fail.showAndWait();
                 }else{
        
        String type = "Annonce Training";
        String s="";
        if (sexeM.isSelected())
        {
            s="Male";
        }
        else
        {
            s="Female";
        }
        
        
        AnnonceTrainingServices as = new AnnonceTrainingServices();
        System.out.println(session.getId());
            as.insererAnnonceTraining(
                    new AnnonceTraining(
                    java.sql.Date.valueOf(dateTr.getValue()), 
                    (String)typeTr.getValue(),
                    (String)typePet.getValue(),
                            nomPet.getText() , 
                            0,
                            Integer.parseInt(agePet.getText()), 
                            colorPet.getText(), 
                            s, 
                            racePet.getText(),
                            descPet.getText(),
                            type,
                            null,
                            session.getId()));
//            initialisation des champs
            dateTr.setValue(LocalDate.now());
            typeTr.setValue("");
            agePet.setText("");
            racePet.setText("");
            descPet.setText("");
            colorPet.setText("");
            typePet.setValue("");
            dateTr.setValue(null);
    }
    }

    @FXML
    private void annulerTraining(ActionEvent event) {
         dateTr.setValue(null);
            typeTr.setValue("");
            agePet.setText("");
            racePet.setText("");
            descPet.setText("");
            colorPet.setText("");
            typePet.setValue("");
//            sexe.setUserData(null);
    }

    @FXML
    private void redirection(ActionEvent event) {
            loadSplashScreen("/paw/trainingService/FXMLTrainingPrincipal.fxml");
    }

    private boolean isInteger(TextField input) {
        try {
            int age = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

 
  

    

   
    
    
}
