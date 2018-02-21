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
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import Utility.PaymentOrder;

public class FXMLPayerController {

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
    String code = "";
    String recepteur = session.getEmail();
    @FXML
    private StackPane stripe_form;
    @FXML
    private JFXButton confirmer;
    @FXML
    private JFXButton fermer;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField etat;
    @FXML
    private JFXTextField code_postal;
    @FXML
    private JFXTextField numero;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField nom_card;
    @FXML
    private JFXTextField numero_carte;
    @FXML
    private JFXTextField cvv;
    @FXML
    private JFXDatePicker date_expiration;
    @FXML
    private JFXDialogLayout layout;

    void annuler(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) throws
            Exception {

        if (Panier.panier.isEmpty()) {
            MyNotifications.ErrorNotification("Panier", "Votre Panier est encore vide");
        } else {

            if (livraison.isSelected()) {
                validation.setVisible(true);
                code = genererCode();
                System.out.println(code);
                Mail.send(recepteur, code);
            } else if (stripe.isSelected()) {

                JFXDialogLayout content = new JFXDialogLayout();
                content.setBody(layout);
                JFXDialog dialog = new JFXDialog(stripe_form, content, JFXDialog.DialogTransition.CENTER);
                stripe_form.setVisible(true);
                dialog.show();
                dialog.setOnDialogClosed((even) -> {
                    stripe_form.setVisible(false);
                });
                fermer.setOnAction((eve) -> {
                    dialog.close();
                    stripe_form.setVisible(false);
                });

            }
        }
    }

    void initialize() {

        validation.setVisible(false);
    }

    @FXML
    private void entrer(ActionEvent event) {

        Achat achat = new Achat(session.getId(), Panier.prixtotal(), Panier.panier);
        if (code.equals(valid_code.getText())) {
            achat.setEtat("Non Payer");
            if (achatservice.addAchat(achat)) {
                MyNotifications.infoNotification("Achat", "Votre Achat a eté effectué avec succes ");
                Panier.panier.clear();
            }
        } else {
            MyNotifications.ErrorNotification("Confirmation", "Code D'activation Incorrect");
        }
    }

    @FXML
    private void renevoyer(ActionEvent event) throws
            Exception {
        code = genererCode();
        System.out.println(code);
        Mail.send(recepteur, code);
    }

    @FXML
    private void confirmer(ActionEvent event) {
            java.sql.Date datea = java.sql.Date.valueOf(date_expiration.getValue());
            String tt = String.valueOf(datea);
            String year = tt.substring(0, 4);
            String month = tt.substring(5, 7);
           try {
            PaymentOrder po = new PaymentOrder(numero_carte.getText(), cvv.getText(), month, year,Panier.prixtotal(), email.getText(), ville.getText(), etat.getText(), adresse.getText(), "tunisia", code_postal.getText());
          Charge a =po.createCharge("sk_test_s3qNFSFh0IqhB0vaSTwTe9n8",po.getAmmount(),nom_card.getText(), po.getCardnumber(), po.getExp_month(), po.getExp_year(), po.getCvv(), po.getAddress(), po.getCity(), po.getState(), po.getCountry(), po.getZip(), po.getEmail());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Operation Reussite", ButtonType.CLOSE);
            alert.show();
            
            if(a.getStatus().equals("succeeded"))
            {
               stripe_form.setVisible(false);
               Panier.panier.clear();
            }
        } catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException ex) {
           System.out.println(ex);
        } catch (CardException ex) {
            System.out.println(ex.getCode());
        }
        
        
    }

}
