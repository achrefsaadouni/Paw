/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonceaccouplement;
import Entity.AnnonceAccouplement;
import Service.AnnonceAccouplementServices;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gmehd
 */
public class FXMLAnnonceAccouplementController implements Initializable {

    @FXML
    private ChoiceBox<String> choixInsertion;
    @FXML
    private JFXTextField ageInsertion;
    @FXML
    private JFXTextField raceInsertion;
    @FXML
    private JFXTextField couleurInsertion;
    @FXML
    private JFXTextField msgInsertion;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private ChoiceBox<String> poilInsertion;
    @FXML
    private JFXRadioButton vouiInsertion;
    @FXML
    private JFXRadioButton vnonInsertion;
    @FXML
    private JFXRadioButton douiInsertion;
    @FXML
    private JFXRadioButton dnonInsertion;
    @FXML
    private JFXTextField idModification;
    @FXML
    private ChoiceBox<String> choixInsertion1;
    @FXML
    private JFXTextField ageInsertion1;
    @FXML
    private JFXTextField raceInsertion1;
    @FXML
    private JFXTextField couleurInsertion1;
    @FXML
    private JFXTextField msgInsertion1;
    @FXML
    private JFXRadioButton male1;
    @FXML
    private JFXRadioButton female1;
    @FXML
    private ChoiceBox<String> poilInsertion1;
    @FXML
    private JFXRadioButton vouiInsertion1;
    @FXML
    private JFXRadioButton vnonInsertion1;
    @FXML
    private JFXRadioButton douiInsertion1;
    @FXML
    private JFXRadioButton dnonInsertion1;
    @FXML
    private JFXTextField idSupression;
    @FXML
    private TableView<AnnonceAccouplement> tableView;
    @FXML
    private TableColumn<AnnonceAccouplement, String> idCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> ageCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> couleurCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> sexCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> raceCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> msgCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> typeCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> dateCol;
    @FXML
    private TableColumn<AnnonceAccouplement, String> poilCol1;
    @FXML
    private TableColumn<AnnonceAccouplement, String> vaccinCol1;
    @FXML
    private TableColumn<AnnonceAccouplement, String> dossierCol1;

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void actionInsertion2(ActionEvent event) {
        if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue()))&& (!"".equals(poilInsertion.getValue())))
        {
// String colier, Date date_trouvee, String lieu_perdu, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, Date date           
              AnnonceAccouplementServices as = new AnnonceAccouplementServices();
            String sexe="Male";
            if (female.isSelected())
            {
                sexe="Female";
            }
            
            String vaccin="OUI";
            if (vnonInsertion.isSelected())
            {
                vaccin="NON";
            }
            
            String dossier="OUI";
            if (dnonInsertion.isSelected())
            {
                dossier="NON";
            }
            
            
            as.insererAnnonceAccouplement(new AnnonceAccouplement(poilInsertion.getValue(), vaccin, dossier,0 , Integer.parseInt(ageInsertion.getText()), couleurInsertion.getText(), sexe, raceInsertion.getText(), msgInsertion.getText(),choixInsertion.getValue(), null));

           
            poilInsertion.setValue("Nus");
            ageInsertion.setText("");
            raceInsertion.setText("");
            msgInsertion.setText("");
            couleurInsertion.setText("");
            choixInsertion.setValue("Chien");
            loadTable();
        }
    }

    @FXML
    private void actionModification2(ActionEvent event) {
         if ((!"".equals(couleurInsertion1.getText()))&& (!"".equals(ageInsertion1.getText()))
                 && (!"".equals(raceInsertion1.getText()))&& (!"".equals(msgInsertion1.getText()))&& (!"".equals(choixInsertion1.getValue()))&& (!"".equals(poilInsertion1.getValue())))
        {
              AnnonceAccouplementServices as = new AnnonceAccouplementServices();
            String sexe="Male";
            if (female1.isSelected())
            {
                sexe="Female";
            }
            
            String vaccin="OUI";
            if (vnonInsertion1.isSelected())
            {
                vaccin="NON";
            }
            
            String dossier="OUI";
            if (dnonInsertion1.isSelected())
            {
                dossier="NON";
            }
            
            
            as.updateAnnonceAccouplement(new AnnonceAccouplement(poilInsertion1.getValue(), vaccin, dossier,0 , Integer.parseInt(ageInsertion1.getText()), couleurInsertion1.getText(), sexe, raceInsertion1.getText(), msgInsertion1.getText(),choixInsertion1.getValue(), null),Integer.parseInt(idModification.getText()));

           
            poilInsertion1.setValue("Nus");
            ageInsertion1.setText("");
            raceInsertion1.setText("");
            msgInsertion1.setText("");
            couleurInsertion1.setText("");
            choixInsertion1.setValue("Chien");
            loadTable();
        }
    }

    @FXML
    private void actionSupression2(ActionEvent event) {
        if (!"".equals(idSupression.getText())){
            AnnonceAccouplementServices ps = new AnnonceAccouplementServices();
            ps.DeleteAnnonceAccouplement(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        loadTable();
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
        choixInsertion1.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion1.setValue("Chien");
        
        poilInsertion.getItems().setAll("Nus","Sans sous-poil","Double pelage","Poils courts","Poils longs");
        poilInsertion.setValue("Nus");
        poilInsertion1.getItems().setAll("Nus","Sans sous-poil","Double pelage","Poils courts","Poils longs");
        poilInsertion1.setValue("Nus");
        
        
        
        ToggleGroup groupi = new ToggleGroup();
        ToggleGroup groupm = new ToggleGroup();
        
        ToggleGroup groupvi = new ToggleGroup();
        ToggleGroup groupvm = new ToggleGroup();
        
        
        ToggleGroup groupdi = new ToggleGroup();
        ToggleGroup groupdm = new ToggleGroup();
        
        male1.setToggleGroup(groupi);
        female1.setToggleGroup(groupi);
        
        male.setToggleGroup(groupm);
        female.setToggleGroup(groupm);
        
        vouiInsertion.setToggleGroup(groupvi);
        vnonInsertion.setToggleGroup(groupvi);
        
        vouiInsertion1.setToggleGroup(groupvm);
        vnonInsertion1.setToggleGroup(groupvm);
        
        douiInsertion.setToggleGroup(groupdi);
        dnonInsertion.setToggleGroup(groupdi);
        
        douiInsertion1.setToggleGroup(groupdm);
        dnonInsertion1.setToggleGroup(groupdm);
        
        
        
    }
    
    
    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        raceCol.setCellValueFactory(new PropertyValueFactory<>("race"));
        msgCol.setCellValueFactory(new PropertyValueFactory<>("message_complementaire"));
        couleurCol.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        poilCol1.setCellValueFactory(new PropertyValueFactory<>("type_poil"));
        vaccinCol1.setCellValueFactory(new PropertyValueFactory<>("vaccin"));
        dossierCol1.setCellValueFactory(new PropertyValueFactory<>("dossier"));
    
    }

    private void loadTable() {
           AnnonceAccouplementServices rs = new AnnonceAccouplementServices();
           tableView.getItems().setAll(rs.getAll1());    
    }
    
    
}
