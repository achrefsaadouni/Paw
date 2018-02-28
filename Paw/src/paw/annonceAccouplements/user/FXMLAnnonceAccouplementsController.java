/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceAccouplements.user;

import Entity.AnnonceAccouplement;
import Service.AnnonceAccouplementServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import paw.MyNotifications;
import paw.Paw;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLAnnonceAccouplementsController implements Initializable {

    @FXML
    private Label nom11;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXTextField raceInsertion;
    @FXML
    private JFXTextField ageInsertion;
    @FXML
    private JFXTextField couleurInsertion;
    @FXML
    private ChoiceBox<String> choixInsertion;
    @FXML
    private ChoiceBox<String> poilInsertion;
    @FXML
    private JFXRadioButton vouiInsertion;
    @FXML
    private JFXRadioButton vnonInsertion;
    @FXML
    private JFXRadioButton douiInsertion;
    @FXML
    private JFXRadioButton dnonInsertion;
    @FXML
    private JFXTextField msgInsertion;
    private File file;
    @FXML
    private JFXTextField lieu;

    private String chaine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choixInsertion.getItems().setAll("Chien", "Chat", "Autres");
        choixInsertion.setValue("Chien");

        poilInsertion.getItems().setAll("Nus", "Sans sous-poil", "Double pelage", "Poils courts", "Poils longs");
        poilInsertion.setValue("Nus");

        ToggleGroup groupi = new ToggleGroup();
        ToggleGroup groupm = new ToggleGroup();

        ToggleGroup groupvi = new ToggleGroup();
        ToggleGroup groupvm = new ToggleGroup();

        ToggleGroup groupdi = new ToggleGroup();
        ToggleGroup groupdm = new ToggleGroup();

        male.setToggleGroup(groupm);
        female.setToggleGroup(groupm);

        vouiInsertion.setToggleGroup(groupvi);
        vnonInsertion.setToggleGroup(groupvi);

        douiInsertion.setToggleGroup(groupdi);
        dnonInsertion.setToggleGroup(groupdi);

    }

    @FXML
    private void actionInsertion2(ActionEvent event) {
        if ((couleurInsertion.getText().trim().equals("")) || (ageInsertion.getText().trim().equals(""))
                || (raceInsertion.getText().trim().equals("")) || (msgInsertion.getText().trim().equals("")) || (choixInsertion.getValue().isEmpty())
                || (lieu.getText().trim().equals(""))) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("erreur");
            fail.setContentText("Vous avez oublier de remplir un champs");
            fail.showAndWait();
        } else {
            if (file == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image ou bien des  donnes concernant l'annonce ", ButtonType.CLOSE);
                alert.show();
                upload.requestFocus();
            } else {

                AnnonceAccouplementServices as = new AnnonceAccouplementServices();
                String sexe = "Male";
                if (female.isSelected()) {
                    sexe = "Female";
                }

                String vaccin = "OUI";
                if (vnonInsertion.isSelected()) {
                    vaccin = "NON";
                }

                String dossier = "OUI";
                if (dnonInsertion.isSelected()) {
                    dossier = "NON";
                }

                if ((!isInteger(ageInsertion))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Le champs age doit être un entier", ButtonType.CLOSE);
                    alert.show();
                } else {
                    as.insererAnnonceAccouplement(new AnnonceAccouplement(
                            poilInsertion.getValue(),
                            vaccin,
                            dossier,
                            lieu.getText(),
                            0,
                            Integer.parseInt(ageInsertion.getText()),
                            couleurInsertion.getText(),
                            sexe,
                            raceInsertion.getText(),
                            msgInsertion.getText(),
                            choixInsertion.getValue(),
                            null,
                            file,
                            Paw.session.getId()));

                    poilInsertion.setValue("Nus");
                    ageInsertion.setText("");
                    raceInsertion.setText("");
                    msgInsertion.setText("");
                    couleurInsertion.setText("");
                    choixInsertion.setValue("Chien");
                    lieu.setText("");
                    MyNotifications.infoNotification("Ajout", "Votre annonce a été ajouté avec Succès");
                }

            }

        }

    }

    @FXML
    private void fileChoosing(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            Image im = new Image("file:///" + file.toPath().toString());
            imajout1.setImage(im);

        }
    }

    public boolean isInteger(JFXTextField input) {
        try {
            int a = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
