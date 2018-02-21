/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.boutique.admin.achat;

import Entity.Achat;
import Entity.LigneAchat;
import Entity.Utilisateur;
import Service.AchatService;
import Service.LigneAchatService;
import Service.UtilisateurServices;
import Utility.Pdf;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.List;
import java.util.function.Predicate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import paw.MyNotifications;

public class FXMLAchatController {

    List<Achat> liste_achat;
    @FXML
    private JFXTextField recherche_achat;

    @FXML
    private JFXTreeTableView<Achat> achat;

    @FXML
    private TreeTableColumn<Achat, String> nom_prenom;

    @FXML
    private TreeTableColumn<Achat, Number> prixTotal;

    @FXML
    private TreeTableColumn<Achat, String> date;

    @FXML
    private TreeTableColumn<Achat, String> status;

    @FXML
    private JFXTextField rechercher_ligneachat;

    @FXML
    private JFXTreeTableView<LigneAchat> ligneachat;

    @FXML
    private TreeTableColumn<LigneAchat, ImageView> image;

    @FXML
    private TreeTableColumn<LigneAchat, String> libelle;

    @FXML
    private TreeTableColumn<LigneAchat, Number> nombre_achat;
    @FXML
    private TreeTableColumn<Achat, String> adresse;

    @FXML
    private TreeTableColumn<Achat, String> numero;

    @FXML
    private TreeTableColumn<LigneAchat, Number> prixunite;
    @FXML
    StackPane info;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton pdf;
    @FXML
    private JFXButton livrer;
    AchatService achatservice = AchatService.getAchatService();
    @FXML
    private StackPane stackpane;
    @FXML
    private JFXDialogLayout layout;
    @FXML
    private JFXButton annuler;
    @FXML
    private JFXButton ok_supprimer;

    public void initialize() {

        info.setVisible(true);
        ligneachat.setVisible(false);
        rechercher_ligneachat.setVisible(false);
        initAchat();
        supprimer.setVisible(false);
        pdf.setVisible(false);
        livrer.setVisible(false);
    }

