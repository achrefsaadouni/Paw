/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu.user.mesAnnonces;

import Entity.AnnoncePerdu;
import Entity.Utilisateur;
import Service.AnnoncePerduServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import static paw.Paw.session;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLMesAnnonceController implements Initializable {

    @FXML
    private AnchorPane princi;
    @FXML
    private AnchorPane box1;
    @FXML
    private Label nom1;
    @FXML
    private Label tel1;
    @FXML
    private Label type1;
    @FXML
    private Label adr1;
    @FXML
    private JFXButton modifier1;
    @FXML
    private JFXButton supprimer1;
    @FXML
    private ImageView imageanimal1;
    @FXML
    private Label mail1;
    @FXML
    private Label datedepo;
    @FXML
    private AnchorPane box3;
    @FXML
    private Label nom3;
    @FXML
    private Label tel3;
    @FXML
    private Label type3;
    @FXML
    private Label adr3;
    @FXML
    private JFXButton modifier3;
    @FXML
    private JFXButton supprimer3;
    @FXML
    private ImageView imageanimal3;
    @FXML
    private Label mail3;
    @FXML
    private Label datedepo3;
    @FXML
    private AnchorPane box2;
    @FXML
    private Label nom2;
    @FXML
    private Label tel2;
    @FXML
    private Label type2;
    @FXML
    private Label adr2;
    @FXML
    private JFXButton modifier2;
    @FXML
    private JFXButton supprimer2;
    @FXML
    private ImageView imageanimal2;
    @FXML
    private Label mail2;
    @FXML
    private Label datedepo2;
    @FXML
    private AnchorPane box4;
    @FXML
    private Label nom4;
    @FXML
    private Label tel4;
    @FXML
    private Label type4;
    @FXML
    private Label adr4;
    @FXML
    private JFXButton modifier4;
    @FXML
    private JFXButton supprimer4;
    @FXML
    private ImageView imageanimal4;
    @FXML
    private Label mail4;
    @FXML
    private Label datedepo4;
    @FXML
    private Pagination paginator;
    @FXML
    private StackPane consulterAnnonce;
    @FXML
    private Label nom12;
    @FXML
    private Label nom11;

    private ArrayList<AnnoncePerdu> list ;
    @FXML
    private JFXTextField ageModification;
    @FXML
    private JFXTextField sexModification;
    @FXML
    private JFXTextField lieuModification;
    @FXML
    private JFXTextField msgModification;
    @FXML
    private JFXTextField raceModification;
    @FXML
    private JFXTextField couleurModification;
    @FXML
    private JFXTextField colierModification;
    @FXML
    private ChoiceBox<String> choixModification;
    @FXML
    private JFXButton valider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     consulterAnnonce.setVisible(false);
        AnnoncePerduServices service = new AnnoncePerduServices();
      
             
        list = service.getMesAnnoncesPerdus(2);
        
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
        //System.out.println(QuatreAnnoncePerdus.size());
        if (QuatreAnnoncePerdus.size() >= 1) {
            box1.setVisible(true);
            System.out.println(QuatreAnnoncePerdus.get(0).getId_utilisateur());
        Utilisateur u = utilisateurservice.rechercher(QuatreAnnoncePerdus.get(0).getId_utilisateur());
       nom1.setText(u.getNom()+" "+u.getPrenom());
       mail1.setText(u.getEmail());
       tel1.setText(String.valueOf(u.getNumero()));
       adr1.setText(QuatreAnnoncePerdus.get(0).getLieu_perdu());
       datedepo.setText(String.valueOf(QuatreAnnoncePerdus.get(0).getDate_perte()).substring(0, 10));
            System.out.println(QuatreAnnoncePerdus.get(0).getImages());
       type1.setText(QuatreAnnoncePerdus.get(0).getType());
       Image im = new Image ("file:///" +QuatreAnnoncePerdus.get(0).getImages().getPath());
       imageanimal1.setFitHeight(100);
       imageanimal1.setFitWidth(100);
       imageanimal1.setImage(im);
        valider.setOnAction((ActionEvent e) ->{
            modifier(QuatreAnnoncePerdus.get(0).getId());
       });

       modifier1.setOnAction((ActionEvent e) -> {
                    modifierannonce(QuatreAnnoncePerdus.get(0),u);
                });
       supprimer1.setOnAction((ActionEvent e)->{
       supprimerannonce(QuatreAnnoncePerdus.get(0).getId()) ; 
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
        valider.setOnAction((ActionEvent e) ->{
            modifier(QuatreAnnoncePerdus.get(1).getId());
       });

       modifier2.setOnAction((ActionEvent e) -> {
                    modifierannonce(QuatreAnnoncePerdus.get(1),u);
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
       
       valider.setOnAction((ActionEvent e) ->{
            modifier(QuatreAnnoncePerdus.get(2).getId());
       });
       
       
       modifier3.setOnAction((ActionEvent e) -> {
                    modifierannonce(QuatreAnnoncePerdus.get(2),u);
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
        valider.setOnAction((ActionEvent e) ->{
            modifier(QuatreAnnoncePerdus.get(3).getId());
       });

        modifier4.setOnAction((ActionEvent e) -> {
                   modifierannonce(QuatreAnnoncePerdus.get(3),u);
                });

        } else {
            box4.setVisible(false);
        }
         


    }
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    @FXML
    private void retour(ActionEvent event) {
         consulterAnnonce.setVisible(false);
           
    }

    private void modifierannonce(AnnoncePerdu a, Utilisateur u) {
     
             sexModification.setText(a.getSex());
             choixModification.setValue(a.getType());
             lieuModification.setText(a.getLieu_perdu());
             msgModification.setText(a.getMessage_complementaire());
             raceModification.setText(a.getRace());
             couleurModification.setText(a.getCouleur());
             colierModification.setText(a.getColier());
             ageModification.setText(String.valueOf(a.getAge()));
            consulterAnnonce.setVisible(true);     
            
    
    }

    private void modifier(int id) {
       if ((!"".equals(couleurModification.getText()))&& (!"".equals(ageModification.getText()))&& (!"".equals(sexModification.getText()))
                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))
                    && (!"".equals(choixModification.getValue()))&& (!"".equals(colierModification.getText()))
                    
                    
                    && (!"".equals(lieuModification.getText())))
        {
            AnnoncePerduServices as = new AnnoncePerduServices();
            
           as.updateAnnoncePerdu(
                   new AnnoncePerdu(colierModification.getText(), null, lieuModification.getText(), id, Integer.parseInt(ageModification.getText()), couleurModification.getText(), sexModification.getText(), raceModification.getText(), msgModification.getText(), choixModification.getValue(), null, 0)
                   ,id);
           list=as.getMesAnnoncesPerdus(2) ; 
            
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
    }

    private void supprimerannonce(int id) {
                AnnoncePerduServices as = new AnnoncePerduServices();
                as.DeleteAnnoncePerdu(id);
                     consulterAnnonce.setVisible(false);
        AnnoncePerduServices service = new AnnoncePerduServices();
      
             
        list = service.getMesAnnoncesPerdus(2);
        
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

   
}
