/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.LigneAchat;
import Entity.Panier;
import Entity.Utilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
                                    loadSplashScreen("/paw/annonceperdu/user/FXMLinterfacePrincipalPerdu.fxml");
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
        
        initpanier();

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

    
    
    
    
    
    
    
    
    
      public void initpanier(){
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
     try{  
            loadSplashScreen("/paw/boutique/user/Payer/FXMLPayer.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

}
