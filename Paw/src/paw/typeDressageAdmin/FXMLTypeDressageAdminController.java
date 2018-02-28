package paw.typeDressageAdmin;

import Entity.TypeTraining;
import Service.TypeTrainingServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class FXMLTypeDressageAdminController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private JFXTextField addText;
    @FXML
    private JFXButton add;
    @FXML
    private JFXTextField modifID;
    @FXML
    private JFXTextField modifText;
    @FXML
    private JFXButton modif;
    @FXML
    private JFXTextField suppID;
    @FXML
    private JFXButton supp;
    @FXML
    private TableView<TypeTraining> table;
    @FXML
    private TableColumn<TypeTraining, Integer> idCol;
    @FXML
    private TableColumn<TypeTraining, String> typeCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();   
    }    

    @FXML
    private void ajouterType(ActionEvent event) {
        TypeTrainingServices s = new TypeTrainingServices();
        s.inserer(addText.getText());
        initCol();
        loadTable();  
        addText.setText("");
    }

    @FXML
    private void modifType(ActionEvent event) {
        TypeTrainingServices s = new TypeTrainingServices();
        s.update(new TypeTraining(Integer.parseInt(modifID.getText()),modifText.getText()));
        initCol();
        loadTable(); 
        modifText.setText("");
        modifID.setText("");

    }

    @FXML
    private void suppType(ActionEvent event) {
        TypeTrainingServices s = new TypeTrainingServices();
        s.Delete(Integer.parseInt(suppID.getText()));
        initCol();
        loadTable();
        suppID.setText("");

    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    private void loadTable() {
        TypeTrainingServices rs = new TypeTrainingServices();
        table.getItems().setAll(rs.getAll1());
    }
    
}
