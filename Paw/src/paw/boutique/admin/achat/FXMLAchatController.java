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
import Service.ProduitService;
import Service.UtilisateurServices;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Timestamp;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

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
    public void initialize() {
        AchatService achatservice = AchatService.getAchatService();
        info.setVisible(true);
        ligneachat.setVisible(false);
        rechercher_ligneachat.setVisible(false);
      
        liste_achat =achatservice.All();
          initAchat();
    }    
    
    public void initAchat()
    {
         AchatService achatservice = AchatService.getAchatService();
         UtilisateurServices p = new UtilisateurServices();
         
        nom_prenom.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Achat achat = (Achat) param.getValue().getValue();
            Utilisateur u = null;
            u = p.rechercher(achat.getId_client());
            property.set(u.getNom()+" "+u.getPrenom());
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
                consulter(newSelection.getValue().getList());
                });
 
        ObservableList<Achat> achats = FXCollections.observableArrayList(liste_achat);
        TreeItem<Achat> root = new RecursiveTreeItem<>(achats, RecursiveTreeObject::getChildren);
        achat.setRoot(root);
        achat.setShowRoot(false);
        
    }
    
    
      public void consulter(List<LigneAchat> liste)
    {  
        
            LigneAchatService achatservice = LigneAchatService.getLigneService();
             image.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat ligne = (LigneAchat) param.getValue().getValue();
            ImageView im = new ImageView();
            try {
              Image  img = new Image("file:///" + ligne.getProduit().getImages().get(0).getPath().toString());
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
            property.set(achat.getProduit().getPrix()*achat.getNbr_produit());
            return property;
        });
          ligneachat.setVisible(true);
          rechercher_ligneachat.setVisible(true);
          info.setVisible(false);
        ObservableList<LigneAchat> achats = FXCollections.observableArrayList(liste);
        TreeItem<LigneAchat> root = new RecursiveTreeItem<>(achats, RecursiveTreeObject::getChildren);
        ligneachat.setRoot(root);
        ligneachat.setShowRoot(false);
        info.setVisible(false);
        
    }
    
}
