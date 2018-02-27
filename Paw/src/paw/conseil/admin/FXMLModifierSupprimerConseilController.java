package paw.conseil.admin;

import Entity.Conseil;
import Entity.Conseil;
import Service.ConseilServices;
import Service.ConseilServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;

public class FXMLModifierSupprimerConseilController implements Initializable {

    @FXML
    private JFXTreeTableView<Conseil> ConseilTable;

    @FXML
    private TreeTableColumn<Conseil, String> titre;

    @FXML
    private TreeTableColumn<Conseil, String> animal;

    @FXML
    private TreeTableColumn<Conseil, String> type;

    @FXML
    private TreeTableColumn<Conseil, String> description;

    @FXML
    private TreeTableColumn<Conseil, String> gerer;

    @FXML
    private TreeTableColumn<Conseil, String> modifcol;

    @FXML
    private TreeTableColumn<Conseil, String> suppcol;

    @FXML
    private JFXTextField filtre;
    @FXML
    private AnchorPane modifConseil;
    @FXML
    private JFXTextField titreInsertion1;
    @FXML
    private ChoiceBox<String> animalInsertion1;
    @FXML
    private ChoiceBox<String> typeInsertion1;
    @FXML
    private JFXTextArea descriptionInsertion1;
    
    ArrayList<Conseil> liste;
    @FXML
    private TreeTableColumn<Conseil, String> id;
    @FXML
    private JFXTextField idInsertion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConseilServices serviceConseil = new ConseilServices();

        modifConseil.setVisible(false);

        initConseil();
        
        animalInsertion1.getItems().setAll("CHIEN","CHAT","AUTRES");
        animalInsertion1.setValue("CHIEN");
        
       
        typeInsertion1.getItems().setAll("NUTRITION","SANTE");
        typeInsertion1.setValue("NUTRITION");
    }

    private void initConseil() {

        
        id.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Conseil c = (Conseil) param.getValue().getValue();
            property.set(String.valueOf(c.getId()));
            return property;
        });
        
        titre.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Conseil c = (Conseil) param.getValue().getValue();
            property.set(c.getTitre());
            return property;
        });

        animal.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Conseil c = (Conseil) param.getValue().getValue();
            property.set(c.getAnimal());
            return property;
        });

        

        type.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Conseil c = (Conseil) param.getValue().getValue();
            property.set(c.getType());
            return property;
        });

        description.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Conseil c = (Conseil) param.getValue().getValue();
            property.set(String.valueOf(c.getDescription()));
            return property;
        });

        modifcol.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Conseil c = (Conseil) param.getValue().getValue();

            JFXButton modifier = new JFXButton("Modifier");
            modifier.setStyle("-fx-background-color: #26B99A;-fx-text-fill: white;");
            modifier.setOnAction((ActionEvent e) -> {
                modifConseil.setVisible(true);
                modifier(c);
            });
            property.set(modifier);
            return property;

        });
        suppcol.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Conseil v = (Conseil) param.getValue().getValue();
            JFXButton supprimer = new JFXButton("Supprimer");
            supprimer.setStyle("-fx-background-color: #FE2E64;-fx-text-fill: white;");

            supprimer.setOnAction((ActionEvent ea) -> {
                supprimerConseil(v.getId());
                initConseil();
            });

            property.set(supprimer);
            return property;
        });
        
        
        
      refresh();
        filtre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ConseilTable.setPredicate(new Predicate<TreeItem<Conseil>>() {
                    @Override
                    public boolean test(TreeItem<Conseil> v) {
                        Boolean flag = v.getValue().getAnimal().contains(newValue)
                                || v.getValue().getType().contains(newValue)
                                || v.getValue().getTitre().contains(newValue)
                                || v.getValue().getDescription().contains(newValue);
                        return flag;
                    }
                });
            }

        });
        
    }
    
    public void refresh()
    {
                ConseilServices serviceConseil = new ConseilServices();
        liste = serviceConseil.getList();
         ObservableList<Conseil> lesconseils = FXCollections.observableArrayList(liste);
        TreeItem<Conseil> root = new RecursiveTreeItem<>(lesconseils, RecursiveTreeObject::getChildren);
        ConseilTable.setRoot(root);
        ConseilTable.setShowRoot(false);

    }
     @FXML
    void fermer(ActionEvent event) {
        modifConseil.setVisible(false);
        refresh();
    }

    @FXML
    private void actionInsertion(ActionEvent event) {
                if ((!"".equals(titreInsertion1.getText()))&&(!"".equals(animalInsertion1.getValue()))&&(!"".equals(descriptionInsertion1.getText()))&&(!"".equals(typeInsertion1.getValue())))
        {
            ConseilServices service = new ConseilServices();
            service.updateConseil(new Conseil(Integer.valueOf(idInsertion.getText()),titreInsertion1.getText(),animalInsertion1.getValue(),typeInsertion1.getValue(),descriptionInsertion1.getText(),null));
            titreInsertion1.setText("");
            animalInsertion1.setValue("CHIEN");
            typeInsertion1.setValue("NUTRITION");
            descriptionInsertion1.setText("");
    }
    }
    
    private void supprimerConseil(int id) {
        ConseilServices cs = new ConseilServices();
        cs.DeleteConseil(id);
        refresh();
    }
    
    private void modifier(Conseil c) {
        titreInsertion1.setText(c.getTitre());
        typeInsertion1.setValue(c.getType());
        animalInsertion1.setValue(c.getAnimal());
        descriptionInsertion1.setText(c.getDescription());
        idInsertion.setText(String.valueOf(c.getId()));

    }

}

    



