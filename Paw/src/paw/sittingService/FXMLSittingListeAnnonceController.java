package paw.sittingService;

import Entity.AnnonceSitting;
import Service.AnnonceSittingServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.StringUtils;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import static paw.Paw.session;

public class FXMLSittingListeAnnonceController {


    @FXML
    private AnchorPane window;

    @FXML
    private Pane annonce;

    @FXML
    private GridPane tableau;

    @FXML
    private Label animal;

    @FXML
    private Label race;

    @FXML
    private Label sexe;

    @FXML
    private Label age;

    @FXML
    private Label couleur;

    private Label nom;

    @FXML
    private Label dateSit;

    @FXML
    private Label dureSit;

    @FXML
    private Label desc;

    @FXML
    private Pagination paginator;


    @FXML
    private JFXButton ModifButton;

    @FXML
    private JFXButton SuppButton;

    @FXML
    private JFXListView<String> listView;

    @FXML
    private StackPane stackModif;

    @FXML
    private GridPane tableau1;

    @FXML
    private JFXListView<String> listView1;

    @FXML
    private JFXTextField taskText;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton quitterModif;

    @FXML
    private JFXButton confirmModif;
    @FXML
    private JFXTextField typeM;
    @FXML
    private JFXTextField raceM;
    @FXML
    private JFXTextField sexeM;
    @FXML
    private JFXTextField ageM;
    @FXML
    private JFXTextField couleurM;
    @FXML
    private JFXTextField dureSitM;
    @FXML
    private JFXTextField descM;
    @FXML
    private JFXDatePicker dateSitM;
    String type="Annonce Sitting";
    @FXML
    private StackPane aucunAnnonce;

    @FXML
    void addTask(ActionEvent event) {
        listView1.getItems().add(taskText.getText());   
        taskText.setText("");
    }
    ArrayList<AnnonceSitting> liste = null;
    @FXML
    void initialize() {
        
        stackModif.setVisible(false);
        
        AnnonceSittingServices service = new AnnonceSittingServices();    
            
           liste = service.getAnnonceSitting(session.getId());
           if(liste.isEmpty()){
            annonce.setVisible(false);
            paginator.setVisible(false);
            aucunAnnonce.setVisible(true);
           }else{
            aucunAnnonce.setVisible(false);
            annonce.setVisible(true);
            paginator.setVisible(true);
//            listevide.setVisible(false);
            setNbPages();
            initAnnoncePage(0);
        }

    }


   
    private void setNbPages() {
        paginator.setPageCount(liste.size());
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });    
    }

    private void initAnnoncePage(int i) {
            paginator.setCurrentPageIndex(i);     
            animal.setText(liste.get(i).getTypePet());
            race.setText(liste.get(i).getRace());
            sexe.setText(liste.get(i).getSex());
            age.setText(String.valueOf(liste.get(i).getAge()));
            couleur.setText(liste.get(i).getCouleur());
            dateSit.setText(liste.get(i).getDateSit().toString());
            dureSit.setText(String.valueOf(liste.get(i).getDureSit()));
            desc.setText(liste.get(i).getMessage_complementaire());
            String todoString = liste.get(i).getToDoList().substring(1,liste.get(i).getToDoList().length()-1 );
            List<String> list = StringUtils.split(todoString,",",true);
            ObservableList<String> todo = FXCollections.observableArrayList(list);
            listView.setItems(todo);
            ModifButton.setOnAction((event) -> {
                    
                   modifierAnnoce(liste.get(i).getId(),i);
                    
                });
            SuppButton.setOnAction((event) -> {
                    
                   suppAnnonce(liste.get(i).getId(),i);
                    
             });
    }

    private void modifierAnnoce(int id, int i) {
        
        stackModif.setVisible(true);
        
        typeM.setText(liste.get(i).getTypePet());
        raceM.setText(liste.get(i).getRace());
        sexeM.setText(liste.get(i).getSex());
        ageM.setText(String.valueOf(liste.get(i).getAge()));
        couleurM.setText(liste.get(i).getCouleur());
        dureSitM.setText(String.valueOf(liste.get(i).getDureSit()));
        descM.setText(liste.get(i).getMessage_complementaire());
        
        confirmModif.setOnAction((event) -> {
                    
                   confirmModif(id);
                   AnnonceSittingServices service = new AnnonceSittingServices();
                   liste = service.getAnnonceSitting(session.getId());
                   initAnnoncePage(i);
                    
                });
        initAnnoncePage(i);
    }
    
    private void confirmModif(int id) {
        System.out.println(listView1.getItems().toString());
        if ((couleurM.getText().trim().equals(""))
                || (typeM.getText().trim().equals(""))|| (listView1.getItems().toString().trim().equals(""))|| (ageM.getText().trim().equals(""))||(!isInteger(ageM))
                || (raceM.getText().trim().equals(""))|| (descM.getText().trim().equals("")))
                 {
                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
                    fail.setHeaderText("erreur");
                    fail.setContentText("Vous devez remplir touts les champs");
                    fail.showAndWait();
                 }else{
        AnnonceSittingServices as = new AnnonceSittingServices();
            
            as.updateAnnonceSitting(new 
             AnnonceSitting(
                    
                    java.sql.Date.valueOf(dateSitM.getValue()),
                    Integer.parseInt(dureSitM.getText()),
                    listView1.getItems().toString(),
                    typeM.getText(),
                    0,
                    Integer.parseInt(ageM.getText()),
                    couleurM.getText(),
                    sexeM.getText(),
                    raceM.getText(),
                    descM.getText(),
                    type,
                    java.sql.Date.valueOf(LocalDate.now()), 
                    session.getId()),id);
       
       stackModif.setVisible(false);
        }
    }

    @FXML
    private void QuitModif(ActionEvent event) {
        stackModif.setVisible(false);
    }

    private void suppAnnonce(int id, int i) {
            AnnonceSittingServices ps = new AnnonceSittingServices();
            ps.DeleteAnnonceSitting(id);
            AnnonceSittingServices service = new AnnonceSittingServices();    
            liste = service.getAnnonceSitting(session.getId()); 
            setNbPages();
            initAnnoncePage(1); 
    }

    private boolean isInteger(JFXTextField input) {
        try {
            int age = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    
}
