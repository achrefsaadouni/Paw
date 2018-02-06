/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncetrouvee;

//import Entity.Annonce;
import Entity.AnnonceTrouvee;
//import Service.AnnonceServices;
import Service.AnnonceTrouveServices;
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
 * @author Guideinfo
 */
public class FXMLAnnonceTrouveeController implements Initializable {


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
    private TableView<AnnonceTrouvee> tableView;

    @FXML
    private TableColumn<AnnonceTrouvee,String> idCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> ageCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> couleurCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> sexCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> raceCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> msgCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> typeCol;

    @FXML
    private TableColumn<AnnonceTrouvee,String> dateCol;


    @FXML
    private TableColumn<AnnonceTrouvee,String> colierCol1;

    @FXML
    private TableColumn<AnnonceTrouvee,String> dateTrouveeCol1;

    @FXML
    private TableColumn<AnnonceTrouvee,String> lieuCol1;

    @FXML
    void actionInsertion2(ActionEvent event) {
   if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue()))&& (!"".equals(colierInsertion3.getText()))&& (!"".equals(lieuxInsertion3.getText())))
        {
// String colier, Date date_trouvee, String lieu_perdu, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date           
              AnnonceTrouveServices as = new AnnonceTrouveServices();
            as.insererAnnonceTrouvee(new AnnonceTrouvee(colierInsertion3.getText(), null, lieuxInsertion3.getText(),0 , Integer.parseInt(ageInsertion.getText()), couleurInsertion.getText(), sexInsertion.getText(), raceInsertion.getText(), msgInsertion.getText(),choixInsertion.getValue(), null));
            
           
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
            AnnonceTrouveServices as = new AnnonceTrouveServices();
            
            as.updateAnnonceTrouvee(new AnnonceTrouvee(colierModification.getText(), null, lieuModification1.getText(),0 , Integer.parseInt(ageModification.getText()), couleurModification.getText(), sexModification.getText(), raceModification.getText(), msgModification.getText(),choixModification.getValue(), null),Integer.parseInt(idModification.getText()));
            
           
            
            
            
            
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
        dateTrouveeCol1.setCellValueFactory(new PropertyValueFactory<>("date_trouvee"));
        lieuCol1.setCellValueFactory(new PropertyValueFactory<>("lieu_perdu"));
    
    }

    private void loadTable() {
           AnnonceTrouveServices rs = new AnnonceTrouveServices();
           tableView.getItems().setAll(rs.getAll1());    
    }
}
