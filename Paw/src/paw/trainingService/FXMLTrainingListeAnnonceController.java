package paw.trainingService;

import Entity.AnnonceTraining;
import Service.AnnonceTrainingServices;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private ImageView avatar;
    @FXML
    private JFXButton ModifButton;
    @FXML
    private JFXButton SuppButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceTrainingServices service = new AnnonceTrainingServices();
        liste= new ArrayList<>();
        liste= service.getAnnonceTraining();
        if (liste.isEmpty()) {
            tableau.setVisible(false);
//            paginator2.setVisible(false);
            paginator.setVisible(false);
//            listevide.setVisible(true);
            paginator.setVisible(false);
        } else {
            tableau.setVisible(true);
            paginator.setVisible(true);
//            listevide.setVisible(false);
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
            
            
    }

    @FXML
    private void modifAnnonce(ActionEvent event) {
//        loadWindow("/Paw/src/paw/trainingService/FXMLModificationAnnonce.fxml","Modification");
    }

    @FXML
    private void suppAnnonce(ActionEvent event) {
    }
//    void loadWindow(String loc,String title){
//        try {
////
////             javafx.fxml.LoadException: Root hasn't been set. Use method setRoot() before load.           
////    
//           
//            Parent parent = FXMLLoader.load(getClass().getResource(loc));
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle(title);
//            stage.setScene(new Scene(parent));
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLModificationAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex);
//        }
//    }
}
