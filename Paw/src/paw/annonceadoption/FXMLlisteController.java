/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceadoption;

import Entity.AnnonceAdoption;
import Service.AnnonceAdoptionService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLlisteController implements Initializable {

    ArrayList<AnnonceAdoption> liste;
    
    @FXML
    private Label nom11;
    @FXML
    private AnchorPane box;
    @FXML
    private Label typeAdoptiontypeAnimal;
    @FXML
    private JFXButton consulter;
    @FXML
    private Label date;
    @FXML
    private Label race;
    @FXML
    private Label message;
    @FXML
    private Pagination paginator;
    @FXML
    private AnchorPane box1;
    @FXML
    private Label typeAdoptiontypeAnimal1;
    @FXML
    private JFXButton consulter1;
    @FXML
    private Label date1;
    @FXML
    private Label race1;
    @FXML
    private Label message1;
    @FXML
    private AnchorPane box11;
    @FXML
    private Label typeAdoptiontypeAnimal11;
    @FXML
    private JFXButton consulter11;
    @FXML
    private Label date11;
    @FXML
    private Label race11;
    @FXML
    private Label message11;
    @FXML
    private AnchorPane window;
    @FXML
    private StackPane vide;
    @FXML
    private AnchorPane details;
    @FXML
    private Label titrer;
    @FXML
    private Label textr;
    @FXML
    private Label dater;
    @FXML
    private Label utilisateurr;
    @FXML
    private JFXTextArea reponser;
    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private Label reptext;
    @FXML
    private Separator separator;
    @FXML
    private Label datereponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceAdoptionService service= new AnnonceAdoptionService();
        details.setVisible(false);
        liste= new ArrayList<>();
        liste = service.getAnnonceAdoptionDisponible();
        if (liste.isEmpty()) {
            box.setVisible(false);
            box1.setVisible(false);
            box11.setVisible(false);
            vide.setVisible(true);
            paginator.setVisible(false);
        } else {
            paginator.setVisible(true);
            vide.setVisible(false);
            setNbPages();
            initAnnoncePage(0);
        }       
    }    

    @FXML
    private void consulterOffre(ActionEvent event) {
    }

    private void setNbPages() {

       if (liste.size() % 3 != 0) {
            paginator.setPageCount((liste.size() / 3) + 1);
        } else {
            paginator.setPageCount(liste.size() / 3);
        }
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });
    }

    private void initAnnoncePage(int i) {
        paginator.setCurrentPageIndex(i);
        List<AnnonceAdoption> TroisAnnonces = getAnnoncesPage(i);     
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            typeAdoptiontypeAnimal.setText("Adoption "+TroisAnnonces.get(0).getTypeAdoption()+" : "+TroisAnnonces.get(0).getType());
            message.setText(TroisAnnonces.get(0).getMessage_complementaire());
            date.setText(String.valueOf(TroisAnnonces.get(0).getDate()).substring(0, 16));
            race.setText(TroisAnnonces.get(0).getRace());
            box.setOnMouseClicked((MouseEvent e) -> {
                details.setVisible(true);
            });
            
        } 
        else { 
            box.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (TroisAnnonces.size() >= 2){
            box1.setVisible(true);  

            typeAdoptiontypeAnimal1.setText("Adoption "+TroisAnnonces.get(1).getTypeAdoption()+" : "+TroisAnnonces.get(1).getType());
            message1.setText(TroisAnnonces.get(1).getMessage_complementaire());
            date1.setText(String.valueOf(TroisAnnonces.get(1).getDate()).substring(0, 16));
            race1.setText(TroisAnnonces.get(1).getRace());
        } 
        else{
            box1.setVisible(false);
        }
        ///////////////////////////////////////////////////////////
        
        if (TroisAnnonces.size() >= 3){
            box11.setVisible(true);         

            typeAdoptiontypeAnimal11.setText("Adoption "+TroisAnnonces.get(2).getTypeAdoption()+" : "+TroisAnnonces.get(2).getType());
            message11.setText(TroisAnnonces.get(2).getMessage_complementaire());
            date11.setText(String.valueOf(TroisAnnonces.get(2).getDate()).substring(0, 16));
            race11.setText(TroisAnnonces.get(2).getRace());
        } 
        else{
            box11.setVisible(false); 
        }
    }

    private List<AnnonceAdoption> getAnnoncesPage(int i) {
        int start = 3 * i;
        int fin = start + 3;
        if (liste.size() > start) {
            if (liste.size() > fin) {
                return liste.subList(start, fin);
            } else {
                return liste.subList(start, liste.size());
            }
        }
        return liste.subList(0, 2);    
    }

    @FXML
    private void enregistrer(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
        details.setVisible(false);
    }
    
}
