/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu.user;

import Service.AnnoncePerduServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import paw.MyNotifications;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLAjouterAnnoncePerduController implements Initializable {

     private File file;
      private List<File> files = new ArrayList<>();
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
    private JFXTextField colierInsertion3;
    @FXML
    private JFXTextField sexInsertion;
    @FXML
    private JFXTextField msgInsertion;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    @FXML
    private Label nom11;
    @FXML
    private ChoiceBox<String> choixInsertion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
      
        
        
    }    

    @FXML
    private void actionInsertion2(ActionEvent event) {
         
         if (files.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image ou bien des  donnes concernant l'annonce ", ButtonType.CLOSE);
            alert.show();
            upload.requestFocus();
        } 
         else if((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue()))
                 && (!"".equals(colierInsertion3.getText()))&& (!"".equals(lieuxInsertion3.getText())))
        {
              AnnoncePerduServices  as = new AnnoncePerduServices();
            
            dateinsertionp.setValue(LocalDate.now());
            colierInsertion3.setText("");
            lieuxInsertion3.setText("");
            ageInsertion.setText("");
            sexInsertion.setText("");
            raceInsertion.setText("");
            msgInsertion.setText("");
            couleurInsertion.setText("");
            choixInsertion.setValue("Chien");
            MyNotifications.infoNotification("Ajout", "Votre annonce est  ajouté avec Succès");
       
        }

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
            files.add(0, file);
            Image im = new Image("file:///" + file.toPath().toString());
            imajout1.setImage(im);
        }
    }
    
}
