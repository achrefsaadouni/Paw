/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.veterinaires.user;

import Entity.Vets;
import Service.VeterinaireServices;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import netscape.javascript.JSObject;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLRechercheVetsController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField addressTextField;

    private GeocodingService geocodingService;

    private GoogleMap map;

    private StringProperty address = new SimpleStringProperty();

    ArrayList<Vets> list;
    @FXML
    private ImageView avatar;
    @FXML
    private Label nom;
    @FXML
    private Label tel;
    @FXML
    private Label adresse;
    @FXML
    private Label mail;
    @FXML
    private Label region;
    @FXML
    private Rating rate;
    @FXML
    private Label note;
    @FXML
    private StackPane afficheVet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
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

        VeterinaireServices service = new VeterinaireServices();
        //List<Vets> list = new ArrayList<Vets>();
        list = service.getList();

        //System.out.println(service.getList());
        if (list.isEmpty()) {
            System.out.println("Liste vide");
        } else {
            System.out.println("Liste remplise");
        }

        list.forEach((v) -> {

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLong(v.getLatitude(), v.getLongitude()))
                    .visible(Boolean.TRUE)
                    .title("My Marker");

            Marker marker = new Marker(markerOptions);
            
            map.addMarker(marker);
            map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
                afficheVet.setVisible(true);
                DecimalFormat df = new DecimalFormat("#.#");
                nom.setText(v.getNom() + " " + v.getPrenom());
                mail.setText(v.getEmail());
                tel.setText(String.valueOf(v.getNumero()));
                adresse.setText(v.getAdresse());
                region.setText(v.getRegion());
                rate.setRating(v.getRate());
                if ((v.getRate() != 0) && (v.getRate() != 1) && (v.getRate() != 2) && (v.getRate() != 3) && (v.getRate() != 4) && (v.getRate() != 5)) {
                note.setText(String.valueOf(df.format(v.getRate())) + "/5");
            } else {
                note.setText(String.valueOf((v.getRate()).intValue()) + "/5");
            }
            });

        });

    }

    @FXML
    private void fermer(ActionEvent event) {
        afficheVet.setVisible(false);
    }

}
