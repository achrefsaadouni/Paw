/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.veterinaires.admin;

import Entity.Veterinaire;
import Service.ProduitService;
import Service.VeterinaireServices;
import Utility.Checksum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLAjoutVeterinaireController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    LatLong x;
    
    private GoogleMap map;
    @FXML
    private TextField addressTextField;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
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
    private JFXListView<String> gouv;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imajout1;
    
    private String chaine;
    
    private File file;
    @FXML
    private AnchorPane admin_window;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
        //gouv = (JFXListView<String>) new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(
            "Ariana","Beja","Ben Arous","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Le Kef",
            "Mahdia","La Manouba","Medenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine",
            "Tozeur","Tunis","Zaghouan");
        gouv.setItems(items);
        
        
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

    

    @FXML
    private void zoomGouv(MouseEvent event) {

        setViewGouv("Ariana", 36.9874882,9.9589971);
        setViewGouv("Beja", 36.7713139,8.8242868);
        setViewGouv("Ben Arous",36.6394789,9.8975044 );
        setViewGouv("Bizerte",37.1407854,9.030019 );
        setViewGouv("Gabes",33.8016011,9.2986517 );
        setViewGouv("Gafsa",34.425061,8.2639009 );
        setViewGouv("Jendouba",36.6979108,8.0589651 );
        setViewGouv("Kairouan",35.5977859,9.2243966 );
        setViewGouv("Kasserine",35.2159615,8.228817 );
        setViewGouv("Kebili", 33.3576046,7.746856);
        setViewGouv("Le Kef",36.0437322,8.1425308 );
        setViewGouv("Mahdia",35.3507583,10.3791821 );
        setViewGouv("La Manouba",36.7854664,9.537374 );
        setViewGouv("Medenine",33.0741092,9.5311095 );
        setViewGouv("Monastir",35.6323446,10.4858122 );
        setViewGouv("Nabeul", 36.7577206,10.1732619);
        setViewGouv("Sfax", 34.7290309,9.9900728);
        setViewGouv("Sidi Bouzid",34.8925282,8.8746037 );
        setViewGouv("Siliana",35.9828075,8.7834317 );
        setViewGouv("Sousse", 35.9157545,9.8426515);
        setViewGouv("Tataouine",31.7406107,7.6335918 );
        setViewGouv("Tozeur", 33.9789884,7.5311612 );
        setViewGouv("Tunis",36.8383903,10.0304474 );
        setViewGouv("Zaghouan", 36.3528152,9.4229962);
    }
    private void setViewGouv(String nom, double latit, double longit){
    if(gouv.getSelectionModel().getSelectedItem().equals(nom)){
            MapOptions mapOptions = new MapOptions();
            x=new LatLong(latit, longit);
        mapOptions.center(new LatLong(latit,longit))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(9);
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
            x=ll;
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
        });
        }
    }
    
    @FXML
    private void actionInsertion(ActionEvent event) {
         if ((!"".equals(emailInsertion.getText()))&&(!"".equals(nomInsertion.getText()))&& (!"".equals(prenomInsertion.getText()))&&(!"".equals(adresseInsertion.getText()))&&(!"".equals(numeroInsertion.getText())))
        {
            VeterinaireServices service = new VeterinaireServices();
            service.insererVeterinaire(new Veterinaire(0,nomInsertion.getText(),prenomInsertion.getText(),adresseInsertion.getText(),gouv.getSelectionModel().getSelectedItem(),Integer.parseInt(numeroInsertion.getText()),emailInsertion.getText(),x.getLongitude(),x.getLatitude(), file));
            emailInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            adresseInsertion.setText("");
            numeroInsertion.setText("");
        }
    }

    @FXML
    private void fileChoosing(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChoser = new FileChooser();
        fileChoser.setTitle("Sélectionnez Des images");
        fileChoser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif")
       
        );
        file = fileChoser.showOpenDialog(theStage);
        if (file != null) {
            Image im = new Image("file:///" + file.toPath().toString());
            imajout1.setImage(im);
                   
           
        }
    }
}
