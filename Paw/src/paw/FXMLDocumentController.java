/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw;

import Entity.Connexion;
import Service.ConnexionServices;
import Service.LoginServices;
import com.jfoenix.controls.JFXButton;
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
  
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        usern.setText("ayoubelkadhi");
        passw.setText("12345678");
    }    

    @FXML
    private void connexionButton(ActionEvent event) throws IOException {
        LoginServices service = new LoginServices();
        int x =service.Valide(usern.getText(), passw.getText());
        if(x!=-1 && x!=0)
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
        else{
            
            
        }
    }

    @FXML
    private void inscriptionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
//        UtilisateurServices o = new UtilisateurServices();
//                o.utilisateurMois(0);
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

    
}
