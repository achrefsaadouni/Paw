package paw.trainingServiceAdmin;

import Entity.AnnonceTraining;
import Service.AnnonceTrainingServices;
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
import javafx.scene.layout.AnchorPane;

public class FXMLTrainingAdminController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private TableView<AnnonceTraining> AnnonceTable;
    @FXML
    private TableColumn<AnnonceTraining, String> age;
    @FXML
    private TableColumn<AnnonceTraining, String> couleur;
    @FXML
    private TableColumn<AnnonceTraining, String> sexe;
    @FXML
    private TableColumn<AnnonceTraining, String> typePet;
    @FXML
    private TableColumn<AnnonceTraining, String> race;
    @FXML
    private TableColumn<AnnonceTraining, String> message;
    @FXML
    private TableColumn<AnnonceTraining, String> date;
    @FXML
    private TableColumn<AnnonceTraining, String> typeTr;
    @FXML
    private TableColumn<AnnonceTraining, String> dateTr;
    @FXML
    private TableColumn<AnnonceTraining, String> supprimer;

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
        dateTr.setCellValueFactory(new PropertyValueFactory<>("dateTr"));
        typeTr.setCellValueFactory(new PropertyValueFactory<>("typeTr"));
        typePet.setCellValueFactory( new PropertyValueFactory<>("typePet"));
    }
    
    private void loadTable() {
            
           AnnonceTrainingServices rs = new AnnonceTrainingServices();
           AnnonceTable.getItems().setAll(rs.getAll1());
         //  rs.getAll1().forEach(e -> System.out.println(e.getLieu_trouve()));
           
           supprimer.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            AnnonceTraining a = (AnnonceTraining) param.getValue();
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
        AnnonceTrainingServices ps = new AnnonceTrainingServices();
            ps.DeleteAnnonceTraining(id);
    }
    
}
