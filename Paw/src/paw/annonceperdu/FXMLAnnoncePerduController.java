/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu;

//import Entity.AnnonceTrouvee;
//import Service.AnnonceServices;
import Entity.AnnoncePerdu;
import Service.AnnoncePerduServices;
import Service.AnnonceTrouveServices;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;

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
 * @author Guideinfo
 */
public class FXMLAnnoncePerduController implements Initializable {


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
    private ChoiceBox<String> choixInsertion;

    @FXML
    private JFXTextField lieuxInsertion3;

    @FXML
    private JFXTextField colierInsertion3;

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
    private ChoiceBox<String> choixModification;

    @FXML
    private JFXTextField colierModification;

    @FXML
    private JFXTextField lieuModification1;

    @FXML
    private JFXTextField idSupression;

    @FXML
    private TableView<AnnoncePerdu> tableView;

    @FXML
    private TableColumn<AnnoncePerdu,String> idCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> ageCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> couleurCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> sexCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> raceCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> msgCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> typeCol;

    @FXML
    private TableColumn<AnnoncePerdu,String> dateCol;


    @FXML
    private TableColumn<AnnoncePerdu,String> colierCol1;

    @FXML
    private TableColumn<AnnoncePerdu,String> dateTrouveeCol1;

    @FXML
    private TableColumn<AnnoncePerdu,String> lieuCol1;
    @FXML
    private JFXDatePicker dateinsertionp;

    @FXML
    void actionInsertion2(ActionEvent event) {
   if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue()))&& (!"".equals(colierInsertion3.getText()))&& (!"".equals(lieuxInsertion3.getText())))
        {
// String colier, Date date_trouvee, String lieu_perdu, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date           
              AnnoncePerduServices  as = new AnnoncePerduServices();
              //as.insererAnnoncePerdu(new AnnoncePerdu(colierInsertion3.getText(),java.sql.Date.valueOf(dateinsertionp.getValue()), lieuxInsertion3.getText(),0 , Integer.parseInt(ageInsertion.getText()), couleurInsertion.getText(), sexInsertion.getText(), raceInsertion.getText(), msgInsertion.getText(),choixInsertion.getValue(), null));
            
            dateinsertionp.setValue(LocalDate.now());
            colierInsertion3.setText("");
            lieuxInsertion3.setText("");
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
    void actionModification2(ActionEvent event) {

            if ((!"".equals(idModification.getText()))&&(!"".equals(couleurModification.getText()))&& (!"".equals(ageModification.getText()))&& (!"".equals(sexModification.getText()))
                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))&& (!"".equals(choixModification.getValue()))&& (!"".equals(colierModification.getText()))&& (!"".equals(lieuModification1.getText())))
        {
            AnnoncePerduServices as = new AnnoncePerduServices();
            
            //as.updateAnnoncePerdu(new AnnoncePerdu(colierModification.getText(), null, lieuModification1.getText(),0 , Integer.parseInt(ageModification.getText()), couleurModification.getText(), sexModification.getText(), raceModification.getText(), msgModification.getText(),choixModification.getValue(), null),Integer.parseInt(idModification.getText()));
            
           
            
            
            
            
            idModification.setText("");
            ageModification.setText("");
            couleurModification.setText("");
            sexModification.setText("");
            raceModification.setText("");
            msgModification.setText("");
            choixModification.setValue("Chien");
            colierModification.setText(""); 
            lieuModification1.setText("");
            loadTable();
                   
        }

    }

    @FXML
    void actionSupression2(ActionEvent event) {
    if (!"".equals(idSupression.getText())){
            AnnonceTrouveServices ps = new AnnonceTrouveServices();
            ps.DeleteAnnonceTrouvee(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
   
    }

    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
        choixModification.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixModification.setValue("Chien");
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
        colierCol1.setCellValueFactory(new PropertyValueFactory<>("colier"));
        dateTrouveeCol1.setCellValueFactory(new PropertyValueFactory<>("date_perte"));
        lieuCol1.setCellValueFactory(new PropertyValueFactory<>("lieu_perdu"));
    
    }

    private void loadTable() {
           AnnoncePerduServices rs = new AnnoncePerduServices();
           tableView.getItems().setAll(rs.getAll1());    
    }
    
}
