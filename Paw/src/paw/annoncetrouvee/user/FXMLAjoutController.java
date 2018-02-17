/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncetrouvee.user;

import Entity.AnnonceTrouvee;
import Service.AnnonceTrouveServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.mail.Session;
import static paw.Paw.session;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLAjoutController implements Initializable {

   private File file;
    @FXML
    private JFXTextField raceInsertion;
    @FXML
    private JFXTextField couleurInsertion;
    @FXML
    private JFXTextField ageInsertion;
    @FXML
    private JFXDatePicker dateinsertionp;
    @FXML
    private JFXTextField lieuxInsertion3;
    @FXML
    private JFXTextField msgInsertion;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton ColierN;
    @FXML
    private JFXRadioButton ColierO;
    @FXML
    private Label nom11;
    @FXML
    private ChoiceBox<String> choixInsertion;


    @FXML
    void actionInsertion2(ActionEvent event) {
        
         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))
                 && (!"".equals(choixInsertion.getValue()))&& (!"".equals(lieuxInsertion3.getText())))
        {       
            
            AnnonceTrouveServices as = new AnnonceTrouveServices();
            String sexe="Male";
            if (female.isSelected())
            {
                sexe="Female";
            }
             String colier="oui";
            if (ColierN.isSelected())
            {
                sexe="non";
            }
            as.insererAnnonceTrouvee(new AnnonceTrouvee(colier,java.sql.Date.valueOf(dateinsertionp.getValue()), 
                    lieuxInsertion3.getText(),0 , 
                    Integer.parseInt(ageInsertion.getText()), 
                    couleurInsertion.getText(), 
                    sexe, 
                    raceInsertion.getText(), 
                    msgInsertion.getText(),
                    choixInsertion.getValue(), 
                    null,
                    session.getId(),
                   
                    file));

            dateinsertionp.setValue(LocalDate.now());
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
        ToggleGroup groupi= new ToggleGroup() ; 
        ColierN.setToggleGroup(groupi);
        ColierO.setToggleGroup(groupi);
    }    

    @FXML
    private void fileChoosing(ActionEvent event) {
   
      Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
       
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            Image im = new Image("file:///" + file.toPath().toString());
            System.out.println("hani hneee");
            imajout1.setImage(im);
           
        }
    
    
    
    
    
    }
    
}
