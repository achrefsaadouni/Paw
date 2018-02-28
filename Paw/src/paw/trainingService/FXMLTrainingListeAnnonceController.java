package paw.trainingService;

import Entity.AnnonceTraining;
import Service.AnnonceTrainingServices;
import Service.TypeTrainingServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static paw.Paw.session;

public class FXMLTrainingListeAnnonceController implements Initializable {

    @FXML
    private Label animal;
    @FXML
    private Label race;
    @FXML
    private Label sexe;
    @FXML
    private Label age;
    @FXML
    private Label couleur;
    @FXML
    private Label nom;
    @FXML
    private Label dateTr;
    @FXML
    private Label typeTr;
    @FXML
    private Label desc;
    @FXML
    private Pagination paginator;
    @FXML
    private Pane annonce;
    @FXML
    private GridPane tableau;
    
    ArrayList<AnnonceTraining> liste ;
    @FXML
    private JFXButton ModifButton;
    @FXML
    private JFXButton SuppButton;
    @FXML
    private StackPane stackModif;
    @FXML
    private GridPane tableau1;
    @FXML
    private JFXTextField typePetM;
    @FXML
    private JFXTextField raceM;
    @FXML
    private JFXTextField sexeM;
    @FXML
    private JFXTextField ageM;
    @FXML
    private JFXTextField couleurM;
    @FXML
    private JFXTextField nomM;
    @FXML
    private JFXDatePicker dateTrM;
    @FXML
    private JFXComboBox<String> typeTrM;
    @FXML
    private JFXTextField descM;
    @FXML
    private JFXButton confirmModif;
    
    
    String type = "Annonce Training";
    
    @FXML
    private JFXButton quitModif;
    @FXML
    private StackPane aucunAnnonce;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeTrainingServices ss = new TypeTrainingServices();
        typeTrM.setItems(ss.getString());
        stackModif.setVisible(false);
        AnnonceTrainingServices service = new AnnonceTrainingServices();
        liste= new ArrayList<>();
        liste= service.getAnnonceTraining(session.getId());
        if (liste.isEmpty()) {
            tableau.setVisible(false);
            paginator.setVisible(false);
            aucunAnnonce.setVisible(true);
        } else {
            aucunAnnonce.setVisible(false);
            tableau.setVisible(true);
            paginator.setVisible(true);
            setNbPages();
            initAnnoncePage(0);
        }
        
        
        
    }    
    private void setNbPages() {
        paginator.setPageCount(liste.size());
        
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });
    }

    private void initAnnoncePage(int i) {
            paginator.setCurrentPageIndex(i);     
            animal.setText(liste.get(i).getTypePet());
            race.setText(liste.get(i).getRace());
            sexe.setText(liste.get(i).getSex());
            age.setText(String.valueOf(liste.get(i).getAge()));
            couleur.setText(liste.get(i).getCouleur());
            nom.setText(liste.get(i).getNomPet());
            dateTr.setText(liste.get(i).getDateTr().toString());
            typeTr.setText(liste.get(i).getTypeTr());
            desc.setText(liste.get(i).getMessage_complementaire());
            ModifButton.setOnAction((event) -> {
                    
                   modifierAnnoce(liste.get(i).getId(),i);
                    
                });
            SuppButton.setOnAction((event) -> {
                    
                   suppAnnonce(liste.get(i).getId(),i);
                    
                });
            
            
    }

    public void modifierAnnoce(int id,int i)
    {
//              initilisation
        typePetM.setText(liste.get(i).getTypePet());
        raceM.setText(liste.get(i).getRace());
        sexeM.setText(liste.get(i).getSex());
        ageM.setText(String.valueOf(liste.get(i).getAge()));
        couleurM.setText(liste.get(i).getCouleur());
        nomM.setText(liste.get(i).getNomPet());
        typeTrM.setValue(liste.get(i).getTypeTr());
        descM.setText(liste.get(i).getMessage_complementaire());
        if ((typeTrM.getValue().isEmpty())
                 || (couleurM.getText().trim().equals(""))|| (ageM.getText().trim().equals(""))||(!isInteger(ageM))
                || (raceM.getText().trim().equals(""))|| (descM.getText().trim().equals(""))|| (typePetM.getText().trim().equals("")))
                 {
                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
                    fail.setHeaderText("erreur");
                    fail.setContentText("Vous devez remplir touts les champs");
                    fail.showAndWait();
                 }else{
        stackModif.setVisible(true);
        confirmModif.setOnAction((event) -> {
                    
                   confirmModif(id);
                   AnnonceTrainingServices service = new AnnonceTrainingServices();
                   liste = service.getAnnonceTraining(session.getId());
                   initAnnoncePage(i);
                    
                });
        initAnnoncePage(i);
        }
        
    }
    
    private void confirmModif(int id) {
         if ((typeTrM.getValue()==null) 
                 || (couleurM.getText().trim().equals(""))|| (ageM.getText().trim().equals(""))||(!isInteger(ageM))
                || (raceM.getText().trim().equals(""))|| (descM.getText().trim().equals(""))|| (typePetM.getText().trim().equals("")))
                 {
                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
                    fail.setHeaderText("erreur");
                    fail.setContentText("Vous devez remplir touts les champs");
                    fail.showAndWait();
                 }else{
        AnnonceTrainingServices as = new AnnonceTrainingServices();
            
            as.updateAnnonceTraining(new 
             AnnonceTraining(
                    
                    java.sql.Date.valueOf(dateTrM.getValue()), 
                    (String) typeTrM.getValue(),
                    typePetM.getText(),
                    nomM.getText(),
                    0,
                    Integer.parseInt(ageM.getText()),
                    couleurM.getText(),
                    sexeM.getText(),
                    raceM.getText(),
                    descM.getText(),
                    type,
                    java.sql.Date.valueOf(LocalDate.now()), 
                    session.getId()),id);
        stackModif.setVisible(false);
         }
    }

    @FXML
    private void quitterModif(ActionEvent event) {
        stackModif.setVisible(false);
    }
    
    private void suppAnnonce(int id,int i) {
            AnnonceTrainingServices ps = new AnnonceTrainingServices();
            ps.DeleteAnnonceTraining(id);
            AnnonceTrainingServices service = new AnnonceTrainingServices();    
            liste = service.getAnnonceTraining(session.getId()); 
            setNbPages();
            initAnnoncePage(1); 
    }

    private boolean isInteger(JFXTextField input) {
        try {
            int age = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }


    

    
}
