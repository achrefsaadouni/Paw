/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncetraining;
import Entity.AnnonceTraining;

import Service.AnnonceServices;
import Service.AnnonceSittingServices;
import Service.AnnonceTrainingServices;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.skins.JFXDatePickerContent;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLAnnonceTrainingController implements Initializable {


    @FXML
    private JFXTextField msgInsertion;

    @FXML
    private JFXTextField ageInsertion;


    @FXML
    private JFXTextField sexInsertion;

    @FXML
    private JFXTextField couleurInsertion;

    @FXML
    private JFXTextField raceInsertion;
    
    @FXML
    private JFXDatePicker  dateTrInsertion;

    @FXML
    private JFXTextField typeTrInsertion;
    
    @FXML
    private JFXTextField dureTrInsertion;

    @FXML
    private JFXTextField idModification;

    @FXML
    private JFXTextField couleurModification;

    @FXML
    private JFXTextField sexModification;

    @FXML
    private JFXTextField msgModification;

    @FXML
    private JFXTextField raceModification;


    @FXML
    private JFXTextField ageModification;
    
    @FXML
    private JFXDatePickerContent dateTrModification;

    @FXML
    private JFXTextField typeTrModification;
    
    @FXML
    private JFXTextField dureTrModification;
    
    @FXML
    private JFXTextField typeModification;
    
    @FXML
    private JFXDatePicker dateModification;

    

    @FXML
    private JFXTextField idSupression;
 
    @FXML
    private TableView<AnnonceTraining> tableView;

    @FXML
    private TableColumn<AnnonceTraining,String> idCol;

    @FXML
    private TableColumn<AnnonceTraining, String> ageCol;

    @FXML
    private TableColumn<AnnonceTraining, String> couleurCol;

    @FXML
    private TableColumn<AnnonceTraining, String> sexCol;

    @FXML
    private TableColumn<AnnonceTraining, String> raceCol;

    @FXML
    private TableColumn<AnnonceTraining, String> msgCol;

    @FXML
    private TableColumn<AnnonceTraining, String> typeCol;

    @FXML
    private TableColumn<AnnonceTraining, String> dateTrCol;

    @FXML
    private TableColumn<AnnonceTraining, String> typeTrCol;
    
    @FXML
    private TableColumn<AnnonceTraining, String> dureTrCol;
    
    @FXML
    private ChoiceBox<String> choixInsertion;
    
    @FXML
    private TableColumn<AnnonceTraining, String> dateCol;
@FXML
   /* void actionInsertion2(ActionEvent event) {
         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(java.sql.Date.valueOf(dateTrInsertion.getValue())))&& (!"".equals(typeTrInsertion.getText()))&& (!"".equals(dureTrInsertion.getText()))&& (!"".equals(choixInsertion.getValue())))
        {
            AnnonceTrainingServices as = new AnnonceTrainingServices();
              as.insererAnnonceTraining(new AnnonceTraining(java.sql.Date.valueOf(dateTrInsertion.getValue()),, Integer.parseInt(dureTrInsertion.getText()), typeTrInsertion.getText(),0 , Integer.parseInt(ageInsertion.getText()), couleurInsertion.getText(), sexInsertion.getText(), raceInsertion.getText(), msgInsertion.getText(),choixInsertion.getValue(), null));
            dateTrInsertion.setValue(LocalDate.now());
            typeTrInsertion.setText("");
              
             ageInsertion.setText("");
            sexInsertion.setText("");
            raceInsertion.setText("");
            msgInsertion.setText("");
            couleurInsertion.setText("");
            choixInsertion.setValue("Chien");
            loadTable();
        }


    }

    @FXML
    void actionModification2(ActionEvent event) 
    {

            
        
            if ((!"".equals(idModification.getText()))&&(!"".equals(couleurModification.getText()))&& (!"".equals(ageModification.getText()))&& (!"".equals(sexModification.getText()))
                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))&& (!"".equals(java.sql.Date.valueOf(dateTrModification.getTypeSelector())))&& (!"".equals(dureTrModification.getText())) &&(!"".equals(typeTrModification.getText())) && (!"".equals(typeModification.getText())))
        {
            AnnonceTrainingServices as = new AnnonceTrainingServices();
            
            as.updateAnnonceTraining(new 
             AnnonceTraining(
                    
                    dateTrModification.getText(),
                    Integer.parseInt(dureTrModification.getText()),
                    typeTrModification.getText(),
                    Integer.parseInt(idModification.getText()),
                    Integer.parseInt(ageModification.getText()),
                    couleurModification.getText(),
                    sexModification.getText(),
                    raceModification.getText(),
                    msgModification.getText(),
                    typeModification.getText()),
                    null);
                    
            
            
            
            
            idModification.setText("");
            ageModification.setText("");
         couleurModification.setText("");
         sexModification.setText("");
           raceModification.setText("");
           msgModification.setText("");
             typeModification.setText("Chien");
             dateTrModification.getText("");
             dureTrModification.setText("");
             typeTrModification.setText("");
             
            loadTable();
                   
        }

    }

    @FXML
    void actionSupression2(ActionEvent event) {

    if (!"".equals(idSupression.getText())){
            AnnonceTrainingServices ps = new AnnonceTrainingServices();
            ps.DeleteAnnonceTraining(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
   
    }*/

  
   
    
    @Override
      public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
        
        typeModification.setText("Chien");
    }    
    

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        raceCol.setCellValueFactory(new PropertyValueFactory<>("race"));
        msgCol.setCellValueFactory(new PropertyValueFactory<>("message_complementaire"));
        couleurCol.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeTrCol.setCellValueFactory(new PropertyValueFactory<>("typeTr"));
        dateTrCol.setCellValueFactory(new PropertyValueFactory<>("dateTr"));
        dureTrCol.setCellValueFactory(new PropertyValueFactory<>("dureTr"));
    }

    private void loadTable() {
       AnnonceTrainingServices rs = new AnnonceTrainingServices();
        tableView.getItems().setAll(rs.getAll1());    
    }
}
