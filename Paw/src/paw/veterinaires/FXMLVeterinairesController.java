package paw.veterinaires;


import Entity.Vets;
import Service.VeterinaireServices;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.Rating;
import static paw.Paw.session;


public class FXMLVeterinairesController implements Initializable, MapComponentInitializedListener {

    @FXML
    private Label nom1;
    @FXML
    private Label adr1;
    @FXML
    private Label mail1;
    @FXML
    private Label nom2;
    @FXML
    private Label adr2;
    @FXML
    private Label mail2;
    @FXML
    private Label nom3;
    @FXML
    private Label adr3;
    @FXML
    private Label mail3;
    @FXML
    private Label nom4;
    @FXML
    private Label adr4;
    @FXML
    private Label mail4;
    @FXML
    private Label tel1;
    @FXML
    private Label tel2;
    @FXML
    private Label tel3;
    @FXML
    private Label tel4;
    @FXML
    private Label reg1;

    @FXML
    private Label reg2;

    @FXML
    private Label reg3;

    @FXML
    private Label reg4;
    @FXML
    private Rating rate1;
    @FXML
    private Rating rate2;
    @FXML
    private Rating rate3;
    @FXML
    private Rating rate4;
    @FXML
    private Label note1;
    @FXML
    private Label note2;
    @FXML
    private Label note3;
    @FXML
    private Label note4;
    @FXML
    private AnchorPane box1;
    @FXML
    private AnchorPane box2;
    @FXML
    private AnchorPane box3;
    @FXML
    private AnchorPane box4;
    @FXML
    private Pagination paginator;

    ArrayList<Vets> list;
    @FXML
    private StackPane vide;
    @FXML
    private StackPane evaluer;
    @FXML
    private AnchorPane evaldoc;
    @FXML
    private Rating evaluation;
    @FXML
    private Label docteur;
    @FXML
    private JFXButton eval1;
    @FXML
    private JFXButton eval2;
    @FXML
    private JFXButton eval3;
    @FXML
    private JFXButton eval4;
    @FXML
    private Rating evaluation1;
    @FXML
    private Rating evaluation2;
    @FXML
    private Rating evaluation3;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private StackPane posVet;
    @FXML
    private JFXButton position1;
    @FXML
    private JFXButton position2;
    @FXML
    private JFXButton position3;
    @FXML
    private JFXButton position4;

    private GoogleMap map;
    
    LatLong x;
    @FXML
    private ImageView photo1;
    @FXML
    private ImageView photo2;
    @FXML
    private ImageView photo3;
    @FXML
    private ImageView photo4;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VeterinaireServices service = new VeterinaireServices();

