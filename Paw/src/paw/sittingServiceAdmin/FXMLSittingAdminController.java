package paw.sittingServiceAdmin;

import Entity.AnnonceSitting;
import Service.AnnonceSittingServices;
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


public class FXMLSittingAdminController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private TableView<AnnonceSitting> AnnonceTable;
    @FXML
    private TableColumn<AnnonceSitting, String> age;
    @FXML
    private TableColumn<AnnonceSitting, String> couleur;
    @FXML
    private TableColumn<AnnonceSitting, String> sexe;
    @FXML
    private TableColumn<AnnonceSitting, String> typePet;
    @FXML
    private TableColumn<AnnonceSitting, String> race;
    @FXML
    private TableColumn<AnnonceSitting, String> message;
    @FXML
    private TableColumn<AnnonceSitting, String> date;
    @FXML
    private TableColumn<AnnonceSitting, String> toDoList;
    @FXML
    private TableColumn<AnnonceSitting, String> supprimer;
    @FXML
    private TableColumn<AnnonceSitting, String> dateSit;
    @FXML
    private TableColumn<AnnonceSitting, String> dureSit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();
    }    

    private void initCol() {
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sex"));
        typePet.setCellValueFactory( new PropertyValueFactory<>("typePet"));
        race.setCellValueFactory(new PropertyValueFactory<>("race"));
        message.setCellValueFactory(new PropertyValueFactory<>("message_complementaire"));
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur")); 
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateSit.setCellValueFactory(new PropertyValueFactory<>("dateSit"));
        dureSit.setCellValueFactory(new PropertyValueFactory<>("dureSit"));
        toDoList.setCellValueFactory(new PropertyValueFactory<>("toDoList"));
    }

    private void loadTable() {
        AnnonceSittingServices rs = new AnnonceSittingServices();
           AnnonceTable.getItems().setAll(rs.getAll1());
         //  rs.getAll1().forEach(e -> System.out.println(e.getLieu_trouve()));
           
           supprimer.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            AnnonceSitting a = (AnnonceSitting) param.getValue();
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
        AnnonceSittingServices ps = new AnnonceSittingServices();
            ps.DeleteAnnonceSitting(id);
    }
    
}
