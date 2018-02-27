/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.conseil.admin;

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

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLAjoutConseilController implements Initializable {

    @FXML
    private JFXTextField titreInsertion;
    @FXML
    private ChoiceBox<String> animalInsertion;
    @FXML
    private ChoiceBox<String> typeInsertion;
    @FXML
    private JFXTextArea descriptionInsertion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animalInsertion.getItems().setAll("CHIEN","CHAT","AUTRES");
        animalInsertion.setValue("CHIEN");
        
        
        typeInsertion.getItems().setAll("NUTRITION","SANTE");
        typeInsertion.setValue("NUTRITION");
        
    }    

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
        }
    }
    
}
