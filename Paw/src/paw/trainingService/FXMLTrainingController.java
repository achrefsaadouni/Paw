package paw.trainingService;

import Entity.AnnonceTraining;
import Service.AnnonceTrainingServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import static paw.Paw.session;

public class FXMLTrainingController implements Initializable {

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
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeTr.getItems().add("Puppy Training");
        typeTr.getItems().add("Beginner Training");
        typeTr.getItems().add("Advanced Training");
        typePet.getItems().add("Chien");
        typePet.getItems().add("Chat");
        typePet.getItems().add("Autre");
        sexeM.setToggleGroup(sexe);
        sexeF.setToggleGroup(sexe);
        
    }    

    @FXML
    private void ValiderTraining(ActionEvent event) {
        System.out.println(typeTr.getValue());
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
            dateTr.setValue(LocalDate.now());
            typeTr.setValue("");
            agePet.setText("");
            racePet.setText("");
            descPet.setText("");
            colorPet.setText("");
            typePet.setValue("");
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

   
    
    
}
