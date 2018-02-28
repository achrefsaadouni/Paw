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
import static Utility.Checksum.genererCode;
import Utility.Mail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        chaine="";
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
        
        
        
        ////////////////////
        /////validators/////
        ///////////////////
        
        NumberValidator nv = new NumberValidator();
        nv.setMessage("Veuillez saisir un numéro valide");
        RequiredFieldValidator rf = new RequiredFieldValidator();
        rf.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator rf1 = new RequiredFieldValidator();
        rf1.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator rf2 = new RequiredFieldValidator();
        rf2.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator rf3 = new RequiredFieldValidator();
        rf3.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator rf4 = new RequiredFieldValidator();
        rf4.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator rf5 = new RequiredFieldValidator();
        rf5.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator rf6 = new RequiredFieldValidator();
        rf6.setMessage("Veuillez remplir ce champs");
        RequiredFieldValidator ev = new RequiredFieldValidator();
        ev.setMessage("Veuillez saisir une adresse valide");
        
        nom.getValidators().add(rf);
        nom.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    nom.validate();
                }
            }
        });
        prenom.getValidators().add(rf1);
        prenom.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    prenom.validate();
                }
            }
        });
        rue.getValidators().add(rf2);
        rue.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    rue.validate();
                }
            }
        });
        numero.getValidators().add(nv);
        numero.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    numero.validate();
                }
            }
        });
        username.getValidators().add(rf3);
        username.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    username.validate();
                }
            }
        });
        password.getValidators().add(rf4);
        password.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    password.validate();
                }
            }
        });
        email.getValidators().add(ev);
        email.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    email.validate();
                }
            }
        });
        confirmer.getValidators().add(rf5);
        confirmer.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (!confirmer.getText().equals(confirmer.getText()))
                    confirmer.validate();
                }
            }
        });
        
        
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
            String filePath = "c:\\wamp64\\www\\paw\\web\\images\\pawUsers\\"+imageName + extension;
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
        if (chaine.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image pour votre profile ", ButtonType.CLOSE);
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
                isInteger(numero) &&    
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
                            String code ="";
                            try {
                                code = genererCode();
                            } catch (Exception ex) {
                                Logger.getLogger(FXMLinscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Utilisateur p = new Utilisateur(
                                    0, 
                                    nom.getText(), 
                                    prenom.getText(), 
                                    ville.getValue().toUpperCase()+", "+rue.getText(), 
                                    email.getText(), 
                                    username.getText(), 
                                    password.getText(), 
                                    "Membre", 
                                    sexe,
                                    Integer.parseInt(numero.getText()), 
                                    chaine, 
                                    null,                    
                                    "Free"+code,
                                    "no");
                            s.insererUtilisateur(p);
                            Mail.send(email.getText(), "Confirmation de l'adresse Email", "Bienvenue à la famille Paw : \n Pour Confimer votre adresse email veuillez utiliser ce code : " + code + "\n");
                            

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
                nom.validate();
                prenom.validate();
                password.validate();
                confirmer.validate();
                rue.validate();
                //ville.validate();
                System.out.println("*"+ville.getValue()+"*");
                numero.validate();  
                username.validate();
                email.validate();
                
//                JFXSnackbar snack = new JFXSnackbar(anchor);
//                snack.show("Veuillez remplir tout les champs", 1000);
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
    
    public boolean isInteger(JFXTextField input) {
        try {
            int prix = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    
    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    @FXML
    private void seconnecterAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
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
