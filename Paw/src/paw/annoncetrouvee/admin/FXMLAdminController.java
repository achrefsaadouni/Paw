/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annoncetrouvee.admin;

import Entity.AnnoncePerdu;
import Entity.AnnonceTrouvee;
import Service.AnnonceTrouveServices;
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
 * @author achref
 */
public class FXMLAdminController implements Initializable {

    @FXML
    private TableView<AnnonceTrouvee> AnnonceTable;
    @FXML
    private TableColumn<AnnonceTrouvee, String> race;
    @FXML
    private TableColumn<AnnonceTrouvee, String> sexe;
    @FXML
    private TableColumn<AnnonceTrouvee, String> age;
    @FXML
    private TableColumn<AnnonceTrouvee, String> couleur;
    @FXML
    private TableColumn<AnnonceTrouvee, String> date;
    @FXML
    private TableColumn<AnnonceTrouvee, String> lieu;
    @FXML
    private TableColumn<AnnonceTrouvee, String> message;
    @FXML
    private TableColumn<AnnonceTrouvee, String> datetrouve;
    @FXML
    private TableColumn<AnnonceTrouvee, String> colier;
    @FXML
    private TableColumn<AnnonceTrouvee, String> supprimer;

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
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        datetrouve.setCellValueFactory(new PropertyValueFactory<>("date_trouvee"));
        colier.setCellValueFactory(new PropertyValueFactory<>("colier"));
        lieu.setCellValueFactory( new PropertyValueFactory<>("lieu"));
    }

    private void loadTable() {
            
           AnnonceTrouveServices rs = new AnnonceTrouveServices();
           AnnonceTable.getItems().setAll(rs.getList());
           rs.getList().forEach(e -> System.out.println(e.getLieu_trouve()));
           
           supprimer.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            AnnonceTrouvee a = (AnnonceTrouvee) param.getValue();
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

            AnnonceTrouveServices ps = new AnnonceTrouveServices();
            ps.DeleteAnnonceTrouvee(id);
            

    }

    
    
    
    
    
    
    
}
