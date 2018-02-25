/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceAccouplements.user;

import Entity.AnnonceAccouplement;
import Entity.Utilisateur;
import Service.AnnonceAccouplementServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLListeAnnonceAccouplementController implements Initializable {

    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView photo1;
    @FXML
    private Label animal1;
    @FXML
    private JFXButton modif1;
    @FXML
    private JFXButton supp1;
    @FXML
    private AnchorPane box2;
    @FXML
    private Label animal2;
    @FXML
    private JFXButton modif2;
    @FXML
    private JFXButton supp2;
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
    private Label age1;
    @FXML
    private Label date1;
    @FXML
    private Label animal3;
    @FXML
    private JFXButton modif3;
    @FXML
    private JFXButton supp3;
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
    private Label animal4;
    @FXML
    private JFXButton modif4;
    @FXML
    private JFXButton supp4;
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
    private AnchorPane box3;
    @FXML
    private AnchorPane box4;

    ArrayList<AnnonceAccouplement> list;
    @FXML
    private Label lieu1;
    @FXML
    private StackPane modifAnnonceAccouplement;
    @FXML
    private ChoiceBox<String> choixInsertion1;
    @FXML
    private JFXTextField raceInsertion1;
    @FXML
    private JFXRadioButton male1;
    @FXML
    private JFXRadioButton female1;
    @FXML
    private JFXRadioButton vouiInsertion1;
    @FXML
    private JFXRadioButton vnonInsertion1;
    @FXML
    private JFXRadioButton douiInsertion1;
    @FXML
    private JFXRadioButton dnonInsertion1;
    @FXML
    private ChoiceBox<String> poilInsertion1;
    @FXML
    private JFXTextField ageInsertion1;
    @FXML
    private JFXTextField couleurInsertion1;
    @FXML
    private JFXTextField msgInsertion1;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXTextField lieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceAccouplementServices service = new AnnonceAccouplementServices();

        list = service.getMesAnnoncesAccouplements(2);
        
        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
            paginator.setVisible(false);
            //vide.setVisible(true);
        } else {
            paginator.setVisible(true);
            setNbPages();
            initAnnonceAccouplementPage(0);
            //vide.setVisible(false);

        }
       
        choixInsertion1.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        
        
        
        poilInsertion1.getItems().setAll("Nus","Sans sous-poil","Double pelage","Poils courts","Poils longs");
        
        
        
        
        ToggleGroup groupi = new ToggleGroup();
        ToggleGroup groupm = new ToggleGroup();
        
        ToggleGroup groupvi = new ToggleGroup();
        ToggleGroup groupvm = new ToggleGroup();
        
        
        ToggleGroup groupdi = new ToggleGroup();
        ToggleGroup groupdm = new ToggleGroup();
        
        male1.setToggleGroup(groupi);
        female1.setToggleGroup(groupi);
        
        
        
        vouiInsertion1.setToggleGroup(groupvm);
        vnonInsertion1.setToggleGroup(groupvm);
        
       
        
        douiInsertion1.setToggleGroup(groupdm);
        dnonInsertion1.setToggleGroup(groupdm);
        
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

        UtilisateurServices utilisateurservice = new UtilisateurServices();
        paginator.setCurrentPageIndex(index);
        List<AnnonceAccouplement> QuatreAnnonceAccouplements = getAnnonceAccouplementsPage(index);
        if (QuatreAnnonceAccouplements.size() >= 1) {
            box1.setVisible(true);

           
            Utilisateur u = utilisateurservice.rechercher(QuatreAnnonceAccouplements.get(0).getId_utilisateur());
            animal1.setText(QuatreAnnonceAccouplements.get(0).getType());
            lieu1.setText(QuatreAnnonceAccouplements.get(0).getLieu());
            race1.setText(QuatreAnnonceAccouplements.get(0).getRace());
            sexe1.setText(QuatreAnnonceAccouplements.get(0).getSex());
            age1.setText(String.valueOf(QuatreAnnonceAccouplements.get(0).getAge()));
            date1.setText(String.valueOf(QuatreAnnonceAccouplements.get(0).getDate()).substring(0, 10));
            //System.out.println(QuatreAnnonceAccouplements.get(0).getImages());
            
            Image im = new Image("file:///" + QuatreAnnonceAccouplements.get(0).getImages().getPath());
            photo1.setFitHeight(225);
            photo1.setFitWidth(250);
            photo1.setImage(im);
            

            modif1.setOnAction((ActionEvent e) -> {
                modifierannonce(QuatreAnnonceAccouplements.get(0), u);
                valider.setOnAction((ActionEvent el) -> {
                modifier(QuatreAnnonceAccouplements.get(0).getId());
            });
            });
            supp1.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(0).getId());
            });

        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (QuatreAnnonceAccouplements.size() >= 2) {
            box2.setVisible(true);
            
            Utilisateur u = utilisateurservice.rechercher(QuatreAnnonceAccouplements.get(1).getId_utilisateur());
            animal2.setText(QuatreAnnonceAccouplements.get(1).getType());
            lieu2.setText(QuatreAnnonceAccouplements.get(1).getLieu());
            race2.setText(QuatreAnnonceAccouplements.get(1).getRace());
            sexe2.setText(QuatreAnnonceAccouplements.get(1).getSex());
            age2.setText(String.valueOf(QuatreAnnonceAccouplements.get(1).getAge()));
            date2.setText(String.valueOf(QuatreAnnonceAccouplements.get(1).getDate()).substring(0, 10));
            //System.out.println(QuatreAnnonceAccouplements.get(1).getImages());
            
            Image im = new Image("file:///" + QuatreAnnonceAccouplements.get(1).getImages().getPath());
            photo2.setFitHeight(225);
            photo2.setFitWidth(250);
            photo2.setImage(im);
            

            modif2.setOnAction((ActionEvent e) -> {
                modifierannonce(QuatreAnnonceAccouplements.get(1), u);
                valider.setOnAction((ActionEvent e1) -> {
                modifier(QuatreAnnonceAccouplements.get(1).getId());
            });
            });
            supp2.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(1).getId());
            });
            
        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (QuatreAnnonceAccouplements.size() >= 3) {
            box3.setVisible(true);
            
            
            Utilisateur u = utilisateurservice.rechercher(QuatreAnnonceAccouplements.get(2).getId_utilisateur());
            animal3.setText(QuatreAnnonceAccouplements.get(2).getType());
            lieu3.setText(QuatreAnnonceAccouplements.get(2).getLieu());
            race3.setText(QuatreAnnonceAccouplements.get(2).getRace());
            sexe3.setText(QuatreAnnonceAccouplements.get(2).getSex());
            age3.setText(String.valueOf(QuatreAnnonceAccouplements.get(2).getAge()));
            date3.setText(String.valueOf(QuatreAnnonceAccouplements.get(2).getDate()).substring(0, 10));
            //System.out.println(QuatreAnnonceAccouplements.get(2).getImages());
            
            Image im = new Image("file:///" + QuatreAnnonceAccouplements.get(2).getImages().getPath());
            photo3.setFitHeight(225);
            photo3.setFitWidth(250);
            photo3.setImage(im);
            

            modif3.setOnAction((ActionEvent e) -> {
                modifierannonce(QuatreAnnonceAccouplements.get(2), u);
                valider.setOnAction((ActionEvent el) -> {
                modifier(QuatreAnnonceAccouplements.get(2).getId());
            });
            });
            supp3.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(2).getId());
            });
            
            
        } else {
            box3.setVisible(false);
        }

        ///////////////////////////////////////////////////////////
        if (QuatreAnnonceAccouplements.size() >= 4) {
            box4.setVisible(true);

            Utilisateur u = utilisateurservice.rechercher(QuatreAnnonceAccouplements.get(3).getId_utilisateur());
            animal4.setText(QuatreAnnonceAccouplements.get(3).getType());
            //lieu4.setText(QuatreAnnonceAccouplements.get(3).getLieu());
            race4.setText(QuatreAnnonceAccouplements.get(3).getRace());
            sexe4.setText(QuatreAnnonceAccouplements.get(3).getSex());
            age4.setText(String.valueOf(QuatreAnnonceAccouplements.get(3).getAge()));
            date4.setText(String.valueOf(QuatreAnnonceAccouplements.get(3).getDate()).substring(0, 10));
            //System.out.println(QuatreAnnonceAccouplements.get(3).getImages());
            
            Image im = new Image("file:///" + QuatreAnnonceAccouplements.get(3).getImages().getPath());
            photo4.setFitHeight(225);
            photo4.setFitWidth(250);
            photo4.setImage(im);
            

            modif4.setOnAction((ActionEvent e) -> {
                modifierannonce(QuatreAnnonceAccouplements.get(3), u);
                valider.setOnAction((ActionEvent el) -> {
                modifier(QuatreAnnonceAccouplements.get(3).getId());
            });
            });
            supp4.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(3).getId());
            });
            
        } else {
            box4.setVisible(false);
        }

    }
    
    private void modifierannonce(AnnonceAccouplement a, Utilisateur u) {
     
            if(a.getSex().equals("Female"))
            {
                female1.setSelected(true);
            }
            else{
                male1.setSelected(true);
            }
            
            
            if(a.getVaccin().equals("NON"))
            {
                vnonInsertion1.setSelected(true);
            }
            else{
                vouiInsertion1.setSelected(true);
            }
            
            
            if(a.getDossier().equals("NON"))
            {
                dnonInsertion1.setSelected(true);
            }
            else{
                douiInsertion1.setSelected(true);
            }
            
            
            poilInsertion1.setValue(a.getType_poil());
            ageInsertion1.setText(String.valueOf(a.getAge()));
            raceInsertion1.setText(a.getRace());
            lieu.setText(a.getLieu());
            msgInsertion1.setText(a.getMessage_complementaire());
            couleurInsertion1.setText(a.getCouleur());
            choixInsertion1.setValue(a.getType());
            modifAnnonceAccouplement.setVisible(true);     
            
    
    }

    private void modifier(int id) {
       if ((!"".equals(couleurInsertion1.getText()))&& (!"".equals(ageInsertion1.getText())) && (!"".equals(lieu.getText()))
                 && (!"".equals(raceInsertion1.getText()))&& (!"".equals(msgInsertion1.getText()))&& (!"".equals(choixInsertion1.getValue()))&& (!"".equals(poilInsertion1.getValue())))
        {
              AnnonceAccouplementServices as = new AnnonceAccouplementServices();
            String sexe="Male";
            if (female1.isSelected())
            {
                sexe="Female";
            }
            
            String vaccin="OUI";
            if (vnonInsertion1.isSelected())
            {
                vaccin="NON";
            }
            
            String dossier="OUI";
            if (dnonInsertion1.isSelected())
            {
                dossier="NON";
            }
            
//            System.out.println(new AnnonceAccouplement(poilInsertion1.getValue(), vaccin, dossier,0 , Integer.parseInt(ageInsertion1.getText()), couleurInsertion1.getText(), sexe, raceInsertion1.getText(), msgInsertion1.getText(),choixInsertion1.getValue(), null));
//            System.out.println(id);
            as.updateAnnonceAccouplement(new AnnonceAccouplement(
                    poilInsertion1.getValue(), 
                    vaccin, 
                    dossier,
                    lieu.getText(), 
                    0 , 
                    Integer.parseInt(ageInsertion1.getText()), 
                    couleurInsertion1.getText(), 
                    sexe, 
                    raceInsertion1.getText(), 
                    msgInsertion1.getText(),
                    choixInsertion1.getValue(),
                    null,null,0),id);

           
            poilInsertion1.setValue("Nus");
            ageInsertion1.setText("");
            lieu.setText("");
            raceInsertion1.setText("");
            msgInsertion1.setText("");
            couleurInsertion1.setText("");
            choixInsertion1.setValue("Chien");
            //loadTable();
            AnnonceAccouplementServices service = new AnnonceAccouplementServices();
            list = service.getMesAnnoncesAccouplements(2);
                    if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
        } else {
            setNbPages();
            initAnnonceAccouplementPage(0);
        }
        }
    }

    private void supprimerannonce(int id) {
                AnnonceAccouplementServices as = new AnnonceAccouplementServices();
                as.DeleteAnnonceAccouplement(id);
                     modifAnnonceAccouplement.setVisible(false);
        AnnonceAccouplementServices service = new AnnonceAccouplementServices();
      
             
        list = service.getMesAnnoncesAccouplements(2);
        
        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
        } else {
            setNbPages();
            initAnnonceAccouplementPage(0);
        }
        
        
        
    }

    @FXML
    private void actionModification2(ActionEvent event) {
         
    }

    @FXML
    private void fermer(ActionEvent event) {
        modifAnnonceAccouplement.setVisible(false);
    }

}
