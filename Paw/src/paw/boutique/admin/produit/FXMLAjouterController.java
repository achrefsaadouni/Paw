/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.boutique.admin.produit;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;
import paw.MyNotifications;

public class FXMLAjouterController {

    private File file;
    
    ObservableList<String> items = FXCollections.observableArrayList(
            "Laisse, Collier et Harnais",
            "Lits et Couvertures",
            "Shampoings et Conditionneurs",
            "Vetements",
            "Bols",
            "Cadeaux",
            "Gâteries Et Nourritures",
            "Jouets"
    );

    private List<File> files = new ArrayList<>();
    Image img = null;
    Image img1 = null;
    private ProduitService produitservice;
    private List<Produit> myproduits;

    @FXML
    private JFXTextField libelle;

    @FXML
    private JFXTextField prix;

    @FXML
    private TreeTableColumn<Produit, JFXButton> supprimer;

    @FXML
    private JFXTextField quantite;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField key;

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
    private TreeTableColumn<Produit, ImageView> image1;

    @FXML
    private TreeTableColumn<Produit, JFXButton> modif1;

    @FXML
    private TreeTableColumn<Produit, ImageView> image2;

    @FXML
    private TreeTableColumn<Produit, JFXButton> modif2;

    @FXML
    private TreeTableColumn<Produit, ?> images_view;
    @FXML
    private ImageView imajout1;

    @FXML
    private ImageView imajout2;

    @FXML
    void ajouterProduit(ActionEvent event) {
        if (files.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image", ButtonType.CLOSE);
            alert.show();
            upload.requestFocus();
        } else {
            Produit produit = new Produit(libelle.getText(), Float.valueOf(prix.getText()), Integer.valueOf(quantite.getText()), description.getText(), files, type.getValue());
            produitservice = ProduitService.getProduitService();
            produitservice.addProduit(produit);
            MyNotifications.infoNotification("Ajout", "Produit ajouté avec Succès");
        }

        refresh();
    }

    @FXML
    void fileChoosing(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            files.add(0, file);
            Image im = new Image("file:///" + file.toPath().toString());
            imajout1.setImage(im);
        }
    }

    @FXML
    void fileChoosing2(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            files.add(1, file);
            Image im = new Image("file:///" + file.toPath().toString());
            imajout2.setImage(im);
        }
    }

    void modifierImage(ActionEvent event, int index, int id) {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            produitservice.updateImage(file, index, id);
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
        libelle_view.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        libelle_view.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Produit, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Produit, String> event) {
                TreeItem<Produit> curseur = produitsTableView.getTreeItem(event.getTreeTablePosition().getRow());
                produitservice.updatelibelle(event.getNewValue(), curseur.getValue().getId_produit());
                MyNotifications.infoNotification("Modification", "Libelle edite avec Succès");
            }
        });

        prix_view.setCellValueFactory(param -> {
            SimpleDoubleProperty property = new SimpleDoubleProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getPrix());
            return property;
        });
        prix_view.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new NumberStringConverter()));
        prix_view.setOnEditCommit((TreeTableColumn.CellEditEvent<Produit, Number> event) -> {
            TreeItem<Produit> curseur = produitsTableView.getTreeItem(event.getTreeTablePosition().getRow());
            produitservice.updateprix(event.getNewValue().floatValue(), curseur.getValue().getId_produit());
            MyNotifications.infoNotification("Modification", "prix edite avec Succès");
        });

        quantite_view.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.setValue(produit.getQuantite());
            return property;
        });

        quantite_view.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new NumberStringConverter()));
        quantite_view.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Produit, Number>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Produit, Number> event) {
                TreeItem<Produit> curseur = produitsTableView.getTreeItem(event.getTreeTablePosition().getRow());
                produitservice.updatequantite(event.getNewValue().intValue(), curseur.getValue().getId_produit());
                MyNotifications.infoNotification("Modification", "quantite edite avec Succès");
            }
        });

        description_view.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getDescription());
            return property;
        });

        description_view.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        description_view.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Produit, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Produit, String> event) {
                TreeItem<Produit> curseur = produitsTableView.getTreeItem(event.getTreeTablePosition().getRow());
                produitservice.updatedescription(event.getNewValue(), curseur.getValue().getId_produit());
                MyNotifications.infoNotification("Modification", "description edite avec Succès");
            }
        });

        type_view.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Produit produit = (Produit) param.getValue().getValue();
            property.set(produit.getType());
            return property;
        });

        type_view.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(items));
        type_view.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Produit, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Produit, String> event) {
                TreeItem<Produit> curseur = produitsTableView.getTreeItem(event.getTreeTablePosition().getRow());
                produitservice.updatetype(event.getNewValue(), curseur.getValue().getId_produit());
                MyNotifications.infoNotification("Modification", "type edite avec Succès");
            }
        });

        image1.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Produit produit = (Produit) param.getValue().getValue();
            ImageView im = new ImageView();
            try {
                img = new Image("file:///" + produit.getImages().get(0).toPath().toString());
                im.setFitHeight(100);
                im.setFitWidth(100);
                im.setImage(img);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            property.set(im);
            return property;
        });

        image2.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Produit produit = (Produit) param.getValue().getValue();
            ImageView im = new ImageView();
            try {
                img1 = new Image("file:///" + produit.getImages().get(1).toPath().toString());
                im.setFitHeight(100);
                im.setFitWidth(100);
                im.setImage(img1);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            property.set(im);
            return property;
        });

        modif1.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Produit produit = (Produit) param.getValue().getValue();
            JFXButton b1 = new JFXButton("image1");
            b1.setStyle("-fx-background-color:#43A047;");
            b1.setOnAction((ActionEvent e) -> {
                modifierImage(e, 0, produit.getId_produit());
                refresh();
            });
            property.set(b1);
            return property;
        });

        modif2.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Produit produit = (Produit) param.getValue().getValue();
            JFXButton b2 = new JFXButton("image2");
            b2.setStyle("-fx-background-color:#607D8B;");
            b2.setOnAction((ActionEvent e) -> {
                modifierImage(e, 1, produit.getId_produit());
                refresh();
            });
            property.set(b2);
            return property;
        });

        supprimer.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Produit produit = (Produit) param.getValue().getValue();
            JFXButton supp = new JFXButton("Supprimer");
            supp.setStyle("-fx-background-color:#D50000;");
            supp.setOnAction((ActionEvent e) -> {
                produitservice.deleteProduit(produit.getId_produit());
                MyNotifications.infoNotification("Suppression", "Produit Supprimé avec success");
                refresh();
            });

            property.set(supp);
            return property;
        });

        refresh();
        key.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                produitsTableView.setPredicate(new Predicate<TreeItem<Produit>>() {
                    @Override
                    public boolean test(TreeItem<Produit> t) {
                        boolean flag = t.getValue().getLibelle().contains(newValue) || String.valueOf(t.getValue().getId_produit()).contains(newValue) || String.valueOf(t.getValue().getPrix()).contains(newValue) || String.valueOf(t.getValue().getType()).contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }

    public void refresh() {
        myproduits = produitservice.findAll();
        ObservableList<Produit> articles = FXCollections.observableArrayList(myproduits);
        TreeItem<Produit> root = new RecursiveTreeItem<>(articles, RecursiveTreeObject::getChildren);
        produitsTableView.setRoot(root);
        produitsTableView.setShowRoot(false);
    }

    @FXML
    void initialize() {

        type.setItems(items);
        initTreeTableView();

    }

}
