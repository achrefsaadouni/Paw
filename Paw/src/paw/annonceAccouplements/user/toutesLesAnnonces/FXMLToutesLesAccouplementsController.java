/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceAccouplements.user.toutesLesAnnonces;

import Entity.AnnonceAccouplement;
import Service.AnnonceAccouplementServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.util.ResourceBundle;
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
 * @author gmehd
 */
public class FXMLToutesLesAccouplementsController implements Initializable {

    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView photo1;
    @FXML
    private Label animal1;
   
    @FXML
    private AnchorPane box2;
    @FXML
    private Label animal2;
    @FXML
    private ImageView photo2;
    @FXML
    private Label race2;
    @FXML
    private Label sexe2;
    @FXML
    private Label lieu2;
    @FXML
    private Label age2;
    @FXML
    private Label date2;
    @FXML
    private Pagination paginator;
    @FXML
    private Label race1;
    @FXML
    private Label sexe1;
    @FXML
    private Label lieu1;
    @FXML
    private Label age1;
    @FXML
    private Label date1;
    @FXML
    private AnchorPane box3;
    @FXML
    private Label animal3;
    @FXML
    private ImageView photo3;
    @FXML
    private Label race3;
    @FXML
    private Label sexe3;
    @FXML
    private Label lieu3;
    @FXML
    private Label age3;
    @FXML
    private Label date3;
    @FXML
    private AnchorPane box4;
    @FXML
    private Label animal4;
    @FXML
    private ImageView photo4;
    @FXML
    private Label race4;
    @FXML
    private Label sexe4;
    @FXML
    private Label lieu4;
    @FXML
    private Label age4;
    @FXML
    private Label date4;
    @FXML
    private StackPane modifAnnonceAccouplement;
    @FXML
    private Label type;
    @FXML
    private Label sexe;
    @FXML
    private Label vaccin;
    @FXML
    private Label dossier;
    @FXML
    private Label poil;
    @FXML
    private Label race;
    @FXML
    private Label age;
    @FXML
    private Label message;
    
    ArrayList<AnnonceAccouplement> list;
    @FXML
    private JFXButton aff1;
    @FXML
    private JFXButton aff2;
    @FXML
    private JFXButton aff3;
    @FXML
    private JFXButton aff4;
    @FXML
    private Label couleur;
    @FXML
    private Label date;
    @FXML
    private Label lieu;
    @FXML
    private StackPane vide;
    /**
     * Initializes the controller class.
     */
    @Override
   
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceAccouplementServices service = new AnnonceAccouplementServices();

