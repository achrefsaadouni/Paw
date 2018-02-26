/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.LigneAchat;
import Entity.Panier;
import Entity.Utilisateur;
import Service.AchatService;
import Service.AnnonceAccouplementServices;
import Service.AnnonceAdoptionService;
import Service.AnnoncePerduServices;
import Service.AnnonceTrainingServices;
import Service.AnnonceTrouveServices;

import Service.ConseilServices;
import Service.ProduitService;
import Service.ReclamationServices;
import Service.UtilisateurServices;
import Service.VeterinaireServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import paw.FXMLDocumentController;
import paw.MyNotifications;
import static paw.Paw.session;
import paw.profile.FXMLprofileController;

/**
 *
 * @author AYOUB
 */
public class FXMLCnxController implements Initializable {
   
    @FXML
    public AnchorPane window;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane chart;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView notification;
    @FXML
    private ImageView chat;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private ImageView panier;
    @FXML
    private ImageView close;

    @FXML
    private TreeTableColumn<LigneAchat, ImageView> imagePanier;
    @FXML
    private TreeTableColumn<LigneAchat, JFXButton> PlusPanier;
    @FXML
    private TreeTableColumn<LigneAchat, Number> quantite;
    @FXML
    private TreeTableColumn<LigneAchat, JFXButton> MinusPanier;
    @FXML
    private TreeTableColumn<LigneAchat, JFXButton> cancel;
    @FXML
    private JFXTreeTableView<LigneAchat> paniertree;
    @FXML
    private JFXButton payerPanier;
    @FXML
    private ImageView fb;
    @FXML
    private ImageView insta;
    @FXML
    private ImageView twit;
    @FXML
    private AnchorPane admin_window;
    @FXML
    private Label nom1;
    @FXML
    private Label achat_livrer;
    @FXML
    private Label achat_payer;
    @FXML
    private Label achat_nonpayer;
    @FXML
    private Label nom11;
    @FXML
    private Label adoption;
    @FXML
    private Label animal_perdu;
    @FXML
    private Label animal_trouve;
    @FXML
    private Label nom111;
    @FXML
    private Label nbr_Reclamation;
    @FXML
    private Label nbr_veterinaire;
    @FXML
    private Label nbr_conseille;
    @FXML
    private Label nbr_produit;
    @FXML
    private Label nbr_produit_out;
    @FXML
    private Label nbr_utilisateur;

    AchatService achatservice = AchatService.getAchatService();
    ProduitService produitservice = ProduitService.getProduitService();
    ReclamationServices reclamationservice = new ReclamationServices();
    ConseilServices conseilservice = new ConseilServices();
    UtilisateurServices utilisateurservice = new UtilisateurServices();
    VeterinaireServices veterinaireservice = new VeterinaireServices();
    AnnonceAdoptionService adoptionservice = new AnnonceAdoptionService();
    AnnoncePerduServices perduservice = new AnnoncePerduServices();
    AnnonceTrouveServices trouveservice = new AnnonceTrouveServices();
    AnnonceTrainingServices trainingservice = new AnnonceTrainingServices();
    
