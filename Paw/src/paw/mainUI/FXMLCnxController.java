/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.Panier;
import Entity.Utilisateur;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static paw.Paw.session;

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
    private ImageView settings;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(session.getUsername());
        email.setText(session.getEmail());
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        try {
            AnchorPane menu = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLMenu.fxml"));
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
                            case "AnnoncePerdus": {
                                try {
                                    loadSplashScreen("/paw/annonceperdu/user/FXMLListeAnnoncesPerdus.fxml");
                                    break;
                                } catch (Exception ex) {
                                    Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            case "veterinaire": {
                                try {
                                    // AnchorPane pane = FXMLLoader.load(getClass().getResource("/paw/veterinaires/FXMLVeterinaires.fxml"));
                                    // window.getChildren().setAll(pane);
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
                            case "gerer_produit": {
                                try {

                                    loadSplashScreen("/paw/boutique/admin/produit/FXMLAjouter.fxml");
                                    break;
                                } catch (Exception ex) {
                                    Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            case "gerer_achat": {
                                try {
                                    loadSplashScreen("/paw/boutique/admin/achat/FXMLAchat.fxml");
                                    break;
                                } catch (Exception ex) {
                                    Logger.getLogger(FXMLCnxController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                    });
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

    }

    public void initialisation(Utilisateur x) {
        session = x;
    }

    @FXML
    private void goToProfile(MouseEvent event) throws IOException {
        loadSplashScreen("/paw/profile/FXMLprofile.fxml");
    }

    @FXML
    private void goToNotification(MouseEvent event) {
    }

    @FXML
    private void goToSettings(MouseEvent event) {
    }

    @FXML
    private void goToChat(MouseEvent event) {
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
        try {
            AnchorPane pa = FXMLLoader.load(getClass().getResource("/paw/mainUI/FXMLPanier.fxml"));
            chart.getChildren().setAll(pa);
        } catch (IOException ex) {
            System.out.println("erreur affichage");
        }
        if (chart.isVisible()) {
            chart.setVisible(false);
        } else {
            chart.setVisible(true);
        }

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

}