        list = service.getList();
        
        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
            paginator.setVisible(false);
            vide.setVisible(true);
        } else {
            paginator.setVisible(true);
            setNbPages();
            initAnnonceAccouplementPage(0);
            vide.setVisible(false);

        }
    }

    private void setNbPages() {
        if (list.size() % 4 != 0) {
            paginator.setPageCount((list.size() / 4) + 1);
        } else {
            paginator.setPageCount(list.size() / 4);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnonceAccouplementPage(newIndex.intValue());
        });
    }

    public List<AnnonceAccouplement> getAnnonceAccouplementsPage(int index) {

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

    private void initAnnonceAccouplementPage(int index) {

        
        paginator.setCurrentPageIndex(index);
        List<AnnonceAccouplement> QuatreAnnonceAccouplements = getAnnonceAccouplementsPage(index);
        if (QuatreAnnonceAccouplements.size() >= 1) {
            box1.setVisible(true);

           
            
            animal1.setText(QuatreAnnonceAccouplements.get(0).getType());
            lieu1.setText(QuatreAnnonceAccouplements.get(0).getLieu());
            race1.setText(QuatreAnnonceAccouplements.get(0).getRace());
            sexe1.setText(QuatreAnnonceAccouplements.get(0).getSex());
            age1.setText(String.valueOf(QuatreAnnonceAccouplements.get(0).getAge()));
            date1.setText(String.valueOf(QuatreAnnonceAccouplements.get(0).getDate()).substring(0, 10));

            
            Image im = new Image("http://localhost/paw_web/web/images/pawPets/" + QuatreAnnonceAccouplements.get(0).getImages().getName());
            photo1.setFitHeight(225);
            photo1.setFitWidth(250);
            photo1.setImage(im);
            
            aff1.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnonceAccouplements.get(0));
                });

            
            

        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (QuatreAnnonceAccouplements.size() >= 2) {
            box2.setVisible(true);
            
            
            animal2.setText(QuatreAnnonceAccouplements.get(1).getType());
            lieu2.setText(QuatreAnnonceAccouplements.get(1).getLieu());
            race2.setText(QuatreAnnonceAccouplements.get(1).getRace());
            sexe2.setText(QuatreAnnonceAccouplements.get(1).getSex());
            age2.setText(String.valueOf(QuatreAnnonceAccouplements.get(1).getAge()));
            date2.setText(String.valueOf(QuatreAnnonceAccouplements.get(1).getDate()).substring(0, 10));
           
            
            Image im = new Image("http://localhost/paw_web/web/images/pawPets/" + QuatreAnnonceAccouplements.get(1).getImages().getName());
            photo2.setFitHeight(225);
            photo2.setFitWidth(250);
            photo2.setImage(im);
            aff2.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnonceAccouplements.get(1));
                });

            
            
        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (QuatreAnnonceAccouplements.size() >= 3) {
            box3.setVisible(true);
            
            
            
            animal3.setText(QuatreAnnonceAccouplements.get(2).getType());
            lieu3.setText(QuatreAnnonceAccouplements.get(2).getLieu());
            race3.setText(QuatreAnnonceAccouplements.get(2).getRace());
            sexe3.setText(QuatreAnnonceAccouplements.get(2).getSex());
            age3.setText(String.valueOf(QuatreAnnonceAccouplements.get(2).getAge()));
            date3.setText(String.valueOf(QuatreAnnonceAccouplements.get(2).getDate()).substring(0, 10));
           
            
            Image im = new Image("http://localhost/paw_web/web/images/pawPets/" + QuatreAnnonceAccouplements.get(2).getImages().getName());
            photo3.setFitHeight(225);
            photo3.setFitWidth(250);
            photo3.setImage(im);
            aff3.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnonceAccouplements.get(2));
                });

            
            
            
        } else {
            box3.setVisible(false);
        }

        ///////////////////////////////////////////////////////////
        if (QuatreAnnonceAccouplements.size() >= 4) {
            box4.setVisible(true);

           
            animal4.setText(QuatreAnnonceAccouplements.get(3).getType());
            lieu4.setText(QuatreAnnonceAccouplements.get(3).getLieu());
            race4.setText(QuatreAnnonceAccouplements.get(3).getRace());
            sexe4.setText(QuatreAnnonceAccouplements.get(3).getSex());
            age4.setText(String.valueOf(QuatreAnnonceAccouplements.get(3).getAge()));
            date4.setText(String.valueOf(QuatreAnnonceAccouplements.get(3).getDate()).substring(0, 10));
            
            
            Image im = new Image("http://localhost/paw_web/web/images/pawPets/" + QuatreAnnonceAccouplements.get(3).getImages().getName());
            photo4.setFitHeight(225);
            photo4.setFitWidth(250);
            photo4.setImage(im);
            aff4.setOnAction((ActionEvent e) -> {
                    voirannonce(QuatreAnnonceAccouplements.get(3));
                });

            
            
        } else {
            box4.setVisible(false);
        }

    }

    @FXML
    private void fermer(ActionEvent event) {
        modifAnnonceAccouplement.setVisible(false);
    }
    
    private void voirannonce(AnnonceAccouplement a) {
        type.setText(a.getType());
        sexe.setText(a.getSex());
        vaccin.setText(a.getVaccin());
        dossier.setText(a.getDossier());
        poil.setText(a.getType_poil());
        race.setText(a.getRace());
        couleur.setText(a.getCouleur());
        age.setText(String.valueOf(a.getAge()));
        message.setText(a.getMessage_complementaire());
        date.setText(String.valueOf(a.getDate()).substring(0,10));
        lieu.setText(a.getLieu());
        modifAnnonceAccouplement.setVisible(true);
    }
    

 }
