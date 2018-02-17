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
import com.jfoenix.controls.JFXButton;
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
    private Label numero;
    @FXML
    private Label email;
    @FXML
    private Label date;
    @FXML
    private Label adresse;
    @FXML
    private Label nom;
    @FXML
    private Label nom21;
    @FXML
    private Label nom12;
    @FXML
    private Label nom11;
    @FXML
    private AnchorPane princi;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn4;
    @FXML
    private Label type;
    @FXML
    private Label sexe;
    @FXML
    private Label race;
    @FXML
    private Label couleur;
    @FXML
    private Label age;
    @FXML
    private Label datedisparition;
    @FXML
    private Label colier;
    @FXML
    private Label lieu;
    @FXML
    private Label prenom;
    @FXML
    private Label sexe1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consulterAnnonce.setVisible(false);
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
       btn1.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnoncePerdus.get(0),u);
                });

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
       btn2.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnoncePerdus.get(1),u);
                });

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
        btn3.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnoncePerdus.get(2),u);
                });

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
        btn4.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnoncePerdus.get(3),u);
                });

        } else {
            box4.setVisible(false);
        }


    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void consulter(ActionEvent event) {
        
         consulterAnnonce.setVisible(true);
        
    }

    

    private void voirannonce(AnnoncePerdu a, Utilisateur u) {
        type.setText("Type : "+ a.getType());
        sexe.setText("Son sexe est :  "+a.getSex());
        race.setText("Sa race est :   "+a.getRace());
        couleur.setText("Sa couleur est :  "+a.getCouleur());
        age.setText("Son age est :  "+String.valueOf(a.getAge()));
        datedisparition.setText("Cet Animal est disparu le :  "+String.valueOf(a.getDate_perte()).substring(0, 10));
        colier.setText("Il porte un colier :   "+a.getColier());
        date.setText("L'annonce a été deposée le :  "+String.valueOf(a.getDate()).substring(0,10));
        lieu.setText("Le lieu de disparition est :  "+a.getLieu_perdu());
        nom.setText("Le nom de son proprietaire :  "+u.getNom());
        prenom.setText("Son prenom est :  "+u.getPrenom());
        sexe1.setText("L'adresse du proprietaire est :  "+u.getAddresse());
        email.setText("Son Email est :  "+u.getEmail());
        numero.setText("Son numero est  :  "+String.valueOf(u.getNumero()));
        adresse.setText("L'adresse du proprietaire est :   "+u.getAddresse()) ; 
       
        consulterAnnonce.setVisible(true);
    }

    @FXML
    private void retour(ActionEvent event) {
        
         consulterAnnonce.setVisible(false);
        
    }

    
}
