/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.reclamation;

import Entity.Reclamation;
import Service.ReclamationServices;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import static paw.Paw.session;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLReclamationController implements Initializable {

    @FXML
    private AnchorPane reclamations;
    @FXML
    private Label nom1;
    @FXML
    private Label nom11;
    @FXML
    private JFXTextArea msg;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXToggleButton type;
    @FXML
    private Pagination paginator;

    
    
    
    ArrayList<Reclamation> mesReclamations ;
    @FXML
    private AnchorPane box1;
    @FXML
    private Label titre1;
    @FXML
    private Label text1;
    @FXML
    private Label date1;
    @FXML
    private Label titre2;
    @FXML
    private Label text2;
    @FXML
    private Label date2;
    @FXML
    private Label titre3;
    @FXML
    private Label text3;
    @FXML
    private Label date3;
    @FXML
    private AnchorPane box2;
    @FXML
    private AnchorPane box3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationServices service= new ReclamationServices();
        mesReclamations = service.getReclamationUtilisateur(session.getId());
        if (mesReclamations.isEmpty()) {
//            box1.setVisible(false);
//            box2.setVisible(false);
//            box3.setVisible(false);
        } else {
            setNbPages();
            initReclamationPage(0);
        }       
    }    

    @FXML
    private void envoyerReclamation(ActionEvent event) {
        ReclamationServices service= new ReclamationServices();
        String t = "Reclamation";
        if (type.isSelected()){
            t="Remerciment";
        }
        service.insererReclamation(new Reclamation(session.getId(), titre.getText(), msg.getText(), t));
                                    //////////////////////////////////
                                    ///affichage d'une notification///
                                    //////////////////////////////////
        Notifications.create()
              .title("Reclamation envoyée")
              .text("Merci pour votre collaboration !")
              .showInformation();
                                    
        type.setSelected(false);
        titre.setText("");
        msg.setText("");
        mesReclamations = service.getReclamationUtilisateur(session.getId());
        setNbPages();
        initReclamationPage(paginator.getCurrentPageIndex());
    }

    private void setNbPages() {
       if (mesReclamations.size() % 3 != 0) {
            paginator.setPageCount((mesReclamations.size() / 3) + 1);
        } else {
            paginator.setPageCount(mesReclamations.size() / 3);
        }
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initReclamationPage(newIndex.intValue());
        });
    }
    
    public List<Reclamation> getReclamationsPage(int index) {
        int start = 3 * index;
        int fin = start + 3;
        if (mesReclamations.size() > start) {
            if (mesReclamations.size() > fin) {
                return mesReclamations.subList(start, fin);
            } else {
                return mesReclamations.subList(start, mesReclamations.size());
            }
        }
        return mesReclamations.subList(0, 2);
    } 

    private void initReclamationPage(int index) {
        paginator.setCurrentPageIndex(index);
        List<Reclamation> TroisReclamations = getReclamationsPage(index);       
        if (TroisReclamations.size() >= 1) {
            box1.setVisible(true);
            if("Remerciment".equals(TroisReclamations.get(0).getType()))
            {
                box1.setStyle("-fx-background-color: #00A65A; -fx-border-radius: 4; -fx-background-radius: 4; -fx-border-color: #008448; -fx-border-width: 0 0 0 4;");
            }
            else
            {
                box1.setStyle("-fx-background-color: #DD4B39; -fx-border-radius: 4; -fx-background-radius: 4; -fx-border-color: #B03C2D; -fx-border-width: 0 0 0 4;");
            }
            titre1.setText(TroisReclamations.get(0).getObjet());
            text1.setText(TroisReclamations.get(0).getText());
            date1.setText(String.valueOf(TroisReclamations.get(0).getDate()).substring(0, 16));
            //heure1.setText(String.valueOf(TroisReclamations.get(0).getDate()).substring(12, 16));
//            consulter1.setOnAction((event) -> {
//                consulter(TroisReclamations.get(0).getList());
//            });
//            
//            nbrArticlebox1.setText("NOMBRE ARTICLE : "+String.valueOf(TroisReclamations.get(0).getList().size()));
//            etatBox1.setText(TroisReclamations.get(0).getEtat());
//            dateBox1.setText(TroisReclamations.get(0).getDate_achat().toString());
//            prixBox1.setText("PRIX TOTAL : "+String.valueOf(TroisReclamations.get(0).getPrix()) + " DT");
            
        } 
        else { 
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (TroisReclamations.size() >= 2){
            box2.setVisible(true);  
            if("Remerciment".equals(TroisReclamations.get(1).getType()))
            {
                box2.setStyle("-fx-background-color: #00A65A; -fx-border-radius: 4; -fx-background-radius: 4; -fx-border-color: #008448; -fx-border-width: 0 0 0 4;");
            }
            else
            {
                box2.setStyle("-fx-background-color: #DD4B39; -fx-border-radius: 4; -fx-background-radius: 4; -fx-border-color: #B03C2D; -fx-border-width: 0 0 0 4;");
            }
            titre2.setText(TroisReclamations.get(1).getObjet());
            text2.setText(TroisReclamations.get(1).getText());
            date2.setText(String.valueOf(TroisReclamations.get(1).getDate()).substring(0, 16));
        } 
        else{
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////
        
        if (TroisReclamations.size() >= 3){
            box3.setVisible(true);         
            if("Remerciment".equals(TroisReclamations.get(2).getType()))
            {
                box3.setStyle("-fx-background-color: #00A65A; -fx-border-radius: 4; -fx-background-radius: 4; -fx-border-color: #008448; -fx-border-width: 0 0 0 4;");
            }
            else
            {
                box3.setStyle("-fx-background-color: #DD4B39; -fx-border-radius: 4; -fx-background-radius: 4; -fx-border-color: #B03C2D; -fx-border-width: 0 0 0 4;");
            }
            titre3.setText(TroisReclamations.get(2).getObjet());
            text3.setText(TroisReclamations.get(2).getText());
            date3.setText(String.valueOf(TroisReclamations.get(2).getDate()).substring(0, 16));
        } 
        else{
            box3.setVisible(false); 
        }

    }
    
}
