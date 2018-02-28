/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceadoption;

import Entity.AnnonceAdoption;
import Service.AnnonceAdoptionService;
import Utility.Checksum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import static paw.Paw.session;
import paw.mainUI.FXMLinscriptionController;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLajouterController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField couleur;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<String> race;
    @FXML
    private JFXTextArea msg;
    @FXML
    private JFXToggleButton typeAdoption;
    @FXML
    private JFXDatePicker debut;
    @FXML
    private JFXDatePicker fin;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;

    private File file;
    private String chaine ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberValidator nv = new NumberValidator();
        nv.setMessage("Veuillez saisir une age valide");
        RequiredFieldValidator rf = new RequiredFieldValidator();
        rf.setMessage("Veuillez saisir la couleur");
        RequiredFieldValidator rf1 = new RequiredFieldValidator();
        rf1.setMessage("Veuillez donner plus de détails");
        age.getValidators().add(nv);
        age.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    age.validate();
                }
            }
        });
        couleur.getValidators().add(rf);
        couleur.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    couleur.validate();
                }
            }
        });
        msg.getValidators().add(rf1);
        msg.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    msg.validate();
                }
            }
        });
        
        
        
        
        
        chaine="";
        ToggleGroup groupsexe = new ToggleGroup();
        male.setToggleGroup(groupsexe);
        female.setToggleGroup(groupsexe);
        male.setSelectedColor(Color.rgb(51, 122, 183));
        female.setSelectedColor(Color.rgb(51, 122, 183));
        
        debut.setValue(LocalDate.now());
        fin.setValue(LocalDate.now());
        
        debut.setVisible(false);
        fin.setVisible(false);
        
        typeAdoption.setOnAction((event) -> {
            if (debut.isVisible())
            {
               debut.setVisible(false);
                fin.setVisible(false);
            }
            else
            {
                debut.setVisible(true);
                fin.setVisible(true);
            }
        });
        ObservableList<String> items = FXCollections.observableArrayList(
            "Chien","Chat","Rongeur","Oiseau","Poisson");
        type.setItems(items);
        
        ObservableList<String> Chien = FXCollections.observableArrayList(
            "Berger","Pitbull","Dalmatien","Kangal","Caniche","Chihuahua","Mastiff","Boxer","Rottweiler","Dobermann","Beagle","Husky","Autre");
        ObservableList<String> Chat = FXCollections.observableArrayList(
            "Siamois","Persan","Main Coon","Sacré de Birmanie","Bengal","Chartreux","British Shorthair","Norvégien","Ragdoll","Exotic Shorthair","Sphynx","Autre");
        ObservableList<String> Rongeur = FXCollections.observableArrayList(
            "Hamster","Lapin nain","Cochon d’Inde","Chinchilla","Souris","Rat","Gerbille","Octodon","Autre");
        ObservableList<String> Oiseau = FXCollections.observableArrayList(
            "Perruche","Canari","Amazone","Gris du Gabon","Rossignol","Diamant","Autre");
        ObservableList<String> Poisson = FXCollections.observableArrayList(
            "Combattant","Platty","Poisson rouge","Colisa","Betta","Tilapia","Oscar","Barbus","Autre");
        //race.setItems(Chien);
        type.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            switch (newValue)
            {
                case "Chien" : race.setItems(Chien); break;
                case "Chat" : race.setItems(Chat); break;
                case "Rongeur" : race.setItems(Rongeur); break;
                case "Oiseau" : race.setItems(Oiseau); break;
                case "Poisson" : race.setItems(Poisson); break;
            }
        }
        ); 
        

        
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
            imajout1.setImage(im);
                   
           
        try {
            String imageName = Checksum.createChecksum(file.getAbsolutePath());
            String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
            String filePath = "c:\\wamp64\\www\\paw\\web\\images\\pawAdoption\\"+imageName + extension;
            chaine =imageName + extension;
            File dest = new File(filePath);
            Files.copy(
                    file.toPath(), 
                    dest.toPath(), 
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            Logger.getLogger(FXMLinscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
        
    }

    @FXML
    private void insertion(ActionEvent event) {
        if (chaine.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image pour votre annonce", ButtonType.CLOSE);
            alert.show();
            upload.requestFocus();
            

        }
        else{
            String sexe="Male";
            if (female.isSelected())
            {
                sexe="Female";
            }                   
            boolean test = true ;
            String adoptionType ="Permanante";
            if (typeAdoption.isSelected()){
                adoptionType="Temporaire";
                test = valideDate(fin.getValue(),debut.getValue());
            }
            
            //test sur les date
            couleur.validate();
            age.validate();
            msg.validate();
            if(age.validate() && couleur.validate() && msg.validate())
            {
                if(race.getValue()!=null && type.getValue()!=null)
                {
                    if(test)
                    {
                        AnnonceAdoptionService service = new AnnonceAdoptionService();
                        service.inserer(new AnnonceAdoption(
                                            adoptionType, 
                                            java.sql.Date.valueOf(debut.getValue()), 
                                            java.sql.Date.valueOf(fin.getValue()), 
                                            0, 
                                            Integer.parseInt(age.getText()), 
                                            couleur.getText(), 
                                            sexe, 
                                            race.getValue(), 
                                            msg.getText(), 
                                            type.getValue(), 
                                            null, 
                                            session.getId(), 
                                            file, 
                                            "Disponible")
                                        ,chaine);

                        Notifications.create().title("Offre enregstrée").text("Votre offre d'adoption est publiée.").show();
                        debut.setValue(LocalDate.now());
                        fin.setValue(LocalDate.now());
                        age.setText("");
                        couleur.setText("");
                        msg.setText("");
                        chaine="";
                        type.setValue("");
                        race.setValue("");
                        race.setItems(null);
                    }
                    else{
                        Notifications.create()
                          .title("Date invalide")
                          .text("Date de fin doit être supérieur à la date de début")
                          .showWarning();
                    }
                }
                else{
                    Notifications.create()
                          .title("Informations manquantes")
                          .text("Veuillez choisir la race")
                          .showWarning();
                }
            }
            else{
                
            }
            
        }
        
    }

    public boolean isInteger(JFXTextField input) {
        try {
            int prix = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean valideDate(LocalDate x , LocalDate y)
    {
        if (x.compareTo(y) < 0)
            return false;
        else
            return true;
                
    }
}