    AnnonceAccouplementServices accouplementservice = new AnnonceAccouplementServices();
    int n;
    @FXML
    private Label training;
    @FXML
    private Label walking;
    @FXML
    private Label accouplement;
    @FXML
    private ImageView boutque;
    @FXML
    private ImageView AnnoncePerdu;
    @FXML
    private ImageView veterinaire;
    @FXML
    private ImageView AnnonceTrouve;
    @FXML
    private JFXButton back;
    @FXML
    private Text feature;
    @FXML
    private JFXButton back1;
    @FXML
    private AnchorPane barre;
    @FXML
    private AnchorPane mainwindow;
    @FXML
    private ImageView logout;
    @FXML
    private StackPane stackpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(session.getUsername());
        email.setText(session.getEmail());
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        try {
            AnchorPane menu = null;
            if ("Admin".equals(session.getRole())) {
                menu = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLAdmin.fxml"));

                menu.setOnMouseClicked(e -> {
                    if (drawer.isHidden() || drawer.isHiding()) {
                    } else {
                        transition.setRate(transition.getRate() * -1);
                        transition.play();
                        drawer.toggle();
                    }
                });
                admin_window.setOnMouseClicked(e -> {
                    if (drawer.isHidden() || drawer.isHiding()) {
                    } else {
                        transition.setRate(transition.getRate() * -1);
                        transition.play();
                        drawer.toggle();
                    }
                });

                for (Node node : menu.getChildren()) {
                    if (node.getAccessibleText() != null) {
                        node.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                            switch (node.getAccessibleText()) {

                                case "gerer_produit": {
                                    try {

                                        loadSplashScreenAdmin("/paw/boutique/admin/produit/FXMLAjouter.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "gerer_utilisateurs": {
                                    try {

                                        loadSplashScreenAdmin("/paw/ayoubAdmin/utilisateurs/FXMLutilisateurs.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "reclamations": {
                                    try {

                                        loadSplashScreenAdmin("/paw/ayoubAdmin/reclamation/FXMLreclamation.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "statistique": {
                                    try {

                                        loadSplashScreenAdmin("/paw/ayoubAdmin/statistiques/FXMLstatistiques.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "gerer_achat": {
                                    try {
                                        loadSplashScreenAdmin("/paw/boutique/admin/achat/FXMLAchat.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                   case "acceuil": {
                                    try {

                                        acceuil("/paw/mainUI/FXMLDocument.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }
                        });
                    }
                }

            } else {
                menu = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLMenu.fxml"));
                menu.setOnMouseClicked(e -> {
                    if (drawer.isHidden() || drawer.isHiding()) {
                    } else {
                        transition.setRate(transition.getRate() * -1);
                        transition.play();
                        drawer.toggle();
                    }
                });
                window.setOnMouseClicked(e -> {
                    if (drawer.isHidden() || drawer.isHiding()) {
                    } else {
                        transition.setRate(transition.getRate() * -1);
                        transition.play();
                        drawer.toggle();
                    }
                });

                for (Node node : menu.getChildren()) {
                    if (node.getAccessibleText() != null) {
                        node.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                            switch (node.getAccessibleText()) {
                                case "Training": {
                                    try {
                                        loadSplashScreen("/paw/trainingService/FXMLTrainingPrincipal.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "AnnonceTrouvées": {
                                    try {
                                        loadSplashScreen("/paw/annoncetrouvee/user/FXMLinterfacePrincipaleTrouvee.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "AnnonceAdoption": {
                                    try {
                                        loadSplashScreen("/paw/annonceadoption/FXMLliste.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "AnnoncePerdus": {
                                    try {
                                        loadSplashScreen("/paw/annonceperdu/user/FXMLinterfacePrincipalPerdu.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "veterinaire": {
                                    try {
                                        loadSplashScreen("/paw/veterinaires/FXMLVeterinaires.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "boutique": {
                                    try {
                                        loadSplashScreen("/paw/boutique/user/produit/FXMLProduit.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                                
                                 case "sitting": {
                                    try {
                                        loadSplashScreen("/paw/sittingService/FXMLSittingPrincipale.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                                    case "acceuil": {
                                    try {

                                        acceuil("/paw/mainUI/FXMLDocument.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }
                        });
                    }
                }
            }

            drawer.setSidePane(menu);
            drawer.setMouseTransparent(true);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                drawer.toggle();
            });
        } catch (IOException ex) {
            Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("Admin".equals(session.getRole())) {
            initAdmin();
        } else {
            initpanier();
            n = 0;
            carousel(n);
        }
    }

    public void initialisation(Utilisateur x) {
        session = x;
    }

    @FXML
    private void goToProfile(MouseEvent event) throws IOException {
         if ("Admin".equals(session.getRole())) {
             loadSplashScreenAdmin("/paw/profile/FXMLprofile.fxml");
         }
         else 
        loadSplashScreen("/paw/profile/FXMLprofile.fxml");
    }

    @FXML
    private void goToNotification(MouseEvent event) {
    }


    @FXML
    private void goToChat(MouseEvent event) {
        loadSplashScreen("/paw/messagerie/inbox/FXMLView_inbox.fxml");
        
    }

    @FXML
    private void closeDrawer(JFXDrawerEvent event) {
        drawer.setMouseTransparent(true);
    }

    @FXML
    private void openDrawer(JFXDrawerEvent event) {
        drawer.setMouseTransparent(false);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void showpanier(MouseEvent event) {
        if (chart.isVisible()) {
            chart.setVisible(false);
        } else {
            chart.setVisible(true);
        }
        refresh();

    }

    public void loadSplashScreen(String location) {
        try {
            StackPane pane = FXMLLoader.load(getClass().getResource(("/paw/FXMLSplash.fxml")));
            window.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource((location)));
                    window.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
    public void loadSplashScreenAdmin(String location) {
        try {
            StackPane pane = FXMLLoader.load(getClass().getResource(("/paw/FXMLSplash.fxml")));
            admin_window.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource((location)));
                    admin_window.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
    public void acceuil(String location)
    {
         try {
            StackPane pane = FXMLLoader.load(getClass().getResource(("/paw/FXMLSplash.fxml")));
            mainwindow.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource((location)));
                    mainwindow.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void initpanier() {
        paniertree.setEditable(true);
        quantite.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.setValue(achat.getNbr_produit());
            return property;
        });

        imagePanier.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            ImageView im = new ImageView();
            try {
                Image img = new Image("file:///" + achat.getProduit().getImages().get(0).getPath());
                im.setFitHeight(50);
                im.setFitWidth(50);
                im.setImage(img);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            property.set(im);
            return property;
        });

        cancel.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();

            ImageView im = new ImageView();
            try {
                Image img1 = new Image("file:///E:/PIDEV/Paw/Paw/src/Ressource/images/cancel.png");
                im.setFitHeight(20);
                im.setFitWidth(20);
                im.setImage(img1);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            JFXButton supp = new JFXButton("", im);
            supp.setStyle("-fx-background-color:white;");
            supp.setOnAction((ActionEvent e) -> {
                Panier.deleteProduit(achat);
                MyNotifications.infoNotification("Cancel", "LigneAchat Supprimé avec success");
                refresh();
            });

            property.set(supp);
            return property;
        });

        PlusPanier.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();

            ImageView im = new ImageView();
            try {
                Image img1 = new Image("file:///E:/PIDEV/Paw/Paw/src/Ressource/images/add-square-button.png");
                im.setFitHeight(20);
                im.setFitWidth(20);
                im.setImage(img1);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            JFXButton add = new JFXButton("", im);
            add.setStyle("-fx-background-color:white;");
            add.setOnAction((ActionEvent e) -> {
                Panier.plus(achat.getProduit());
                refresh();
            });

            property.set(add);
            return property;
        });

        MinusPanier.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();

            ImageView im = new ImageView();
            try {
                Image img1 = new Image("file:///E:/PIDEV/Paw/Paw/src/Ressource/images/substraction.png");
                im.setFitHeight(20);
                im.setFitWidth(20);
                im.setImage(img1);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            JFXButton add = new JFXButton("", im);
            add.setStyle("-fx-background-color:white;");
            add.setOnAction((ActionEvent e) -> {
                Panier.minus(achat.getProduit());
                refresh();
            });

            property.set(add);
            return property;
        });

        refresh();
    }

    public void refresh() {
        ObservableList<LigneAchat> articles = FXCollections.observableArrayList(Panier.panier);
        TreeItem<LigneAchat> root = new RecursiveTreeItem<>(articles, RecursiveTreeObject::getChildren);
        paniertree.setRoot(root);
        paniertree.setShowRoot(false);
    }

    @FXML
    private void payer(ActionEvent event) {
        try {
            loadSplashScreen("/paw/boutique/user/Payer/FXMLPayer.fxml");
            chart.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void facebookPage(MouseEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com/Boutique-Paw-753236058199700"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    private void twiterPage(MouseEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("https://twitter.com/boutique_paw"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    private void gotoInstagram(MouseEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/pawzcorporation/?hl=fr"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

    }

    public void initAdmin() {

        panier.setVisible(false);
        achat_livrer.setText(String.valueOf(achatservice.nombreAchatlivrer()));
        achat_nonpayer.setText(String.valueOf(achatservice.nombreAchatnonpayer()));
        achat_payer.setText(String.valueOf(achatservice.nombreAchatpayer()));
        adoption.setText(String.valueOf(adoptionservice.nombre()));
        animal_perdu.setText(String.valueOf(perduservice.getAnnoncePe()));
        animal_trouve.setText(String.valueOf(trouveservice.getAnnoncetr()));
        training.setText(String.valueOf(trainingservice.nombre()));

        accouplement.setText(String.valueOf(accouplementservice.nombre()));
        nbr_produit.setText("Produit total : " + String.valueOf(produitservice.nombreProduit()));
        nbr_produit_out.setText("Produit non disponible : " + String.valueOf(produitservice.nombreProduitOut()));
        nbr_Reclamation.setText("Reclamation non traitée : " + String.valueOf(reclamationservice.nombre()));
        nbr_conseille.setText("Conseil Total : " + String.valueOf(conseilservice.nombre()));
        nbr_utilisateur.setText("Membre Total : " + String.valueOf(utilisateurservice.nombre()));
        nbr_veterinaire.setText("Veterinaire Total : " + String.valueOf(veterinaireservice.nombre()));

        admin_window.setVisible(true);

    }

    private void next(ActionEvent event) {

        if (n == 3) {
            n = 0;
        } else {
            n++;
        }
        carousel(n);
    }

    @FXML
    private void goToBoutique(MouseEvent event) {
        try {
            loadSplashScreen("/paw/boutique/user/produit/FXMLProduit.fxml");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void back(ActionEvent event) {

        if (n == 0) {
            n = 3;
        } else {
            n--;
        }
        carousel(n);
    }

    @FXML
    private void goToAnnonceP(MouseEvent event) {

        try {
            loadSplashScreen("/paw/annonceperdu/user/FXMLinterfacePrincipalPerdu.fxml");

        } catch (Exception ex) {
            Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToVet(MouseEvent event) {

        try {
            loadSplashScreen("/paw/veterinaires/FXMLVeterinaires.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAnnonceTR(MouseEvent event) {

        try {
            loadSplashScreen("/paw/annoncetrouvee/user/FXMLinterfacePrincipaleTrouvee.fxml");

        } catch (Exception ex) {
            Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carousel(int nbr) {
        switch (nbr) {
            case 0:
                boutque.setVisible(true);
                AnnoncePerdu.setVisible(false);
                AnnonceTrouve.setVisible(false);
                veterinaire.setVisible(false);
                feature.setText("Accedez A Notre Boutique Depuis Votre Ordinateur ");
                break;
            case 1:
                boutque.setVisible(false);
                AnnoncePerdu.setVisible(true);
                AnnonceTrouve.setVisible(false);
                veterinaire.setVisible(false);
                feature.setText("Vous galérez a trouver votre animal Poser une annonce pour le retrouver");

                break;
            case 2:
                boutque.setVisible(false);
                AnnoncePerdu.setVisible(false);
                AnnonceTrouve.setVisible(true);
                veterinaire.setVisible(false);
                feature.setText("Vous Avez Trouvé un animal perdu ,aidez son proprietaire a le retrouvez");

                break;
            default:
                boutque.setVisible(false);
                AnnoncePerdu.setVisible(false);
                AnnonceTrouve.setVisible(false);
                veterinaire.setVisible(true);
                feature.setText("Vous cherchez un bon véterinaire prés de vous nous pouvant vous aidez");

                break;
        }
    }

    @FXML
    private void logoutAction(MouseEvent event) {
        JFXDialogLayout content =new JFXDialogLayout();
        content.setHeading(new Text("Déconnexion"));
        content.setBody(new Text("Êtes-vous sûr de vouloir vous déconnecter ?"));
        
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.TOP);
        
        JFXButton oui = new JFXButton("Se déconnecter");
        oui.setOnAction((e) -> {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/paw/FXMLDocument.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FXMLDocumentController cnt = loader.getController();


            Parent root = loader.getRoot();
            Stage stage=new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            closeStage();
            
            session=null;
            
        });
        
        JFXButton non = new JFXButton("Annuler");
        non.setOnAction((e) -> {
            dialog.close();
            stackpane.setMouseTransparent(true);
        });
        
        stackpane.setMouseTransparent(false);
        content.setActions(oui,non);
        dialog.show();
    }

    private void closeStage() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
