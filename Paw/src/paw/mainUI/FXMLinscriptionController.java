/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.Connexion;
import Entity.Utilisateur;
import Service.ConnexionServices;
import Service.InscriptionService;
import Service.LoginServices;
import Service.ProduitService;
import Service.UtilisateurServices;
import Utility.Checksum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import paw.FXMLDocumentController;
import paw.Paw;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLinscriptionController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numero;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private JFXTextField rue;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    @FXML
    private JFXRadioButton homme;
    @FXML
    private JFXRadioButton femme;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmer;
    @FXML
    private AnchorPane anchor;
    
    private File file;
    private String chaine ;
    @FXML
    private ImageView close;
    @FXML
    private JFXButton seconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup groupsexe = new ToggleGroup();
        homme.setToggleGroup(groupsexe);
        femme.setToggleGroup(groupsexe);
        homme.setSelectedColor(Color.rgb(51, 122, 183));
        femme.setSelectedColor(Color.rgb(51, 122, 183));
        ObservableList<String> items = FXCollections.observableArrayList(
            "Ariana","Beja","Ben Arous","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Le Kef",
            "Mahdia","La Manouba","Medenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine",
            "Tozeur","Tunis","Zaghouan");
        ville.setItems(items);
        JFXSnackbar snack = new JFXSnackbar(anchor);
        
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
                   
           
        try {
            String imageName = Checksum.createChecksum(file.getAbsolutePath());
            String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
            String filePath = "c:\\wamp64\\www\\pawUsers\\"+imageName + extension;
            chaine =imageName + extension;
            System.out.println(chaine);
            File dest = new File(filePath);
            Files.copy(
                    file.toPath(), 
                    dest.toPath(), 
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            Logger.getLogger(FXMLinscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
        
    }

    @FXML
    private void inscriptionButton(ActionEvent event) throws IOException {
        if (!file.exists()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image ou bien des  donnes concernant l'annonce ", ButtonType.CLOSE);
            alert.show();
            upload.requestFocus();
        }
        else
        {
            if (!nom.getText().equals("") &&
                !prenom.getText().equals("") &&
                !password.getText().equals("") && 
                !confirmer.getText().equals("") &&
                !rue.getText().equals("") &&
                !ville.getValue().equals("") &&    
                !numero.getText().equals("") &&    
                !email.getText().equals("") &&
                !username.getText().equals("")
                )
            {
                InscriptionService service = new InscriptionService();
                if (service.EmailNonExistant(email.getText()))
                {
                    if (service.UsernameNonExistant(username.getText()))
                    {
                        if(password.getText().equals(confirmer.getText()))
                        {
                            UtilisateurServices s = new UtilisateurServices();
                            String sexe="Homme";
                            if (femme.isSelected())
                            {
                                sexe="Femme";
                            }
                            Utilisateur p = new Utilisateur(0, nom.getText(), prenom.getText(), ville.getValue().toUpperCase()+", "+rue.getText(), email.getText(), username.getText(), password.getText(), "Membre", Integer.parseInt(numero.getText()), chaine, null, sexe);
                            s.insererUtilisateur(p);
                            Notifications.create().text("Un code de confirmation à été envoyé à "+p.getEmail()).title("Bienvenue chez Paw").show();
                            LoginServices servicelogin = new LoginServices();
                            int x =servicelogin.Valide(username.getText(), password.getText());
                            if(x!=-1 && x!=0)
                            {
                                closeStage();
                                Paw.session=servicelogin.getInformation(x);
                                ConnexionServices sCnx=new ConnexionServices();
                                if(sCnx.existe(x))
                                {
                                    sCnx.updateConnexion(x);
                                }
                                else
                                {
                                    sCnx.inserer(new Connexion(x));
                                }
                                loadMain();  
                            }
                                nom.setText("");
                                prenom.setText("");
                                password.setText("");
                                confirmer.setText("");
                                rue.setText("");
                                ville.setValue("") ;    
                                numero.setText(""); 
                                email.setText("");
                                username.setText("");  
                         }
                         else
                         {
                             JFXSnackbar snack = new JFXSnackbar(anchor);
                             snack.show("Password different de la confirmation", 1000);
                         }
                    }
                    else
                    {
                        JFXSnackbar snack = new JFXSnackbar(anchor);
                        snack.show("Username deja existant", 1000);
                    }
                }
                else
                {
                    JFXSnackbar snack = new JFXSnackbar(anchor);
                    snack.show("Email deja existant", 1000);

                }
            }
            else
            {
                JFXSnackbar snack = new JFXSnackbar(anchor);
                snack.show("Veuillez remplir tout les champs", 1000);
            }
        }
            
    }
    
        private void closeStage() {
        ((Stage)password.getScene().getWindow()).close();
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

    @FXML
    private void seconnecterAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
//        UtilisateurServices o = new UtilisateurServices();
//                o.utilisateurMois(0);
        loader.setLocation(getClass().getResource("/paw/FXMLDocument.fxml"));
        loader.load();
        FXMLDocumentController cnt = loader.getController();
        
        
        Parent root = loader.getRoot();
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        closeStage();
    }
}
