/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceadoption;

import Entity.AnnonceAdoption;
import Entity.RepOffreAdoption;
import Service.AnnonceAdoptionService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLmesoffresadoptionController implements Initializable {

    
    ArrayList<AnnonceAdoption> liste ;
    @FXML
    private Pane infos;
    @FXML
    private GridPane tableau;
    @FXML
    private Label date1;
    @FXML
    private Label date2;
    @FXML
    private Label type;
    @FXML
    private Label race;
    @FXML
    private Label sexe;
    @FXML
    private Label age;
    @FXML
    private Label couleur;
    @FXML
    private Label msg;
    @FXML
    private Label datedeb;
    @FXML
    private Label datefin;
    @FXML
    private Pagination paginator;
    @FXML
    private Pagination paginator2;
    @FXML
    private StackPane listevide;
    @FXML
    private StackPane repvide;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceAdoptionService service= new AnnonceAdoptionService();
        tableau.setVisible(false);
        liste= new ArrayList<>();
        liste = service.getAnnonceAdoptionDisponible();
        if (liste.isEmpty()) {
            tableau.setVisible(false);
            paginator2.setVisible(false);
            paginator.setVisible(false);
            listevide.setVisible(true);
            paginator.setVisible(false);
        } else {
            tableau.setVisible(true);
            paginator.setVisible(true);
            listevide.setVisible(false);
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
            AnnonceAdoptionService servrep = new AnnonceAdoptionService();
            ArrayList<RepOffreAdoption> reponses= servrep.getRep(liste.get(i).getId());
            race.setText(liste.get(i).getRace());
            type.setText(liste.get(i).getType());
            sexe.setText(liste.get(i).getSex());
            age.setText(String.valueOf(liste.get(i).getAge()));
            msg.setText(liste.get(i).getMessage_complementaire());
            couleur.setText(liste.get(i).getCouleur());
            if(liste.get(i).getTypeAdoption().equals("Permanante"))
            {
                datedeb.setVisible(false);
                date1.setVisible(false);
                date2.setVisible(false);
                datefin.setVisible(false);
            }
            else
            {
                datedeb.setVisible(true);
                date1.setVisible(true);
                date2.setVisible(true);
                datefin.setVisible(true);
                datedeb.setText(liste.get(i).getDebutAdoption().toString());
                datefin.setText(liste.get(i).getFinAdoption().toString());
            }
            
        }         
    
}
