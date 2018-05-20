/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import Entity.LigneAchat;
import Entity.Panier;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import paw.MyNotifications;
import paw.profile.FXMLprofileController;

/**
 * FXML Controller class
 *
 * @author vinga
 */
public class FXMLPanierController  extends FXMLCnxController {

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

    public void initialize() {

        initpanier();
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
                Image img = new Image("http://localhost/paw_web/web/images/pawBoutique/" + achat.getProduit().getImage1());
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
            loadSplashScreen("/paw/boutique/user/achat/FXMLPayer.fxml");
        } catch (Exception ex) {
            Logger.getLogger(FXMLprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
