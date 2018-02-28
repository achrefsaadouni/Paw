/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.ayoubAdmin.statistiques;

import Service.AnnoncePerduServices;
import Service.ReclamationServices;
import Service.UtilisateurServices;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;


/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLstatistiquesController implements Initializable {

    private PieChart ReclamationsRemerciments;
    @FXML
    private PieChart TraiteeNonTraitee;
    @FXML
    private PieChart lostandfound;
    private BarChart<String,Integer> barC;
    @FXML
    private StackPane xtx;
    @FXML
    private StackPane recl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationServices recS = new ReclamationServices();
        AnnoncePerduServices as= new AnnoncePerduServices() ; 
//        ObservableList<PieChart.Data> D = FXCollections.observableArrayList(
//             new PieChart.Data("Remerciments", recS.getRemerciment()),
//             new PieChart.Data("Reclamations", recS.getReclamation())
//         );        
//        ReclamationsRemerciments.setData(D);
        
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
        //System.out.println("mochekla inisialize");
        //barC= new BarChart<>();
        try{
            ResultSet rs = recS.getNbrReclamationType();
            ArrayList<Integer> nombres = new ArrayList<>();
            ArrayList<String> objets= new ArrayList<>();
            HashMap<String,Integer> map = new HashMap();
            while (rs.next())
            {
                nombres.add(rs.getInt("nbr"));
                objets.add(rs.getString("objet"));
                map.put(rs.getString("objet"), rs.getInt("nbr"));
            }
            if(!map.isEmpty())
            {
                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setCategories(FXCollections.<String>observableArrayList(map.keySet()));
                NumberAxis yAxis = new NumberAxis(0, 
                                                  map.values().stream().max((a1,a2)-> a1-a2).get()+1 , 
                                                  1);
                ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList() ;
                
                map.forEach((t,u)-> {
                    BarChart.Series<String,Integer> b = new BarChart.Series<>(t,FXCollections.observableArrayList(
                           new BarChart.Data(t, u)     
                    ));
                    barChartData.add(b);
                    
                });           
                BarChart<String,Integer> m = new BarChart(xAxis, yAxis,barChartData);
//                barC.setData(new BarChart(xAxis, yAxis, barChartData).getData());
//                //barC.setData(new BarChart(xAxis, yAxis, barChartData, 25.0d).getData());
                recl.getChildren().add(new BarChart(xAxis, yAxis, barChartData,25.0d));
                xtx.getChildren().add(createBarChart());
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    
        
    }       
    public BarChart createBarChart(){
        
        ArrayList<String> y = new ArrayList();
        y.add("Confirmés");
        y.add("Non Confirmés");

        UtilisateurServices serv = new UtilisateurServices();
        ArrayList<Integer> nbr = new ArrayList();
        nbr.add(serv.nbrConfirmes());
        nbr.add(serv.nombreNonConfirmes());

        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(y));
        NumberAxis yAxis = new NumberAxis("Nombre d'Utilisateurs", 0, nbr.stream().max((a1,a2)-> a1-a2).get()+2, 2);
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
            new BarChart.Series("Utilisateurs", FXCollections.observableArrayList(
               new BarChart.Data(y.get(0), nbr.get(0)),
               new BarChart.Data(y.get(1), nbr.get(1))
            ))
        );
        
        return  new BarChart(xAxis, yAxis, barChartData, 25.0d);
    }
}
