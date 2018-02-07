/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncesitting;
import Entity.AnnonceSitting;

import Service.AnnonceServices;
import Service.AnnonceSittingServices;
import Service.AnnonceWalkingServices;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.skins.JFXDatePickerContent;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
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
public class FXMLAnnonceSittingController implements Initializable {


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
    private JFXDatePicker  dateSitInsertion;

    @FXML
    private JFXTextField typeSitInsertion;

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
    private JFXDatePicker dateSitModification;

    @FXML
    private JFXTextField typeSitModification;
    
    private JFXTextField typeModification;
    
    private JFXDatePicker dateModification;

    

    @FXML
    private JFXTextField idSupression;
 
    @FXML
    private TableView<AnnonceSitting> tableView;

    @FXML
    private TableColumn<AnnonceSitting,String> idCol;

    @FXML
    private TableColumn<AnnonceSitting, String> ageCol;

    @FXML
    private TableColumn<AnnonceSitting, String> couleurCol;

    @FXML
    private TableColumn<AnnonceSitting, String> sexCol;

    @FXML
    private TableColumn<AnnonceSitting, String> raceCol;

    @FXML
    private TableColumn<AnnonceSitting, String> msgCol;

    @FXML
    private TableColumn<AnnonceSitting, String> typeCol;

    @FXML
    private TableColumn<AnnonceSitting, String> dateSitCol;

    @FXML
    private TableColumn<AnnonceSitting, String> typeSitCol;
    
    @FXML
    private ChoiceBox<String> choixInsertion;
    
    @FXML
    private TableColumn<AnnonceSitting, String> dateCol;
    @FXML
    private ChoiceBox<String> choixModification;
@FXML
    void actionInsertion2(ActionEvent event) {
//         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
//                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(typeSitInsertion.getText()))&& (!"".equals(choixInsertion.getValue())))
//        {
            AnnonceSittingServices as = new AnnonceSittingServices();
              as.insererAnnonceSitting(new AnnonceSitting(java.sql.Date.valueOf(dateSitInsertion.getValue()), 
                      typeSitInsertion.getText(),
                      0 , 
                      Integer.parseInt(ageInsertion.getText()), 
                      couleurInsertion.getText(), 
                      sexInsertion.getText(), 
                      raceInsertion.getText(), 
                      msgInsertion.getText(),
                      choixInsertion.getValue(), 
                      null));
            dateSitInsertion.setValue(LocalDate.now());
            typeSitInsertion.setText("");
              
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
//                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))&& (!"".equals(dateSitModification.getValue()))&& (!"".equals(typeSitModification.getText())) && (!"".equals(typeModification.getText())))
//        {
            AnnonceSittingServices as = new AnnonceSittingServices();
            
            as.updateAnnonceSitting(new 
             AnnonceSitting(
                    
                    java.sql.Date.valueOf(dateSitModification.getValue()),
                    typeSitModification.getText(),
                    0,
                    Integer.parseInt(ageModification.getText()),
                    couleurModification.getText(),
                    sexModification.getText(),
                    raceModification.getText(),
                    msgModification.getText(),
                    choixModification.getValue(),
                    java.sql.Date.valueOf(dateSitModification.getValue())),Integer.parseInt(idModification.getText()));
                    
            
            
            
            
            idModification.setText("");
            ageModification.setText("");
         couleurModification.setText("");
         sexModification.setText("");
           raceModification.setText("");
           msgModification.setText("");
             choixModification.setValue("Chien");
             typeSitModification.setText("");
             
            loadTable();
                   
//        }

    }

    @FXML
    void actionSupression2(ActionEvent event) {

    if (!"".equals(idSupression.getText())){
            AnnonceSittingServices ps = new AnnonceSittingServices();
            ps.DeleteAnnonceSitting(Integer.parseInt(idSupression.getText()));
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
        choixModification.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixModification.setValue("Chien");
//        typeModification.setText("Chien");
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
        typeSitCol.setCellValueFactory(new PropertyValueFactory<>("typeSit"));
        dateSitCol.setCellValueFactory(new PropertyValueFactory<>("dateSit"));
        
        
	//id 	age 	couleur 	sex 	race 	message_complementaire 	type 	date 	dateSit 	typeSit 
    }

    private void loadTable() {
       AnnonceSittingServices rs = new AnnonceSittingServices();
        tableView.getItems().setAll(rs.getAll1());    
    }
}
