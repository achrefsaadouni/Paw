package paw.sittingService;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableSetValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;


public class FXMLSittingController implements Initializable {

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
    private JFXTextField nomPet;
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
    private JFXTextField taskText;
    
    @FXML
    private TreeTableView<String> toDoList;
    @FXML
    private TreeTableColumn<String, String> idTaches;
    @FXML
    private TreeTableColumn<String, String> taches;

    ObservableList<String> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typePet.getItems().add("Chien");
        typePet.getItems().add("Chat");
        typePet.getItems().add("Autre");
        sexeM.setToggleGroup(sexe);
        sexeF.setToggleGroup(sexe);
//        System.out.println(session.getId());
    }    

    @FXML
    private void redirection(ActionEvent event) {
    }

    @FXML
    private void ValiderTraining(ActionEvent event) {
    }

    @FXML
    private void annulerTraining(ActionEvent event) {
    }

    @FXML
    private void addTask(ActionEvent event) {
        
        toDoList.setEditable(true);
        list.add(taskText.getText());
        
        new PropertyValueFactory<String>("firstName");                      
    }
    
}
