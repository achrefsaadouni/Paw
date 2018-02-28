/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw;

import Entity.Connexion;
import Service.ConnexionServices;
import Service.LoginServices;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import paw.mainUI.FXMLCnxController;
import paw.mainUI.FXMLinscriptionController;

/**
 *
 * @author AYOUB
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField usern;
    @FXML
    private JFXPasswordField passw;
    @FXML
    private ImageView close;
    @FXML
    private StackPane stackpane;
  
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void connexionButton(ActionEvent event) throws IOException {
        LoginServices service = new LoginServices();
        int x =service.Valide(usern.getText(), passw.getText());
        if(x!=-1 && x!=0 && x!=-2)
        {
            closeStage();
            Paw.session=service.getInformation(x);
            ConnexionServices s=new ConnexionServices();
            if(s.existe(x))
            {
                s.updateConnexion(x);
            }
            else
            {
                s.inserer(new Connexion(x));
            }
            loadMain();  
        }
        else if ( x == 0)
        {
            // information fausse
            messageInformationFausses();
        }
        else if ( x == -1)
        {
            // Erreur, réesseyée plus tard
            messageErreur();
        }
        else
        {
            // Utilisateur bloqué
            messageIsBloqued();
            
        }
    }

    @FXML
    private void inscriptionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/paw/mainUI/FXMLinscription.fxml"));
        loader.load();
        FXMLinscriptionController cnt = loader.getController();
        
        
        Parent root = loader.getRoot();
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        closeStage();
    }

    private void closeStage() {
        ((Stage)usern.getScene().getWindow()).close();
    }

    private void loadMain() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/paw/mainUI/FXMLDocument.fxml"));
        loader.load();
        FXMLCnxController cnt = loader.getController();
        
        
        Parent root = loader.getRoot();
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    private void messageInformationFausses() {
        stackpane.setMouseTransparent(false);
        JFXDialogLayout contentConfirmation =new JFXDialogLayout();

        contentConfirmation.setHeading(new Text("Informatons invalides"));
        contentConfirmation.setBody(new Text("Veuillez vérifier votre nom d'utilisateur et votre \n"
                                            + "mot de passe"));

        JFXDialog dialog = new JFXDialog(stackpane, contentConfirmation, JFXDialog.DialogTransition.TOP);

        JFXButton ok = new JFXButton("Annuler");
        ok.setOnAction((event) -> {
            dialog.close();
            stackpane.setMouseTransparent(true);
        });
        stackpane.setMouseTransparent(false);
        contentConfirmation.setActions(ok);
        dialog.show();
    }

    private void messageErreur() {
        stackpane.setMouseTransparent(false);
        JFXDialogLayout contentConfirmation =new JFXDialogLayout();

        contentConfirmation.setHeading(new Text("Erreur"));
        contentConfirmation.setBody(new Text("Le serveur semble être hors de porté."));

        JFXDialog dialog = new JFXDialog(stackpane, contentConfirmation, JFXDialog.DialogTransition.TOP);

        JFXButton ok = new JFXButton("Annuler");
        ok.setOnAction((event) -> {
            dialog.close();
            stackpane.setMouseTransparent(true);
        });

        stackpane.setMouseTransparent(false);
        contentConfirmation.setActions(ok);
        dialog.show();
    }

    private void messageIsBloqued() {
        stackpane.setMouseTransparent(false);
        JFXDialogLayout contentConfirmation =new JFXDialogLayout();

        contentConfirmation.setHeading(new Text("Votre compte est bloqué"));
        contentConfirmation.setBody(new Text("Votre compte a été bloqué, si vous pensez que ceci \n"
                                            + "est une erreur vous pouvez contacter notre equipe \n"
                                            + " à travers l'adresse : pawzcorporation@gmail.com"));

        JFXDialog dialog = new JFXDialog(stackpane, contentConfirmation, JFXDialog.DialogTransition.TOP);

        JFXButton ok = new JFXButton("Annuler");
        ok.setOnAction((event) -> {
            dialog.close();
            stackpane.setMouseTransparent(true);
        });
        stackpane.setMouseTransparent(false);
        contentConfirmation.setActions(ok);
        dialog.show();
    }

    
}