        list = service.getList();
        
        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
            paginator.setVisible(false);
            vide.setVisible(true);
        } else {
            paginator.setVisible(true);
            setNbPages();
            initVeterinairePage(0);
            vide.setVisible(false);

        }
        mapView.addMapInializedListener(this);

    }

    private void setNbPages() {
        if (list.size() % 4 != 0) {
            paginator.setPageCount((list.size() / 4) + 1);
        } else {
            paginator.setPageCount(list.size() / 4);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initVeterinairePage(newIndex.intValue());
        });
    }

    public List<Vets> getVeterinairesPage(int index) {

        int start = 4 * index;
        int fin = start + 4;
        if (list.size() > start) {
            if (list.size() > fin) {
                return list.subList(start, fin);
            } else {
                return list.subList(start, list.size());
            }
        }
        return list.subList(0, 3);
    }
    
    private void initVeterinairePage(int index) {
        VeterinaireServices service1 = new VeterinaireServices();

        list = service1.getList();
        DecimalFormat df = new DecimalFormat("#.#");
        paginator.setCurrentPageIndex(index);
        List<Vets> QuatreVeterinaires = getVeterinairesPage(index);
        
        if (QuatreVeterinaires.size() >= 1) {
            box1.setVisible(true);

            nom1.setText(QuatreVeterinaires.get(0).getNom() + " " + QuatreVeterinaires.get(0).getPrenom());
            mail1.setText(QuatreVeterinaires.get(0).getEmail());
            tel1.setText(String.valueOf(QuatreVeterinaires.get(0).getNumero()));
            adr1.setText(QuatreVeterinaires.get(0).getAdresse());
            reg1.setText(QuatreVeterinaires.get(0).getRegion());
            rate1.setRating(QuatreVeterinaires.get(0).getRate());
            if ((QuatreVeterinaires.get(0).getRate() != 0) && (QuatreVeterinaires.get(0).getRate() != 1) && (QuatreVeterinaires.get(0).getRate() != 2) && (QuatreVeterinaires.get(0).getRate() != 3) && (QuatreVeterinaires.get(0).getRate() != 4) && (QuatreVeterinaires.get(0).getRate() != 5)) {
                note1.setText(String.valueOf(df.format(QuatreVeterinaires.get(0).getRate())) + "/5");
            } else {
                note1.setText(String.valueOf((QuatreVeterinaires.get(0).getRate()).intValue()) + "/5");
            }
            
            Image im = new Image("http://localhost/pawVets/" + QuatreVeterinaires.get(0).getImages());
//            photo1.setFitHeight(225);
//            photo1.setFitWidth(250);
            photo1.setImage(im);
            
            eval1.setOnAction((ActionEvent)
                    -> {
                
                docteur.setText("Evaluer Dr." + QuatreVeterinaires.get(0).getNom() + " " + QuatreVeterinaires.get(0).getPrenom());
                evaluer.setVisible(true);

                evaluation1.setVisible(false);
                evaluation2.setVisible(false);
                evaluation3.setVisible(false);
                evaluation.setVisible(true);

                VeterinaireServices service = new VeterinaireServices();
                evaluation.setRating(service.getRating(session.getId(), QuatreVeterinaires.get(0).getId()));

                evaluation.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        VeterinaireServices service = new VeterinaireServices();
                        if (service.getRating(session.getId(), QuatreVeterinaires.get(0).getId()) != 0) {
                            service.updateRating(newValue.intValue(), session.getId(), QuatreVeterinaires.get(0).getId());
                        } else {
                            service.setRating(QuatreVeterinaires.get(0).getId(), session.getId(), newValue.intValue());
                        }
                    }

                });
            });
            
            position1.setOnAction((ActionEvent)
                    -> {
                
                posVet.setVisible(true);
                map.clearMarkers();
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(new LatLong(QuatreVeterinaires.get(0).getLatitude(), QuatreVeterinaires.get(0).getLongitude()))
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);

                map.addMarker(marker);
                x=new LatLong(QuatreVeterinaires.get(0).getLatitude(), QuatreVeterinaires.get(0).getLongitude());
                map.setCenter(x);
                map.setZoom(9);
            });
            
            
               

        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (QuatreVeterinaires.size() >= 2) {
            box2.setVisible(true);

            nom2.setText(QuatreVeterinaires.get(1).getNom() + " " + QuatreVeterinaires.get(1).getPrenom());
            mail2.setText(QuatreVeterinaires.get(1).getEmail());
            tel2.setText(String.valueOf(QuatreVeterinaires.get(1).getNumero()));
            adr2.setText(QuatreVeterinaires.get(1).getAdresse());
            reg2.setText(QuatreVeterinaires.get(1).getRegion());
            rate2.setRating(QuatreVeterinaires.get(1).getRate());
            if ((QuatreVeterinaires.get(1).getRate() != 0) && (QuatreVeterinaires.get(1).getRate() != 1) && (QuatreVeterinaires.get(1).getRate() != 2) && (QuatreVeterinaires.get(1).getRate() != 3) && (QuatreVeterinaires.get(1).getRate() != 4) && (QuatreVeterinaires.get(1).getRate() != 5)) {
                note2.setText(String.valueOf(df.format(QuatreVeterinaires.get(1).getRate())) + "/5");
            } else {
                note2.setText(String.valueOf((QuatreVeterinaires.get(1).getRate()).intValue()) + "/5");
            }
            Image im = new Image("http://localhost/pawVets/" + QuatreVeterinaires.get(1).getImages());
//            photo2.setFitHeight(225);
//            photo2.setFitWidth(250);
            photo2.setImage(im);
            eval2.setOnAction((ActionEvent)
                    -> {
                docteur.setText("Evaluer Dr." + QuatreVeterinaires.get(1).getNom() + " " + QuatreVeterinaires.get(1).getPrenom());
                evaluer.setVisible(true);

                VeterinaireServices service = new VeterinaireServices();
                evaluation1.setRating(service.getRating(session.getId(), QuatreVeterinaires.get(1).getId()));

                evaluation.setVisible(false);
                evaluation2.setVisible(false);
                evaluation3.setVisible(false);
                evaluation1.setVisible(true);

                evaluation1.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        VeterinaireServices service = new VeterinaireServices();
                        if (service.getRating(session.getId(), QuatreVeterinaires.get(1).getId()) != 0) {
                            service.updateRating(newValue.intValue(), session.getId(), QuatreVeterinaires.get(1).getId());
                        } else {
                            service.setRating(QuatreVeterinaires.get(1).getId(), session.getId(), newValue.intValue());
                        }
                    }

                });

            });

            position2.setOnAction((ActionEvent)
                    -> {
                posVet.setVisible(true);
                map.clearMarkers();
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(new LatLong(QuatreVeterinaires.get(1).getLatitude(), QuatreVeterinaires.get(1).getLongitude()))
                        .visible(Boolean.TRUE)
                        .title("My Marker");
                
                Marker marker = new Marker(markerOptions);

                map.addMarker(marker);
                
                x=new LatLong(QuatreVeterinaires.get(1).getLatitude(), QuatreVeterinaires.get(1).getLongitude());
                map.setCenter(x);
                map.setZoom(9);
                
                //System.out.println(QuatreVeterinaires.get(1).getLatitude()+"    "+QuatreVeterinaires.get(1).getLongitude());
            });

        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (QuatreVeterinaires.size() >= 3) {
            box3.setVisible(true);

            nom3.setText(QuatreVeterinaires.get(2).getNom() + " " + QuatreVeterinaires.get(2).getPrenom());
            mail3.setText(QuatreVeterinaires.get(2).getEmail());
            tel3.setText(String.valueOf(QuatreVeterinaires.get(2).getNumero()));
            adr3.setText(QuatreVeterinaires.get(2).getAdresse());
            reg3.setText(QuatreVeterinaires.get(2).getRegion());
            rate3.setRating(QuatreVeterinaires.get(2).getRate());
            if ((QuatreVeterinaires.get(2).getRate() != 0) && (QuatreVeterinaires.get(2).getRate() != 1) && (QuatreVeterinaires.get(2).getRate() != 2) && (QuatreVeterinaires.get(2).getRate() != 3) && (QuatreVeterinaires.get(2).getRate() != 4) && (QuatreVeterinaires.get(2).getRate() != 5)) {
                note3.setText(String.valueOf(df.format(QuatreVeterinaires.get(2).getRate())) + "/5");
            } else {
                note3.setText(String.valueOf((QuatreVeterinaires.get(2).getRate()).intValue()) + "/5");
            }
            
            Image im = new Image("http://localhost/pawVets/" + QuatreVeterinaires.get(2).getImages());
//            photo3.setFitHeight(225);
//            photo3.setFitWidth(250);
            photo3.setImage(im);
            
            eval3.setOnAction((ActionEvent)
                    -> {
                docteur.setText("Evaluer Dr." + QuatreVeterinaires.get(2).getNom() + " " + QuatreVeterinaires.get(2).getPrenom());
                evaluer.setVisible(true);

                VeterinaireServices service = new VeterinaireServices();
                evaluation2.setRating(service.getRating(session.getId(), QuatreVeterinaires.get(2).getId()));

                evaluation1.setVisible(false);
                evaluation.setVisible(false);
                evaluation3.setVisible(false);
                evaluation2.setVisible(true);
                evaluation2.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        VeterinaireServices service = new VeterinaireServices();
                        if (service.getRating(session.getId(), QuatreVeterinaires.get(2).getId()) != 0) {
                            service.updateRating(newValue.intValue(), session.getId(), QuatreVeterinaires.get(2).getId());
                        } else {
                            service.setRating(QuatreVeterinaires.get(2).getId(), session.getId(), newValue.intValue());
                        }
                    }

                });

            });
            
            
            position3.setOnAction((ActionEvent)
                    -> {
                posVet.setVisible(true);
                map.clearMarkers();
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(new LatLong(QuatreVeterinaires.get(2).getLatitude(), QuatreVeterinaires.get(2).getLongitude()))
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);

                map.addMarker(marker);
                
                x=new LatLong(QuatreVeterinaires.get(2).getLatitude(), QuatreVeterinaires.get(2).getLongitude());
                map.setCenter(x);
                map.setZoom(9);
                //System.out.println(QuatreVeterinaires.get(1).getLatitude()+"    "+QuatreVeterinaires.get(1).getLongitude());
            });
            
            

        } else {
            box3.setVisible(false);
        }

        ///////////////////////////////////////////////////////////
        if (QuatreVeterinaires.size() >= 4) {

            box4.setVisible(true);

            nom4.setText(QuatreVeterinaires.get(3).getNom() + " " + QuatreVeterinaires.get(3).getPrenom());
            mail4.setText(QuatreVeterinaires.get(3).getEmail());
            tel4.setText(String.valueOf(QuatreVeterinaires.get(3).getNumero()));
            adr4.setText(QuatreVeterinaires.get(3).getAdresse());
            reg4.setText(QuatreVeterinaires.get(3).getRegion());
            rate4.setRating(QuatreVeterinaires.get(3).getRate());
            if ((QuatreVeterinaires.get(3).getRate() != 0) && (QuatreVeterinaires.get(3).getRate() != 1) && (QuatreVeterinaires.get(3).getRate() != 2) && (QuatreVeterinaires.get(3).getRate() != 3) && (QuatreVeterinaires.get(3).getRate() != 4) && (QuatreVeterinaires.get(3).getRate() != 5)) {
                note4.setText(String.valueOf(df.format(QuatreVeterinaires.get(3).getRate())) + "/5");
            } else {
                note4.setText(String.valueOf((QuatreVeterinaires.get(3).getRate()).intValue()) + "/5");
            }
            
            Image im = new Image("http://localhost/pawVets/" + QuatreVeterinaires.get(3).getImages());
//            photo4.setFitHeight(225);
//            photo4.setFitWidth(250);
            photo4.setImage(im);
            
            eval4.setOnAction((ActionEvent)
                    -> {
                docteur.setText("Evaluer Dr. " + QuatreVeterinaires.get(3).getNom() + " " + QuatreVeterinaires.get(3).getPrenom());
                evaluer.setVisible(true);
                VeterinaireServices service = new VeterinaireServices();
                evaluation3.setRating(service.getRating(session.getId(), QuatreVeterinaires.get(3).getId()));

                evaluation1.setVisible(false);
                evaluation2.setVisible(false);
                evaluation.setVisible(false);
                evaluation3.setVisible(true);

                evaluation3.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        VeterinaireServices service = new VeterinaireServices();
                        if (service.getRating(session.getId(), QuatreVeterinaires.get(3).getId()) != 0) {
                            service.updateRating(newValue.intValue(), session.getId(), QuatreVeterinaires.get(3).getId());
                        } else {
                            service.setRating(QuatreVeterinaires.get(3).getId(), session.getId(), newValue.intValue());
                        }
                    }

                });

            });
            
            position4.setOnAction((ActionEvent)
                    -> {
                posVet.setVisible(true);

                map.clearMarkers();
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(new LatLong(QuatreVeterinaires.get(3).getLatitude(), QuatreVeterinaires.get(3).getLongitude()))
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);

                map.addMarker(marker);
                
                x=new LatLong(QuatreVeterinaires.get(3).getLatitude(), QuatreVeterinaires.get(3).getLongitude());
                map.setCenter(x);
                map.setZoom(9);
              
            });

        } else {
            box4.setVisible(false);
        }

    }

    @FXML
    private void actionRate(ActionEvent event) {
        //evaluer.setVisible(true);
        //docteur.setText("");
    }

    @FXML
    private void fermer(ActionEvent event) {
        initVeterinairePage(paginator.getCurrentPageIndex());
        evaluer.setVisible(false);
        posVet.setVisible(false);

    }

    @FXML
    private void actionPosition(ActionEvent event) {
    }

    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();

        //mapOptions.center(x)
        mapOptions.center(new LatLong(33.8869, 9.5375))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(6);

        map = mapView.createMap(mapOptions);

    }

}
