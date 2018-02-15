/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceperdu.user;

import Entity.AnnoncePerdu;
import Entity.Utilisateur;
import Entity.Vets;
import Service.AnnoncePerduServices;
import Service.UtilisateurServices;
import Service.AnnoncePerduServices;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AnnoncePerduServices service = new AnnoncePerduServices();
      
        //ArrayList<AnnoncePerdu> list= service.getList();
       // ArrayList<Utilisateur> list1= service.getNomPrenom();        
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
        
        /*nom1.setText(list1.get(0).getNom()+" "+list1.get(0).getPrenom());
        mail1.setText(list1.get(0).getEmail());
        tel1.setText(String.valueOf(list1.get(0).getNumero()));
        adr1.setText(list.get(0).getLieu_perdu());
       datedepo.setText(String.valueOf(list.get(0).getDate_perte()));
     type1.setText(list.get(0).getType());
       
        
       nom2.setText(list1.get(1).getNom()+" "+list1.get(1).getPrenom());
        mail2.setText(list1.get(1).getEmail());
        tel2.setText(String.valueOf(list1.get(1).getNumero()));
        adr2.setText(list.get(1).getLieu_perdu());
           datedepo2.setText(String.valueOf(list.get(1).getDate_perte()));
     type2.setText(list.get(1).getType());
        
        
        nom3.setText(list1.get(2).getNom()+" "+list1.get(2).getPrenom());
       mail3.setText(list1.get(2).getEmail());
        tel3.setText(String.valueOf(list1.get(2).getNumero()));
       adr3.setText(list.get(2).getLieu_perdu());
          datedepo3.setText(String.valueOf(list.get(2).getDate_perte()));
     type3.setText(list.get(2).getType());   
        
       nom4.setText(list1.get(3).getNom()+" "+list1.get(3).getPrenom());
       mail4.setText(list1.get(3).getEmail());
        tel4.setText(String.valueOf(list1.get(3).getNumero()));
       adr4.setText(list.get(3).getLieu_perdu());
       datedepo4.setText(String.valueOf(list.get(3).getDate_perte()));
     type4.setText(list.get(3).getType());*/
      
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
        
        paginator.setCurrentPageIndex(index);
        List<AnnoncePerdu> QuatreAnnoncePerdus = getAnnoncePerdusPage(index);
        AnnoncePerduServices service = new AnnoncePerduServices();
        list1= service.getUtilisateurs(QuatreAnnoncePerdus.get(0).getId_utilisateur());
        if (QuatreAnnoncePerdus.size() >= 1) {
            box1.setVisible(true);

        nom1.setText(list1.get(0).getNom()+" "+list1.get(0).getPrenom());
       mail1.setText(list1.get(0).getEmail());
       tel1.setText(String.valueOf(list1.get(0).getNumero()));
        adr1.setText(QuatreAnnoncePerdus.get(0).getLieu_perdu());
       datedepo.setText(String.valueOf(QuatreAnnoncePerdus.get(0).getDate_perte()).substring(0, 10));
     type1.setText(QuatreAnnoncePerdus.get(0).getType());

        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (QuatreAnnoncePerdus.size() >= 2) {
            list1= service.getUtilisateurs(QuatreAnnoncePerdus.get(1).getId_utilisateur());
            box2.setVisible(true);
            System.out.println(list1.size());
            nom2.setText(list1.get(0).getNom()+" "+list1.get(0).getPrenom());
        mail2.setText(list1.get(0).getEmail());
        tel2.setText(String.valueOf(list1.get(0).getNumero()));
        adr2.setText(QuatreAnnoncePerdus.get(1).getLieu_perdu());
           datedepo2.setText(String.valueOf(QuatreAnnoncePerdus.get(1).getDate_perte()).substring(0, 10));
     type2.setText(QuatreAnnoncePerdus.get(1).getType());
            

        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (QuatreAnnoncePerdus.size() >= 3) {
            list1= service.getUtilisateurs(QuatreAnnoncePerdus.get(2).getId_utilisateur());
            box3.setVisible(true);

            nom3.setText(list1.get(0).getNom()+" "+list1.get(0).getPrenom());
       mail3.setText(list1.get(0).getEmail());
        tel3.setText(String.valueOf(list1.get(0).getNumero()));
       adr3.setText(QuatreAnnoncePerdus.get(2).getLieu_perdu());
          datedepo3.setText(String.valueOf(QuatreAnnoncePerdus.get(2).getDate_perte()).substring(0, 10));
     type3.setText(QuatreAnnoncePerdus.get(2).getType());

        } else {
            box3.setVisible(false);
        }

        ///////////////////////////////////////////////////////////
        if (QuatreAnnoncePerdus.size() >= 4) {
            list1= service.getUtilisateurs(QuatreAnnoncePerdus.get(3).getId_utilisateur());
            box4.setVisible(true);

            nom4.setText(list1.get(0).getNom()+" "+list1.get(0).getPrenom());
       mail4.setText(list1.get(0).getEmail());
        tel4.setText(String.valueOf(list1.get(0).getNumero()));
       adr4.setText(QuatreAnnoncePerdus.get(3).getLieu_perdu());
       datedepo4.setText(String.valueOf(QuatreAnnoncePerdus.get(3).getDate_perte()).substring(0, 10));
     type4.setText(QuatreAnnoncePerdus.get(3).getType());

        } else {
            box4.setVisible(false);
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void consulter1(ActionEvent event) {
        
        
        
    }

    @FXML
    private void conuslter3(ActionEvent event) {
    }

    @FXML
    private void conuslter2(ActionEvent event) {
    }

    @FXML
    private void conuslter4(ActionEvent event) {
    }
    
}
