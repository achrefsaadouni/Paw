package paw.sittingService;

import Entity.AnnonceSitting;
import Service.AnnonceSittingServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static paw.Paw.session;
import paw.mainUI.FXMLCnxController;
import paw.profile.FXMLprofileController;


public class FXMLSittingController extends FXMLCnxController implements Initializable {

    @FXML
    private ImageView imgTr3;
    @FXML
    private JFXButton retourB;
    @FXML
    private JFXTextField racePet;
    @FXML
    private JFXRadioButton sexeM;
    @FXML
    private JFXRadioButton sexeF;
    
    ToggleGroup sexe = new ToggleGroup();
    
    @FXML
    private JFXTextField agePet;
    @FXML
    private JFXTextField colorPet;
    @FXML
    private JFXTextField descPet;
    @FXML
    private JFXButton validerB;
    @FXML
    private JFXButton annulerB;
    @FXML
    private JFXDatePicker dateSit;
    @FXML
    private JFXComboBox<String> typePet;

    @FXML
    private JFXButton addButton;
    
    @FXML
    private JFXSlider dureSit;
    
    @FXML
    private JFXTextField taskText;
    
    @FXML
    private ListView<String> listView;
    
    ArrayList<String> toDoList = new ArrayList();
    @FXML
    private AnchorPane window;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typePet.getItems().add("Chien");
        typePet.getItems().add("Chat");
        typePet.getItems().add("Autre");
        sexeM.setToggleGroup(sexe);
        sexeF.setToggleGroup(sexe);
        dateSit.setValue(null);
        
        
//        System.out.println(session.getId());
        
    }    

    @FXML
    private void redirection(ActionEvent event) {
        try{
            loadSplashScreen("/paw/sittingService/FXMLSittingPrincipale.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ValiderTraining(ActionEvent event) {
        
        toDoList.addAll(listView.getItems());
        System.out.println(toDoList);
        toDoList.getClass();
        System.out.println(toDoList.toString());
        System.out.println((int)dureSit.getValue());
        String s="";
        if (sexeM.isSelected())
        {
            s="Male";
        }
        else
        {
            s="Female";
        }
        String typeAnnonce = "Annonce Sitting";
         if ((toDoList.isEmpty())
                 || (colorPet.getText().trim().equals(""))|| (agePet.getText().trim().equals(""))||(!isInteger(agePet))
                || (racePet.getText().trim().equals(""))|| (descPet.getText().trim().equals(""))|| (typePet.getValue().isEmpty()))
                 {
                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
                    fail.setHeaderText("erreur");
                    fail.setContentText("Vous devez remplir touts les champs");
                    fail.showAndWait();
                 }else{
        
        AnnonceSittingServices as = new AnnonceSittingServices();
        System.out.println(toDoList);
            as.insererAnnonceSitting(
                    new AnnonceSitting(
                    java.sql.Date.valueOf(dateSit.getValue()), 
                    (int)dureSit.getValue(),
                    toDoList.toString(),
                    typePet.getValue(),
                    0,
                    Integer.parseInt(agePet.getText()), 
                    colorPet.getText(), 
                    s, 
                    racePet.getText(),
                    descPet.getText(),
                    typeAnnonce,
                    Date.valueOf(LocalDate.now()),
                    session.getId()));
                    
         }
//            initialisation des champs
           
            System.out.println((int)dureSit.getValue());
            System.out.println(session.getId());
            
            dateSit.setValue(null);
            dureSit.setValue(15);
            listView.setItems(null);
            agePet.setText("");
            racePet.setText("");
            descPet.setText("");
            colorPet.setText("");
            typePet.setValue("");
    }
        
    

    @FXML
    private void annulerTraining(ActionEvent event) {
        
        //            initialisation des champs
            
            dateSit.setValue(null);
            dureSit.setValue(15);
            listView.setItems(null);
            agePet.setText("");
            racePet.setText("");
            descPet.setText("");
            colorPet.setText("");
            typePet.setValue("");
            
    }

    @FXML
    private void addTask(ActionEvent event) {
        listView.getItems().add(taskText.getText());   
        taskText.setText("");
    }    

    private boolean isInteger(JFXTextField input) {
        try {
            int age = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

  
