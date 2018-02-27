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
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import netscape.javascript.JSObject;

public class FXMLModifierSupprimerVeterinaireController implements Initializable, MapComponentInitializedListener {

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
    private GoogleMapView mapView;

    @FXML
    private TextField addressTextField;

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

    LatLong x;

    private GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();

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
    void actionInsertion(ActionEvent event) {
        if ((!"".equals(idInsertion.getText())) && (!"".equals(emailInsertion.getText())) && (!"".equals(nomInsertion.getText())) && (!"".equals(prenomInsertion.getText())) && (!"".equals(gouv.getValue())) && (!"".equals(adresseInsertion.getText())) && (!"".equals(numeroInsertion.getText()))) {
            VeterinaireServices service = new VeterinaireServices();
            //service.updateVeterinaire(new Vets(Integer.parseInt(idInsertion.getText()),nomInsertion.getText(),prenomInsertion.getText(),adresseInsertion.getText(),gouv.getValue(),Integer.parseInt(numeroInsertion.getText()),emailInsertion.getText()));
            emailInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            gouv.setValue("");
            adresseInsertion.setText("");
            numeroInsertion.setText("");
        }

    }

   @FXML
    private void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }

            map.setCenter(latLong);

        });
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
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
    }

    @Override
    public void mapInitialized() {

        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(34.17372841, 9.76475011))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(7);

        map = mapView.createMap(mapOptions);

        MarkerOptions markerOptions = new MarkerOptions();

        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            map.clearMarkers();
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));

            markerOptions.position(new LatLong(ll.getLatitude(), ll.getLongitude()))
                    .visible(Boolean.TRUE)
                    .title("My Marker");

            Marker marker = new Marker(markerOptions);

            map.addMarker(marker);
           
        });
        

    }

    private void supprimerVeterinaire(int id) {
        VeterinaireServices as = new VeterinaireServices();
        as.DeleteVeterinaire(id);
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
                //initVet();
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
        id.setText(String.valueOf(v.getId()));
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
//        map.clearMarkers();
//                MarkerOptions markerOptions = new MarkerOptions();
//                System.out.println();
//                markerOptions.position(new LatLong(v.getLatitude(), v.getLongitude()))
//                        .visible(Boolean.TRUE)
//                        .title("My Marker");
//
//                Marker marker = new Marker(markerOptions);
//
//                map.addMarker(marker);
//                x=new LatLong(v.getLatitude(), v.getLongitude());
//                map.setCenter(x);
//                map.setZoom(9);
    }

    @FXML
    private void fermer(ActionEvent event) {
        modifVet.setVisible(false);
        refresh();
    }

    @FXML
    private void zoomGouv(MouseEvent event) {
//        setViewGouv("Ariana", 36.9874882,9.9589971);
//        setViewGouv("Beja", 36.7713139,8.8242868);
//        setViewGouv("Ben Arous",36.6394789,9.8975044 );
//        setViewGouv("Bizerte",37.1407854,9.030019 );
//        setViewGouv("Gabes",33.8016011,9.2986517 );
//        setViewGouv("Gafsa",34.425061,8.2639009 );
//        setViewGouv("Jendouba",36.6979108,8.0589651 );
//        setViewGouv("Kairouan",35.5977859,9.2243966 );
//        setViewGouv("Kasserine",35.2159615,8.228817 );
//        setViewGouv("Kebili", 33.3576046,7.746856);
//        setViewGouv("Le Kef",36.0437322,8.1425308 );
//        setViewGouv("Mahdia",35.3507583,10.3791821 );
//        setViewGouv("La Manouba",36.7854664,9.537374 );
//        setViewGouv("Medenine",33.0741092,9.5311095 );
//        setViewGouv("Monastir",35.6323446,10.4858122 );
//        setViewGouv("Nabeul", 36.7577206,10.1732619);
//        setViewGouv("Sfax", 34.7290309,9.9900728);
//        setViewGouv("Sidi Bouzid",34.8925282,8.8746037 );
//        setViewGouv("Siliana",35.9828075,8.7834317 );
//        setViewGouv("Sousse", 35.9157545,9.8426515);
//        setViewGouv("Tataouine",31.7406107,7.6335918 );
//        setViewGouv("Tozeur", 33.9789884,7.5311612 );
//        setViewGouv("Tunis",36.8383903,10.0304474 );
//        setViewGouv("Zaghouan", 36.3528152,9.4229962);
    }

    private void setViewGouv(String nom, double latit, double longit) {
        if (gouv.getValue().equals(nom)) {
            MapOptions mapOptions = new MapOptions();
            //x=new LatLong(latit, longit);
            mapOptions.center(new LatLong(latit, longit))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(9);
            map = mapView.createMap(mapOptions);

//        MarkerOptions markerOptions = new MarkerOptions();
//
//        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
//            map.clearMarkers();
//            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
//
//            markerOptions.position(new LatLong(ll.getLatitude(), ll.getLongitude()))
//                    .visible(Boolean.TRUE)
//                    .title("My Marker");
//
//            Marker marker = new Marker(markerOptions);
//
//            map.addMarker(marker);
//            x=ll;
//            
//        });
        }
    }

    public void refresh() {
        VeterinaireServices serviceVet = new VeterinaireServices();
        liste = serviceVet.getList();
        ObservableList<Vets> vetos = FXCollections.observableArrayList(liste);
        TreeItem<Vets> root = new RecursiveTreeItem<>(vetos, RecursiveTreeObject::getChildren);
        VeterinaireTable.setRoot(root);
        VeterinaireTable.setShowRoot(false);

//        ObservableList<Vets> vetos = FXCollections.observableArrayList(liste);
//        TreeItem<Vets> root = new RecursiveTreeItem<>(vetos, RecursiveTreeObject::getChildren);
//        VeterinaireTable.setRoot(root);
//        VeterinaireTable.setShowRoot(false);
    }

}
