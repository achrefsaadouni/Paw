/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.ayoubAdmin.statistiques;

import Service.AnnoncePerduServices;
import Service.ReclamationServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;


/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLstatistiquesController implements Initializable {

    @FXML
    private PieChart ReclamationsRemerciments;
    @FXML
    private PieChart TraiteeNonTraitee;
    @FXML
    private PieChart lostandfound;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationServices recS = new ReclamationServices();
        AnnoncePerduServices as= new AnnoncePerduServices() ; 
        ObservableList<PieChart.Data> D = FXCollections.observableArrayList(
             new PieChart.Data("Remerciments", recS.getRemerciment()),
             new PieChart.Data("Reclamations", recS.getReclamation())
         );        
        ReclamationsRemerciments.setData(D);
        
        ObservableList<PieChart.Data> a= FXCollections.observableArrayList(
             new PieChart.Data("Annonce Perdu", as.getAnnoncePe()),
             new PieChart.Data("Annonce Trouvee", as.getAnnonceTrou())
         );        
       lostandfound.setData(a);
       
       
       
       
       
       
       
       
       ObservableList<PieChart.Data> T = FXCollections.observableArrayList(
             new PieChart.Data("Traitée", recS.getTraitee()),
             new PieChart.Data("Non Traitée", recS.getNonTraitee())
         );        
        TraiteeNonTraitee.setData(T);
        
        
        


    }
}
