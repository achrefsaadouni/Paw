/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu.user;

import Entity.AnnoncePerdu;
import Entity.LigneAchat;
import Entity.Utilisateur;
import Entity.Vets;
import Service.AnnoncePerduServices;
import Service.UtilisateurServices;
import Service.AnnoncePerduServices;
import Service.LigneAchatService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLMesAnnonceController implements Initializable {

    @FXML
    private Label nom1;
    @FXML
    private Label tel1;
    @FXML
    private Label adr1;
    @FXML
    private ImageView imageanimal1;
    @FXML
    private Label mail1;
    @FXML
    private Label datedepo;
    @FXML
    private Label nom3;
    @FXML
    private Label tel3;
    @FXML
    private Label adr3;
    @FXML
    private ImageView imageanimal3;
    @FXML
    private Label mail3;
    @FXML
    private Label datedepo3;
    @FXML
    private Label nom2;
    @FXML
    private Label tel2;
    @FXML
    private Label adr2;
    @FXML
    private ImageView imageanimal2;
    @FXML
    private Label mail2;
    @FXML
    private Label datedepo2;
    @FXML
    private Label nom4;
    @FXML
    private Label tel4;
    @FXML
    private Label adr4;
    @FXML
    private ImageView imageanimal4;
    @FXML
    private Label mail4;
    @FXML
    private Label datedepo4;
    @FXML
    private Pagination paginator;
    @FXML
    private Label type1;
    @FXML
    private Label type3;
    @FXML
    private Label type2;
    @FXML
    private Label type4;
    @FXML
    private AnchorPane box1;
    @FXML
    private AnchorPane box3;
    @FXML
    private AnchorPane box2;
    @FXML
    private AnchorPane box4;

    /**
     * Initializes the controller class.
     */
    ArrayList<AnnoncePerdu> list;
    ArrayList<Utilisateur> list1;
    @FXML
    private StackPane consulterAnnonce;
    @FXML
    private Label date2;
    @FXML
    private Label numero;
    @FXML
    private Label email;
    @FXML
    private Label date;
    @FXML
    private Label adresse;
    @FXML
    private Label date21;
    @FXML
    private Label date211;
    @FXML
    private Label date2111;
    @FXML
    private Label date21111;
    @FXML
    private Label nom;
    @FXML
    private Label nom21;
    @FXML
    private Label adresse1;
    @FXML
    private Label numero1;
    @FXML
    private Label email1;
    @FXML
    private Label email11;
    @FXML
    private Label email111;
    @FXML
    private Label email1111;
    @FXML
    private Label nom12;
    @FXML
    private Label nom11;
    @FXML
    private AnchorPane princi;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AnnoncePerduServices service = new AnnoncePerduServices();
      
             
        list = service.getList();
        
        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
        } else {
            setNbPages();
            initAnnoncePerduPage(0);
        }
        
       
    }    

    
    
    private void setNbPages() {
        if (list.size() % 4 != 0) {
            paginator.setPageCount((list.size() / 4) + 1);
        } else {
            paginator.setPageCount(list.size() / 4);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePerduPage(newIndex.intValue());
        });
    }

    public List<AnnoncePerdu> getAnnoncePerdusPage(int index) {

        int start = 4 * index;
        int fin = start + 4;
        if (list.size() > start) {
            if (list.size() > fin) {
                return list.subList(start, fin);
            } else {
                return list.subList(start, list.size());
            }
        }
        return list.subList(0, 3);
    }

    private void initAnnoncePerduPage(int index) {
        UtilisateurServices utilisateurservice = new UtilisateurServices();
        paginator.setCurrentPageIndex(index);
        List<AnnoncePerdu> QuatreAnnoncePerdus = getAnnoncePerdusPage(index);
        AnnoncePerduServices service = new AnnoncePerduServices();
        
        if (QuatreAnnoncePerdus.size() >= 1) {
            box1.setVisible(true);
            System.out.println(QuatreAnnoncePerdus.get(0).getId_utilisateur());
        Utilisateur u = utilisateurservice.rechercher(QuatreAnnoncePerdus.get(0).getId_utilisateur());
       nom1.setText(u.getNom()+" "+u.getPrenom());
       mail1.setText(u.getEmail());
       tel1.setText(String.valueOf(u.getNumero()));
       adr1.setText(QuatreAnnoncePerdus.get(0).getLieu_perdu());
       datedepo.setText(String.valueOf(QuatreAnnoncePerdus.get(0).getDate_perte()).substring(0, 10));
       type1.setText(QuatreAnnoncePerdus.get(0).getType());
       Image im = new Image ("file:///" +QuatreAnnoncePerdus.get(0).getImages().getPath());
       imageanimal1.setFitHeight(100);
       imageanimal1.setFitWidth(100);
       imageanimal1.setImage(im);

        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
         if (QuatreAnnoncePerdus.size() >= 2) {
       box2.setVisible(true);
           
        Utilisateur u = utilisateurservice.rechercher(QuatreAnnoncePerdus.get(1).getId_utilisateur());
       nom2.setText(u.getNom()+" "+u.getPrenom());
       mail2.setText(u.getEmail());
       tel2.setText(String.valueOf(u.getNumero()));
       adr2.setText(QuatreAnnoncePerdus.get(1).getLieu_perdu());
       datedepo2.setText(String.valueOf(QuatreAnnoncePerdus.get(1).getDate_perte()).substring(0, 10));
       type2.setText(QuatreAnnoncePerdus.get(1).getType());
       Image im = new Image ("file:///" +QuatreAnnoncePerdus.get(1).getImages().getPath());
       imageanimal2.setFitHeight(100);
       imageanimal2.setFitWidth(100);
       imageanimal2.setImage(im);

        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (QuatreAnnoncePerdus.size() >= 3) {
       box3.setVisible(true);
 
        Utilisateur u = utilisateurservice.rechercher(QuatreAnnoncePerdus.get(2).getId_utilisateur());
       nom3.setText(u.getNom()+" "+u.getPrenom());
       mail3.setText(u.getEmail());
       tel3.setText(String.valueOf(u.getNumero()));
       adr3.setText(QuatreAnnoncePerdus.get(2).getLieu_perdu());
       datedepo3.setText(String.valueOf(QuatreAnnoncePerdus.get(2).getDate_perte()).substring(0, 10));
       type3.setText(QuatreAnnoncePerdus.get(2).getType());
       Image im = new Image ("file:///" +QuatreAnnoncePerdus.get(2).getImages().getPath());
       imageanimal3.setFitHeight(100);
       imageanimal3.setFitWidth(100);
       imageanimal3.setImage(im);

        } else {
            box3.setVisible(false);
        }
        ///////////////////////////////////////////////////////////
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (QuatreAnnoncePerdus.size() >= 4) {
            box4.setVisible(true);
            //System.out.println(QuatreAnnoncePerdus.get(0).getId_utilisateur());
        Utilisateur u = utilisateurservice.rechercher(QuatreAnnoncePerdus.get(3).getId_utilisateur());
       nom4.setText(u.getNom()+" "+u.getPrenom());
       mail4.setText(u.getEmail());
       tel4.setText(String.valueOf(u.getNumero()));
       adr4.setText(QuatreAnnoncePerdus.get(3).getLieu_perdu());
       datedepo4.setText(String.valueOf(QuatreAnnoncePerdus.get(3).getDate_perte()).substring(0, 10));
       type4.setText(QuatreAnnoncePerdus.get(3).getType());
       Image im = new Image ("file:///" +QuatreAnnoncePerdus.get(3).getImages().getPath());
       imageanimal4.setFitHeight(100);
       imageanimal4.setFitWidth(100);
       imageanimal4.setImage(im);

        } else {
            box4.setVisible(false);
        }


    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void consulter(ActionEvent event) {
        
         consulterAnnonce.setVisible(true);
        
    }

    @FXML
    private void conuslter(ActionEvent event) {
    }

    
}
