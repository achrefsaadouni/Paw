/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.boutique.produit.ajouter;

import Entity.Produit;
import Service.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import paw.MyNotifications;
public class FXMLAjouterController {
    private File file ;
  
    private List<File> files = new ArrayList<>() ;
    Image img =null;
    private ProduitService produitservice;
    private List<Produit> myproduits;

    @FXML
    private JFXTextField libelle;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField quantite;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXComboBox<String> type;

      @FXML
    private JFXButton upload;
      
      @FXML
      
    private JFXButton upload1;

    @FXML
    private JFXButton ajouter;
    
     @FXML
    private JFXTreeTableView<Produit> produitsTableView;

    @FXML
    private TreeTableColumn<Produit, Number> id_view;

    @FXML
    private TreeTableColumn<Produit, String> libelle_view;

    @FXML
    private TreeTableColumn<Produit, Number> prix_view;

    @FXML
    private TreeTableColumn<Produit, Number> quantite_view;

    @FXML
    private TreeTableColumn<Produit, String> description_view;

    @FXML
    private TreeTableColumn<Produit, String> type_view;

    @FXML
    private TreeTableColumn<Produit, ImageView> images_view;

        @FXML
    void ajouterProduit(ActionEvent event) {
        if (files.isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image", ButtonType.CLOSE);
           alert.show();
           upload.requestFocus();
        }
        else {
        Produit produit = new Produit(libelle.getText(),Float.valueOf(prix.getText()),Integer.valueOf(quantite.getText()),description.getText(),files,type.getValue());
        produitservice = ProduitService.getProduitService();
        produitservice.addProduit(produit);
        MyNotifications.infoNotification("Ajout","Produit ajouter avec success");
        }
        
               
    }

  @FXML
    void fileChoosing(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
         new ExtensionFilter("Image Files","*.png","*.jpg","*.bmp","*.jpeg","*.gif")
        );
        file = fileChoser.showOpenDialog(theStage);
       if(file !=null)
       {
           files.add(file);
       }
    }

    
      void initTreeTableView() {
        
        produitsTableView.setEditable(true);
        
        produitservice = ProduitService.getProduitService();
        
        id_view.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.setValue(produit.getId_produit());
            return property;
        });
        
        libelle_view.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getLibelle());
            return property;
        });
        prix_view.setCellValueFactory(param -> {
            SimpleDoubleProperty property = new SimpleDoubleProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getPrix());
            return property;
        });
         quantite_view.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.setValue(produit.getQuantite());
            return property;
        });
        description_view.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getDescription());
            return property;
        });
         type_view.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getType());
            return property;
        });
         images_view.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Produit produit = (Produit) param.getValue().getValue();
           ImageView im = new ImageView();  
         try{
         img =new Image("file:///"+produit.getImages().get(0).toPath().toString());
         im.setFitHeight(100);
         im.setFitWidth(100);
         im.setImage(img);
         
         }
         catch(Exception ex)
             {
                 System.out.println("image non charger");
             }
            property.set(im);
            return property;
        });
            
        myproduits = produitservice.findAll();
        ObservableList<Produit> articles = FXCollections.observableArrayList(myproduits);
        TreeItem<Produit> root = new RecursiveTreeItem<Produit>(articles, RecursiveTreeObject::getChildren);
        produitsTableView.setRoot(root);
         produitsTableView.setShowRoot(false);
      }
           
      @FXML
    void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Laisse, Collier et Harnais",
                "Lits et Couvertures",
                "Shampoings et Conditionneurs",
                "Vetements",
                "Bols",
                "Cadeaux",
                "Gâteries",
                "Jouets"        
                );
        type.setItems(items);
        initTreeTableView();
    }

}
