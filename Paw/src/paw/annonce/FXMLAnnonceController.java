/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonce;
import Entity.Annonce;

import Service.AnnonceServices;
import Service.PersonneServices;
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
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLAnnonceController implements Initializable {


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
    private JFXTextField idSupression;
 
    @FXML
    private TableView<Annonce> tableView;

    @FXML
    private TableColumn<Annonce,String> idCol;

    @FXML
    private TableColumn<Annonce, String> ageCol;

    @FXML
    private TableColumn<Annonce, String> couleurCol;

    @FXML
    private TableColumn<Annonce, String> sexCol;

    @FXML
    private TableColumn<Annonce, String> raceCol;

    @FXML
    private TableColumn<Annonce, String> msgCol;

    @FXML
    private TableColumn<Annonce, String> typeCol;
    
    @FXML
    private ChoiceBox<String> choixInsertion;
    @FXML
    private ChoiceBox<String> choixModification;
@FXML
    void actionInsertion2(ActionEvent event) {
         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue())))
        {
            AnnonceServices as = new AnnonceServices();
            as.insererAnnonce(new Annonce(0,Integer.parseInt(ageInsertion.getText()),couleurInsertion.getText(),sexInsertion.getText(),
            raceInsertion.getText(),msgInsertion.getText(),choixInsertion.getValue()));
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
                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))&& (!"".equals(choixModification.getValue())))
        {
            AnnonceServices as = new AnnonceServices();
            
            as.updateAnnonce(new 
             Annonce(Integer.parseInt(idModification.getText()),
                    Integer.parseInt(ageModification.getText()),
                    couleurModification.getText(),
                    sexModification.getText(),
                    raceModification.getText(),
                    msgModification.getText(),
                    choixModification.getValue()),
                    Integer.parseInt(idModification.getText()));
           
            
            
            
            
            idModification.setText("");
            ageModification.setText("");
         couleurModification.setText("");
         sexModification.setText("");
           raceModification.setText("");
           msgModification.setText("");
             choixModification.setValue("Chien");
            loadTable();
                   
        }

    }

    @FXML
    void actionSupression2(ActionEvent event) {

    if (!"".equals(idSupression.getText())){
            AnnonceServices ps = new AnnonceServices();
            ps.DeleteAnnonce(Integer.parseInt(idSupression.getText()));
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
    }    
    

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        raceCol.setCellValueFactory(new PropertyValueFactory<>("race"));
        msgCol.setCellValueFactory(new PropertyValueFactory<>("message_complementaire"));
        couleurCol.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    private void loadTable() {
       AnnonceServices rs = new AnnonceServices();
        tableView.getItems().setAll(rs.getAll());    
    }
}
