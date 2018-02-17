package paw.boutique.user.Payer;

import Entity.Achat;
import Entity.Panier;
import Service.AchatService;
import static Utility.Checksum.genererCode;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import paw.MyNotifications;
import static paw.Paw.session;
import Utility.Mail;

public class FXMLPayerController{


    @FXML
    private JFXRadioButton stripe;

    @FXML
    private ToggleGroup p;


    @FXML
    private JFXRadioButton livraison;

    @FXML
    private JFXButton valider;

    @FXML
    private Pane validation;
    @FXML
    private JFXTextField valid_code;
    @FXML
    private JFXButton valid_btn;
    @FXML
    private JFXButton valid_btn1;

    AchatService achatservice = AchatService.getAchatService();
    String code="";
    String recepteur = session.getEmail();
    
    void annuler(ActionEvent event) {
          
    }

    @FXML
    void valider(ActionEvent event) throws
            Exception{
       
       if (Panier.panier.isEmpty())
        {
            MyNotifications.ErrorNotification("Panier", "Votre Panier est encore vide");
        }
        else{
        
        if(livraison.isSelected())
        {   
            validation.setVisible(true);
            code = genererCode();
            System.out.println(code);
            Mail.send(recepteur,code);
        }
        }
    }

    void initialize() {
        
        validation.setVisible(false);
    }

    @FXML
    private void entrer(ActionEvent event) {
        
        Achat achat = new Achat (session.getId(),Panier.prixtotal(),Panier.panier);
            if (code.equals(valid_code.getText()))
            {achat.setEtat("Non Payer");
            if (achatservice.addAchat(achat))  
            {MyNotifications.infoNotification("Achat", "Votre Achat a eté effectué avec succes ");}
            }
            else 
            {
                MyNotifications.ErrorNotification("Confirmation", "Code D'activation Incorrect");
            }
    }

    @FXML
    private void renevoyer(ActionEvent event)  throws
            Exception{
        code = genererCode();
        System.out.println(code);
       Mail.send(recepteur,code);
    }
    
    
}


