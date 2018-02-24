/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.veterinaires.admin;

import Entity.Veterinaire;
import Service.VeterinaireServices;
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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLAjoutVeterinaireController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
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
    private void actionInsertion(ActionEvent event) {
        // if ((!"".equals(emailInsertion.getText()))&&(!"".equals(nomInsertion.getText()))&& (!"".equals(prenomInsertion.getText()))&&(!"".equals(regionInsertion.getText()))&&(!"".equals(adresseInsertion.getText()))&&(!"".equals(numeroInsertion.getText())))
        {
            VeterinaireServices service = new VeterinaireServices();
            //  service.insererVeterinaire(new Veterinaire(0,nomInsertion.getText(),prenomInsertion.getText(),adresseInsertion.getText(),regionInsertion.getText(),Integer.parseInt(numeroInsertion.getText()),emailInsertion.getText()));
            emailInsertion.setText("");
            nomInsertion.setText("");
            prenomInsertion.setText("");
            //regionInsertion.setText("");
            adresseInsertion.setText("");
            numeroInsertion.setText("");

        }
    }
}
