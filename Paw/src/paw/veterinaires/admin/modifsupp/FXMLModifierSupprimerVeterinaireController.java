package paw.veterinaires.admin.modifsupp;

import Entity.Veterinaire;
import Entity.Vets;
import Service.VeterinaireServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import paw.MyNotifications;

public class FXMLModifierSupprimerVeterinaireController implements Initializable {

    @FXML
    private JFXTreeTableView<Vets> VeterinaireTable;

    @FXML
    private TreeTableColumn<Vets, String> nom;

    @FXML
    private TreeTableColumn<Vets, String> prenom;

    @FXML
    private TreeTableColumn<Vets, String> adresse;

    @FXML
    private TreeTableColumn<Vets, String> region;

    @FXML
    private TreeTableColumn<Vets, String> numero;

    @FXML
    private TreeTableColumn<Vets, String> email;

    @FXML
    private TreeTableColumn<Vets, String> longitude;

    @FXML
    private TreeTableColumn<Vets, String> latitude;

    @FXML
    private TreeTableColumn<Vets, String> gerer;

    @FXML
    private JFXTextField filtre;

    @FXML
    private AnchorPane modifVet;


    @FXML
    private JFXTextField nomInsertion;

    @FXML
    private JFXTextField prenomInsertion;

    @FXML
    private JFXTextField adresseInsertion;

    @FXML
    private JFXTextField numeroInsertion;

    @FXML
    private JFXTextField emailInsertion;

    @FXML
    private JFXComboBox<String> gouv;


    ArrayList<Vets> liste;
    @FXML
    private TreeTableColumn<Vets, String> modifcol;
    @FXML
    private TreeTableColumn<Vets, String> suppcol;
    @FXML
    private TreeTableColumn<Vets, String> id;
    @FXML
    private JFXTextField idInsertion;
    @FXML
    private AnchorPane admin_window;
    
    int k;

    @FXML
    void actionInsertion(ActionEvent event) {
        if ((nomInsertion.getText().trim().equals("")) || (prenomInsertion.getText().trim().equals(""))
                || (emailInsertion.getText().trim().equals("")) || (adresseInsertion.getText().trim().equals(""))) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("erreur");
            fail.setContentText("Vous avez oublier de remplir un champs");
            fail.showAndWait();
        } else
        //if ((!"".equals(idInsertion.getText())) && (!"".equals(emailInsertion.getText())) && (!"".equals(nomInsertion.getText())) && (!"".equals(prenomInsertion.getText())) && (!"".equals(gouv.getValue())) && (!"".equals(adresseInsertion.getText())) && (!"".equals(numeroInsertion.getText()))) {
        {  if ((!isInteger(numeroInsertion))) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Le champs numéro doit être un entier", ButtonType.CLOSE);
                alert.show();
            } else {
            VeterinaireServices service = new VeterinaireServices();
            service.updateVeterinaire(new Veterinaire(
                                k,
                                nomInsertion.getText(),
                                prenomInsertion.getText(),
                                adresseInsertion.getText(),
                                gouv.getValue(),
                                Integer.parseInt(numeroInsertion.getText()),
                                emailInsertion.getText()));
            refresh();
            
            emailInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            gouv.setValue("");
            adresseInsertion.setText("");
            numeroInsertion.setText("");
            MyNotifications.infoNotification("Modification", "Vétérinaire modifié avec Succès");
            modifVet.setVisible(false);
        }

    }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VeterinaireServices serviceVet = new VeterinaireServices();

        modifVet.setVisible(false);

        initVet();

        ObservableList<String> items = FXCollections.observableArrayList(
                "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Le Kef",
                "Mahdia", "La Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine",
                "Tozeur", "Tunis", "Zaghouan");
        gouv.setItems(items);
        
    }

    

    private void supprimerVeterinaire(int id) {
        VeterinaireServices as = new VeterinaireServices();
        as.DeleteVeterinaire(id);
        MyNotifications.infoNotification("Suppression", "Vétérinaire supprimé avec Succès");
        refresh();
    }

    private void initVet() {
        VeterinaireServices serviceVet = new VeterinaireServices();
        liste = serviceVet.getList();

        id.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(String.valueOf(v.getId()));
            return property;
        });

        nom.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(v.getNom());
            return property;
        });

        prenom.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(v.getPrenom());
            return property;
        });

        adresse.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(v.getAdresse());
            return property;
        });

        region.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(v.getRegion());
            return property;
        });

        numero.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(String.valueOf(v.getNumero()));
            return property;
        });

        email.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(v.getEmail());
            return property;
        });
        longitude.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(String.valueOf(v.getLongitude()));
            return property;
        });

        latitude.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Vets v = (Vets) param.getValue().getValue();
            property.set(String.valueOf(v.getLatitude()));
            return property;
        });

        

        modifcol.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Vets v = (Vets) param.getValue().getValue();

            JFXButton modifier = new JFXButton("Modifier");
            modifier.setStyle("-fx-background-color: #26B99A;-fx-text-fill: white;");
            modifier.setOnAction((ActionEvent e) -> {
                modifVet.setVisible(true);
                System.out.println(v);
                modifier(v);

//                 
            });
            property.set(modifier);
            return property;

        });
        suppcol.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Vets v = (Vets) param.getValue().getValue();
            JFXButton supprimer = new JFXButton("Supprimer");
            supprimer.setStyle("-fx-background-color: #FE2E64;-fx-text-fill: white;");

            supprimer.setOnAction((ActionEvent ea) -> {
                supprimerVeterinaire(v.getId());
                initVet();
            });

            property.set(supprimer);
            return property;
        });

        refresh();

        filtre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                VeterinaireTable.setPredicate(new Predicate<TreeItem<Vets>>() {
                    @Override
                    public boolean test(TreeItem<Vets> v) {
                        Boolean flag = v.getValue().getNom().contains(newValue)
                                || v.getValue().getPrenom().contains(newValue)
                                || v.getValue().getRegion().contains(newValue)
                                || v.getValue().getAdresse().contains(newValue);
                        return flag;
                    }
                });
            }

        });
    }

    private void modifier(Vets v) {
        nomInsertion.setText(v.getNom());
        prenomInsertion.setText(v.getPrenom());
        emailInsertion.setText(v.getEmail());
        numeroInsertion.setText(String.valueOf(v.getNumero()));
        adresseInsertion.setText(v.getAdresse());
        gouv.setValue(v.getRegion());
        k= v.getId();
    }

    @FXML
    private void fermer(ActionEvent event) {
        modifVet.setVisible(false);
        refresh();
    }


   

    public void refresh() {
        VeterinaireServices serviceVet = new VeterinaireServices();
        liste = serviceVet.getList();
        ObservableList<Vets> vetos = FXCollections.observableArrayList(liste);
        TreeItem<Vets> root = new RecursiveTreeItem<>(vetos, RecursiveTreeObject::getChildren);
        VeterinaireTable.setRoot(root);
        VeterinaireTable.setShowRoot(false);

    }
    
    public boolean isInteger(JFXTextField input) {
        try {
            int a = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
