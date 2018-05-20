 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.profile;

import Entity.Utilisateur;
import Service.AchatService;
import Service.AnnonceAccouplementServices;
import Service.AnnonceAdoptionService;
import Service.AnnonceServices;
import Service.AnnonceSittingServices;
import Service.LoginServices;
import Service.ReclamationServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static paw.Paw.session;
import paw.mainUI.FXMLCnxController;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLprofileController extends FXMLCnxController implements Initializable  {

    @FXML
    private ImageView avatar;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label numero;
    @FXML
    private Label date;
    @FXML
    private Label achats;
    @FXML
    private Label annonces;
    @FXML
    private AnchorPane window;
    @FXML
    private Label nom1;
    @FXML
    private AnchorPane modifanchor;
    @FXML
    private ImageView avatarmodif;
    @FXML
    private Label usermodif;
    @FXML
    private JFXTextField nommodif;
    @FXML
    private JFXTextField prenommodif;
    @FXML
    private JFXTextField adressemodif;
    @FXML
    private JFXTextField numeromodif;
    @FXML
    private JFXRadioButton hmodif;
    @FXML
    private JFXRadioButton fmodif;
    private JFXTextField emailmodif;
    @FXML
    private StackPane stack;
    @FXML
    private AnchorPane details;
    @FXML
    private Label reclamations;
    @FXML
    private Label mesoffresadoption;
    @FXML
    private Label mesaccouplements;
    @FXML
    private Label sitting;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        ToggleGroup togGroup = new ToggleGroup();
        usermodif.setText(session.getUsername());
        fmodif.setToggleGroup(togGroup);
        hmodif.setToggleGroup(togGroup);
        hmodif.setSelectedColor(Color.rgb(51, 122, 183));
        fmodif.setSelectedColor(Color.rgb(51, 122, 183));
        modifanchor.setVisible(false);
        try{
            avatar.setImage(new Image("http://localhost/paw_web/web/images/pawUsers/"+session.getAvatar()));
            avatar.setFitWidth(175);
            avatar.setFitHeight(169);
            
            avatarmodif.setImage(new Image("http://localhost/paw_web/web/images/pawUsers/"+session.getAvatar()));
            
        }
        catch(Exception e)
        {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, e);
        }
        RequiredFieldValidator rf = new RequiredFieldValidator();
        rf.setMessage("Veuillez remplir ce champs");
        nommodif.getValidators().add(rf);
        nommodif.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    nommodif.validate();
                }
            }
        });
        RequiredFieldValidator rf1 = new RequiredFieldValidator();
        rf1.setMessage("Veuillez remplir ce champs");
        prenommodif.getValidators().add(rf1);
        prenommodif.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    prenommodif.validate();
                }
            }
        });
        RequiredFieldValidator rf2 = new RequiredFieldValidator();
        rf2.setMessage("Veuillez remplir ce champs");
        adressemodif.getValidators().add(rf2);
        adressemodif.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    adressemodif.validate();
                }
            }
        });
        NumberValidator nv = new NumberValidator();
        nv.setMessage("Veuillez saisir un numéro valide");
        numeromodif.getValidators().add(rf);
        numeromodif.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    numeromodif.validate();
                }
            }
        });
        
        
        loadDetails();
    }    

    @FXML
    private void goToAchat(MouseEvent event) {
        try {
            
            loadSplashScreen("/paw/boutique/user/achat/FXMLMesAchat.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAnnonce(MouseEvent event) {
        try{
            loadSplashScreen("/paw/annonceperdu/user/mesAnnonces/FXMLPerduFinal.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToCours(MouseEvent event) {
        try {
           
            loadSplashScreen("/paw/reclamation/FXMLReclamation.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void modifier(ActionEvent event) {
        nommodif.setText(session.getNom());
        prenommodif.setText(session.getPrenom());
        adressemodif.setText(session.getAddresse());
        numeromodif.setText(String.valueOf(session.getNumero()));
        if (session.getSexe().equals("Homme"))
        {
            hmodif.setSelected(true);
            fmodif.setSelected(false);
        }
        else{
            hmodif.setSelected(false);
            fmodif.setSelected(true);
        }
        modifanchor.setVisible(true);
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        
        UtilisateurServices s = new UtilisateurServices();
        String x="Femme";
        if(hmodif.isSelected())
        {
            x = "Homme";
        }
        
        if(nommodif.validate()&&prenommodif.validate()&&adressemodif.validate()&&numeromodif.validate())
        {
            s.modifierInfos( new Utilisateur(session.getId(), nommodif.getText(), prenommodif.getText(), adressemodif.getText(), "", null, null, null, Integer.parseInt(numeromodif.getText()), null, null, x));
            LoginServices service = new LoginServices();
            session= service.getInformation(session.getId());
            modifanchor.setVisible(false);
            loadDetails();
            JFXSnackbar snack = new JFXSnackbar(details);
            snack.show("Informations mises à jours", 3000);
        }
        else{
            
        }              
    }

    private void loadDetails() {
        nom.setText(session.getEsm());
        adresse.setText(session.getAddresse());
        email.setText(session.getEmail());
        numero.setText(String.valueOf(session.getNumero()));
        date.setText("Inscris depuis : "+String.valueOf((session.getDateInscription())));
        
        
        AnnonceServices a = new AnnonceServices();
        annonces.setText(String.valueOf(a.getNbrMesAnnoncesLAF(session.getId())));
        AchatService s =AchatService.getAchatService();
        achats.setText(String.valueOf(s.nombreAchat(session.getId())));
        AnnonceAdoptionService aas = new AnnonceAdoptionService();
        mesoffresadoption.setText(String.valueOf(aas.nombreMesOffres(session.getId())));
        ReclamationServices rs = new ReclamationServices();
        reclamations.setText(String.valueOf(rs.nombreMesReclamations(session.getId())));
        
        AnnonceSittingServices ass = new AnnonceSittingServices();
        sitting.setText(String.valueOf(ass.nombreMesAnnonces(session.getId())));
        AnnonceAccouplementServices annaccser = new AnnonceAccouplementServices();
        mesaccouplements.setText(String.valueOf(annaccser.nombreMesAnnonces(session.getId())));
    }

    @FXML
    private void goToMesOffresAdoption(MouseEvent event) {
        try {                    
            loadSplashScreen("/paw/annonceadoption/FXMLmesoffresadoption.fxml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void annulation(ActionEvent event) {
        modifanchor.setVisible(false);
    }

    @FXML
    private void goToMesAccouplements(MouseEvent event) {
        try {
           
            loadSplashScreen("/paw/annonceAccouplements/user/FXMLListeAnnonceAccouplement.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToSitting(MouseEvent event) {
        try {
           
            loadSplashScreen("/paw/sittingService/FXMLSittingListeAnnonce.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
