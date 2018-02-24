/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceadoption;

import Entity.AnnonceAdoption;
import Entity.Reclamation;
import Entity.RepOffreAdoption;
import Entity.Utilisateur;
import Service.AnnonceAdoptionService;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    ArrayList<RepOffreAdoption> reponses ;
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
    @FXML
    private Pane rep;
    @FXML
    private Label daterep;
    @FXML
    private Label nomrep;
    @FXML
    private Label emailrep;
    @FXML
    private Label numerorep;
    @FXML
    private JFXButton confirmerrep;
    @FXML
    private Pane rep1;
    @FXML
    private Label daterep1;
    @FXML
    private Label nomrep1;
    @FXML
    private Label emailrep1;
    @FXML
    private Label numerorep1;
    @FXML
    private JFXButton confirmerrep1;
    @FXML
    private Pane rep2;
    @FXML
    private Label daterep2;
    @FXML
    private Label nomrep2;
    @FXML
    private Label emailrep2;
    @FXML
    private Label numerorep2;
    @FXML
    private JFXButton confirmerrep2;
    @FXML
    private ImageView image;
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
            
            AnnonceAdoptionService servrep = new AnnonceAdoptionService();
            reponses= servrep.getRep(liste.get(newIndex.intValue()).getId());
            initAnnoncePage(newIndex.intValue());
            setNbrep();
            if(reponses.isEmpty())
            {
                
            }
            else
            {
                initReponse(0);
            }
            
        });
    }
    private void setNbrep() {
        if (reponses.size() % 3 != 0) {
            paginator2.setPageCount((reponses.size() / 3) + 1);
        } else {
            paginator2.setPageCount(reponses.size() / 3);
        }
        
        paginator2.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initReponse(newIndex.intValue());
        });
    }

    private void initAnnoncePage(int i) {
            paginator.setCurrentPageIndex(i);
            
            AnnonceAdoptionService servrep = new AnnonceAdoptionService();
            reponses= servrep.getRep(liste.get(i).getId());
            setNbrep();
            if(reponses.isEmpty())
            {
                repvide.setVisible(true);
                paginator2.setVisible(false);
                rep.setVisible(false);
                rep1.setVisible(false);
                rep2.setVisible(false);
            }
            else
            {
                paginator2.setCurrentPageIndex(0);
                initReponse(0);
                
                repvide.setVisible(false);
                paginator2.setVisible(true);
                rep.setVisible(true);
                rep1.setVisible(true);
                rep2.setVisible(true);
            }
            race.setText(liste.get(i).getRace());
            type.setText(liste.get(i).getType());
            sexe.setText(liste.get(i).getSex());
            age.setText(String.valueOf(liste.get(i).getAge()));
            msg.setText(liste.get(i).getMessage_complementaire());
            couleur.setText(liste.get(i).getCouleur());
            image.setImage(new Image("http://localhost/pawAnnonces/"+liste.get(i).getImages()));
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

    private void initReponse(int i) {
        paginator2.setCurrentPageIndex(i);
        
        List<RepOffreAdoption> TroisRep = getRepPage(i);  
        UtilisateurServices s = new UtilisateurServices();
        if (TroisRep.size() >= 1) {
            Utilisateur u = s.rechercher(TroisRep.get(0).getId_utilisateur());
            rep.setVisible(true);
            daterep.setText(String.valueOf(TroisRep.get(0).getDate()).substring(0, 16));
            nomrep.setText(u.getEsm());
            emailrep.setText(u.getEmail());
            numerorep.setText(String.valueOf(u.getNumero()));

        } 
        else { 
            rep.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (TroisRep.size() >= 2){
            Utilisateur u1 = s.rechercher(TroisRep.get(1).getId_utilisateur());
            rep1.setVisible(true);
            daterep1.setText(String.valueOf(TroisRep.get(1).getDate()).substring(0, 16));
            nomrep1.setText(u1.getEsm());
            emailrep1.setText(u1.getEmail());
            numerorep1.setText(String.valueOf(u1.getNumero()));

        } 
        else { 
            rep1.setVisible(false);
        }
        ///////////////////////////////////////////////////////////
        
        if (TroisRep.size() >= 3){
            Utilisateur u2 = s.rechercher(TroisRep.get(2).getId_utilisateur());
            rep2.setVisible(true);
            daterep2.setText(String.valueOf(TroisRep.get(2).getDate()).substring(0, 16));
            nomrep2.setText(u2.getEsm());
            emailrep2.setText(u2.getEmail());
            numerorep2.setText(String.valueOf(u2.getNumero()));

        } 
        else { 
            rep2.setVisible(false);
        }
              
    }

    private List<RepOffreAdoption> getRepPage(int i) {
        int start = 3 * i;
        int fin = start + 3;
        if (reponses.size() > start) {
            if (reponses.size() > fin) {
                return reponses.subList(start, fin);
            } else {
                return reponses.subList(start, reponses.size());
            }
        }
        return reponses.subList(0, 2);
    }

    @FXML
    private void login(ActionEvent event) {
    }
    
}
