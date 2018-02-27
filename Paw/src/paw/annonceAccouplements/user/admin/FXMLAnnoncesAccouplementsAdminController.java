/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceAccouplements.user.admin;

import Entity.AnnonceAccouplement;
import Service.AnnonceAccouplementServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLAnnoncesAccouplementsAdminController implements Initializable {

    @FXML
    private TableView<AnnonceAccouplement> AnnonceTable;
    @FXML
    private TableColumn<AnnonceAccouplement, String> animal;
    @FXML
    private TableColumn<AnnonceAccouplement, String> race;
    @FXML
    private TableColumn<AnnonceAccouplement, String> sexe;
    @FXML
    private TableColumn<AnnonceAccouplement, String> age;
    @FXML
    private TableColumn<AnnonceAccouplement, String> couleur;
    @FXML
    private TableColumn<AnnonceAccouplement, String> poil;
    @FXML
    private TableColumn<AnnonceAccouplement, String> lieu;
    @FXML
    private TableColumn<AnnonceAccouplement, String> date;
    @FXML
    private TableColumn<AnnonceAccouplement, String> message;
    @FXML
    private TableColumn<AnnonceAccouplement, String> vaccin;
    @FXML
    private TableColumn<AnnonceAccouplement, String> dossier;
    @FXML
    private TableColumn<AnnonceAccouplement, String> supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        initCol();
        loadTable();
    }    
    
    
    private void initCol() {
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sex"));
        race.setCellValueFactory(new PropertyValueFactory<>("race"));
        message.setCellValueFactory(new PropertyValueFactory<>("message_complementaire"));
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        animal.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        poil.setCellValueFactory(new PropertyValueFactory<>("type_poil"));
        vaccin.setCellValueFactory(new PropertyValueFactory<>("vaccin"));
        dossier.setCellValueFactory(new PropertyValueFactory<>("dossier"));
        lieu.setCellValueFactory( new PropertyValueFactory<>("lieu"));
    }

    private void loadTable() {
            
           AnnonceAccouplementServices rs = new AnnonceAccouplementServices();
           AnnonceTable.getItems().setAll(rs.getList());
           rs.getList().forEach(e -> System.out.println(e.getLieu()));
           
           supprimer.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            AnnonceAccouplement a = (AnnonceAccouplement) param.getValue();
            JFXButton supprimer = new JFXButton("Supprimer");
            supprimer.setStyle("-fx-background-color: #FE2E64;-fx-text-fill: white;");

            supprimer.setOnAction((ActionEvent ea) -> {
                supprimerAnnonce(a.getId());
                loadTable();
            });

            property.set(supprimer);
            return property;
        });
    }

    private void supprimerAnnonce(int id) {

            AnnonceAccouplementServices ps = new AnnonceAccouplementServices();
            ps.DeleteAnnonceAccouplement(id);
            

    }

    
}
