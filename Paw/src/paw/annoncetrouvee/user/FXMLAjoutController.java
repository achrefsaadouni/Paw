/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncetrouvee.user;

import Entity.AnnonceTrouvee;
import Service.AnnonceTrouveServices;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLAjoutController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField raceInsertion;

    @FXML
    private JFXTextField couleurInsertion;

    @FXML
    private JFXTextField ageInsertion;

    @FXML
    private Label nom11;

    @FXML
    private ChoiceBox<String> choixInsertion;

    @FXML
    private JFXTextField colierInsertion3;

    @FXML
    private JFXTextField lieuxInsertion3;

    @FXML
    private JFXTextField msgInsertion;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    void actionInsertion2(ActionEvent event) {
        
         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue()))&& (!"".equals(colierInsertion3.getText()))&& (!"".equals(lieuxInsertion3.getText())))
        {
// String colier, Date date_trouvee, String lieu_perdu, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date           
              AnnonceTrouveServices as = new AnnonceTrouveServices();
            String sexe="Male";
            if (female.isSelected())
            {
                sexe="Female";
            }
            as.insererAnnonceTrouvee(new AnnonceTrouvee(colierInsertion3.getText(), null, lieuxInsertion3.getText(),0 , Integer.parseInt(ageInsertion.getText()), couleurInsertion.getText(), sexe, raceInsertion.getText(), msgInsertion.getText(),choixInsertion.getValue(), null));

           
            colierInsertion3.setText("");
            lieuxInsertion3.setText("");
            ageInsertion.setText("");
            raceInsertion.setText("");
            msgInsertion.setText("");
            couleurInsertion.setText("");
            choixInsertion.setValue("Chien");
        
        }


    }

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
          ToggleGroup groupm = new ToggleGroup();
           male.setToggleGroup(groupm);
        female.setToggleGroup(groupm);
    }    
    
}
