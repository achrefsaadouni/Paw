/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncewalking;
import Entity.AnnonceWalking;

import Service.AnnonceServices;
import Service.AnnonceSittingServices;
import Service.AnnonceWalkingServices;
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
public class FXMLAnnoncewalkingController implements Initializable {


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
    private JFXDatePicker  dateWalkInsertion;

    @FXML
    private JFXTextField dureWalkInsertion;

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
    private JFXDatePicker dateWalkModification;

    @FXML
    private JFXTextField dureWalkModification;
    
    @FXML
    private JFXTextField typeModification;
    
    @FXML
    private JFXDatePicker dateModification;

    

    @FXML
    private JFXTextField idSupression;
 
    @FXML
    private TableView<AnnonceWalking> tableView;

    @FXML
    private TableColumn<AnnonceWalking,String> idCol;

    @FXML
    private TableColumn<AnnonceWalking, String> ageCol;

    @FXML
    private TableColumn<AnnonceWalking, String> couleurCol;

    @FXML
    private TableColumn<AnnonceWalking, String> sexCol;

    @FXML
    private TableColumn<AnnonceWalking, String> raceCol;

    @FXML
    private TableColumn<AnnonceWalking, String> msgCol;

    @FXML
    private TableColumn<AnnonceWalking, String> typeCol;

    @FXML
    private TableColumn<AnnonceWalking, String> dateWalkCol;

    @FXML
    private TableColumn<AnnonceWalking, String> dureWalkCol;
    
    @FXML
    private ChoiceBox<String> choixInsertion;
    
    @FXML
    private TableColumn<AnnonceWalking, String> dateCol;
@FXML
    void actionInsertion2(ActionEvent event) {
//         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
//                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(java.sql.Date.valueOf(dateWalkInsertion.getValue())))&& (!"".equals(dureWalkInsertion.getText()))&& (!"".equals(choixInsertion.getValue())))
//        {
            AnnonceWalkingServices as = new AnnonceWalkingServices();
            as.insererAnnonceWalking (new AnnonceWalking(java.sql.Date.valueOf(dateWalkInsertion.getValue()), Integer.parseInt(dureWalkInsertion.getText()),0 , Integer.parseInt(ageInsertion.getText()), couleurInsertion.getText(), sexInsertion.getText(), raceInsertion.getText(), msgInsertion.getText(),choixInsertion.getValue(), null));
            
            dateWalkInsertion.setValue(LocalDate.now());
            dureWalkInsertion.setText("");
              
            ageInsertion.setText("");
            sexInsertion.setText("");
            raceInsertion.setText("");
            msgInsertion.setText("");
            couleurInsertion.setText("");
            choixInsertion.setValue("Chien");
            loadTable();
//        }


    }

    @FXML
    void actionModification2(ActionEvent event) 
    {

            
        
//            if ((!"".equals(idModification.getText()))&&(!"".equals(couleurModification.getText()))&& (!"".equals(ageModification.getText()))&& (!"".equals(sexModification.getText()))
//                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))&& (!"".equals(dateWalkModification.getValue()))&& (!"".equals(dureWalkModification.getText())) && (!"".equals(typeModification.getText())))
//        {
            AnnonceWalkingServices as = new AnnonceWalkingServices();
            
            as.updateAnnonceWalking(new 
             AnnonceWalking(                    
                    java.sql.Date.valueOf(dateWalkModification.getValue()),
                    Integer.parseInt(dureWalkModification.getText()),
                    Integer.parseInt(idModification.getText()),
                    Integer.parseInt(ageModification.getText()),
                    couleurModification.getText(),
                    sexModification.getText(),
                    raceModification.getText(),
                    msgModification.getText(),
                    typeModification.getText(),
                    java.sql.Date.valueOf(dateModification.getValue())),Integer.parseInt(idModification.getText()));
                    
                    
            
            
            
            
            idModification.setText("");
            ageModification.setText("");
            couleurModification.setText("");
            sexModification.setText("");
            raceModification.setText("");
            msgModification.setText("");
            typeModification.setText("Chien");
            dureWalkModification.setText("");
            dateWalkModification.setValue(LocalDate.of(1, 3, 2018)); 
//            loadTable();
                   
//        }

    }

    @FXML
    void actionSupression2(ActionEvent event) {

    if (!"".equals(idSupression.getText())){
            AnnonceWalkingServices ps = new AnnonceWalkingServices();
            ps.DeleteAnnonceWalking(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
   
    }

  
   
    
    @Override
      public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
        
       
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
        dureWalkCol.setCellValueFactory(new PropertyValueFactory<>("dureWalk"));
        dateWalkCol.setCellValueFactory(new PropertyValueFactory<>("dateWalk"));
    }

    private void loadTable() {
       AnnonceWalkingServices rs = new AnnonceWalkingServices();
        tableView.getItems().setAll(rs.getAll1());    
    }
}