    public void initAchat() {
        liste_achat = achatservice.All();
        AchatService achatservice = AchatService.getAchatService();
        UtilisateurServices p = new UtilisateurServices();

        nom_prenom.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Achat achat = (Achat) param.getValue().getValue();
            Utilisateur u = null;
            u = p.rechercher(achat.getId_client());
            property.set(u.getNom() + " " + u.getPrenom());
            return property;
        });

        date.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Achat achat = (Achat) param.getValue().getValue();
            property.setValue(achat.getDate_achat().toString());
            return property;
        });
        status.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Achat achat = (Achat) param.getValue().getValue();
            property.setValue(achat.getEtat());
            return property;
        });

        prixTotal.setCellValueFactory(param -> {
            SimpleDoubleProperty property = new SimpleDoubleProperty();
            Achat achat = (Achat) param.getValue().getValue();
            property.set(achat.getPrix());
            return property;
        });

        adresse.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Achat achat = (Achat) param.getValue().getValue();
            Utilisateur u = null;
            u = p.rechercher(achat.getId_client());
            property.set(u.getAddresse());
            return property;
        });
        numero.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Achat achat = (Achat) param.getValue().getValue();
            Utilisateur u = null;
            u = p.rechercher(achat.getId_client());
            property.set(String.valueOf(u.getNumero()));
            return property;
        });

        achat.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                consulter(oldSelection.getValue().getList());
            } else {
                consulter(newSelection.getValue().getList());
            }
            supprimer.setVisible(true);
            pdf.setVisible(true);
            livrer.setVisible(true);
        });
        
        
        
            recherche_achat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                achat.setPredicate(new Predicate<TreeItem<Achat>>() {
                    @Override
                    public boolean test(TreeItem<Achat> t) {
                        UtilisateurServices p = new UtilisateurServices();
                        Utilisateur u = p.rechercher(t.getValue().getId_client());
                        boolean flag = (u.getNom().concat(u.getPrenom())).toUpperCase().contains(newValue.toUpperCase()) || String.valueOf(t.getValue().getPrix()).equals(newValue) || String.valueOf(t.getValue().getDate_achat()).equals(newValue) || t.getValue().getEtat().contains(newValue)||u.getAddresse().toUpperCase().contains(newValue.toUpperCase())||String.valueOf(u.getNumero()).equals(newValue);
                        return flag;
                    }
                });
            }
        });
        
        
        
        
        refresh();

    }

    public void refresh() {
        liste_achat = achatservice.All();
        ObservableList<Achat> achats = FXCollections.observableArrayList(liste_achat);
        TreeItem<Achat> root = new RecursiveTreeItem<>(achats, RecursiveTreeObject::getChildren);
        achat.setRoot(root);
        achat.setShowRoot(false);
    }

    public void consulter(List<LigneAchat> liste) {

        LigneAchatService achatservice = LigneAchatService.getLigneService();
        image.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat ligne = (LigneAchat) param.getValue().getValue();
            ImageView im = new ImageView();
            try {
                Image img = new Image("file:///" + ligne.getProduit().getImages().get(0).getPath().toString());
                im.setFitHeight(100);
                im.setFitWidth(100);
                im.setImage(img);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            property.set(im);
            return property;
        });
        libelle.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.set(achat.getProduit().getLibelle());
            return property;
        });

        nombre_achat.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.setValue(achat.getNbr_produit());
            return property;
        });

        prixunite.setCellValueFactory(param -> {
            SimpleDoubleProperty property = new SimpleDoubleProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.set(achat.getProduit().getPrix() * achat.getNbr_produit());
            return property;
        });
        ligneachat.setVisible(true);
        rechercher_ligneachat.setVisible(true);
        
             rechercher_ligneachat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ligneachat.setPredicate(new Predicate<TreeItem<LigneAchat>>() {
                    @Override
                    public boolean test(TreeItem<LigneAchat> t) {
                      
                        boolean flag = (t.getValue().getProduit().getLibelle()).toUpperCase().contains(newValue.toUpperCase()) || String.valueOf(t.getValue().getProduit().getPrix()).equals(newValue) || String.valueOf(t.getValue().getNbr_produit()).equals(newValue);
                        return flag;
                    }
                });
            }
        });
        
        info.setVisible(false);
        ObservableList<LigneAchat> achats = FXCollections.observableArrayList(liste);
        TreeItem<LigneAchat> root = new RecursiveTreeItem<>(achats, RecursiveTreeObject::getChildren);
        ligneachat.setRoot(root);
        ligneachat.setShowRoot(false);
        info.setVisible(false);

    }

    @FXML
    private void supprimer(ActionEvent event) {

        stackpane.setVisible(true);

    }

    @FXML
    private void pdf(ActionEvent event) {
        Achat a = achat.getSelectionModel().selectedItemProperty().getValue().getValue();
        UtilisateurServices p = new UtilisateurServices();
        Utilisateur u = p.rechercher(a.getId_client());
        Pdf.getPdf(a, u);
    }

    @FXML
    private void livrer(ActionEvent event) {
        Achat a = achat.getSelectionModel().selectedItemProperty().getValue().getValue();
        System.out.println(a.getEtat());
        if ("livrer".equals(a.getEtat())) {
            MyNotifications.infoNotification("Modification", "Cet Achat a ete deja livre");
            return;
        }

        achatservice.livrer(a.getId_achat());
        MyNotifications.infoNotification("Modification", "Cet Achat A ete modifier a l'etat livrer");
        refresh();
    }

    @FXML
    private void annuler(ActionEvent event) {
        stackpane.setVisible(false);
    }

    @FXML
    private void confirmersupprimer(ActionEvent event) {
        Achat a = achat.getSelectionModel().selectedItemProperty().getValue().getValue();
        achatservice.deleteAchat(a);
        MyNotifications.infoNotification("Suppression", "Cet Achat A ete supprimer ");
        refresh();
        stackpane.setVisible(false);
    }

}
