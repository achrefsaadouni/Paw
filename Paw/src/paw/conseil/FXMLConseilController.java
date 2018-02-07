/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.conseil;

import Entity.Conseil;
import Service.ConseilServices;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLConseilController implements Initializable {

    @FXML
    private JFXTextField titreInsertion;
    @FXML
    private ChoiceBox<String> animalInsertion;
    @FXML
    private ChoiceBox<String> typeInsertion;
    @FXML
    private JFXTextArea descriptionInsertion;
    @FXML
    private JFXTextField idInsertion1;
    @FXML
    private JFXTextField titreInsertion1;
    @FXML
    private ChoiceBox<String> animalInsertion1;
    @FXML
    private ChoiceBox<String> typeInsertion1;
    @FXML
    private JFXTextArea descriptionInsertion1;
    @FXML
    private JFXTextField idSupression;
    @FXML
    private TableView<Conseil> tableView;
    @FXML
    private TableColumn<Conseil, String> idCol;
    @FXML
    private TableColumn<Conseil, String> titreCol;
    @FXML
    private TableColumn<Conseil, String> animalCol;
    @FXML
    private TableColumn<Conseil, String> typeCol;
    @FXML
    private TableColumn<Conseil, String> descriptionCol;

    /**
     * Initializes the controller class.
     */
        

    @FXML
    private void actionInsertion(ActionEvent event) {
        if ((!"".equals(titreInsertion.getText()))&&(!"".equals(animalInsertion.getValue()))&&(!"".equals(descriptionInsertion.getText()))&&(!"".equals(typeInsertion.getValue())))
        {
            ConseilServices service = new ConseilServices();
            service.insererConseil(new Conseil(0,titreInsertion.getText(),animalInsertion.getValue(),typeInsertion.getValue(),descriptionInsertion.getText(),null));
            titreInsertion.setText("");
            animalInsertion.setValue("CHIEN");
            typeInsertion.setValue("NUTRITION");
            descriptionInsertion.setText("");
            
            loadTable();
        }
    }

    @FXML
    private void actionModification(ActionEvent event) {
        if ((!"".equals(titreInsertion1.getText()))&&(!"".equals(animalInsertion1.getValue()))&&(!"".equals(descriptionInsertion1.getText()))&&(!"".equals(typeInsertion1.getValue())))
        {
            ConseilServices service = new ConseilServices();
            service.updateConseil(new Conseil(0,titreInsertion1.getText(),animalInsertion1.getValue(),typeInsertion1.getValue(),descriptionInsertion1.getText(),null),Integer.parseInt(idInsertion1.getText()));
            titreInsertion1.setText("");
            animalInsertion1.setValue("CHIEN");
            typeInsertion1.setValue("NUTRITION");
            descriptionInsertion1.setText("");
            
            loadTable();
    }
    }

    @FXML
    private void actionSupression(ActionEvent event) {
        if (!"".equals(idSupression.getText())){
            ConseilServices service = new ConseilServices();
            service.DeleteConseil(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        animalInsertion.getItems().setAll("CHIEN","CHAT","AUTRES");
        animalInsertion.setValue("CHIEN");
        animalInsertion1.getItems().setAll("CHIEN","CHAT","AUTRES");
        animalInsertion1.setValue("CHIEN");
        
        typeInsertion.getItems().setAll("NUTRITION","SANTE");
        typeInsertion.setValue("NUTRITION");
        typeInsertion1.getItems().setAll("NUTRITION","SANTE");
        typeInsertion1.setValue("NUTRITION");
        
        initCol();
        loadTable();
    }
    
    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        animalCol.setCellValueFactory(new PropertyValueFactory<>("animal"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    }

    private void loadTable() {
        ConseilServices service = new ConseilServices();
        tableView.getItems().setAll(service.getAll());    
    }
    
}
