/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.annonce;
import Entity.Annonce;

import Service.AnnonceServices;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.OutputStreamWriter;

import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;
import static paw.Paw.session;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLAnnonceController implements Initializable {


    @FXML
    private JFXTextField msgInsertion;

    @FXML
    private JFXTextField ageInsertion;


    @FXML
    private JFXTextField sexInsertion;

    @FXML
    private JFXTextField couleurInsertion;

    @FXML
    private JFXTextField raceInsertion;

    @FXML
    private JFXTextField idModification;

    @FXML
    private JFXTextField couleurModification;

    @FXML
    private JFXTextField sexModification;

    @FXML
    private JFXTextField msgModification;

    @FXML
    private JFXTextField raceModification;


    @FXML
    private JFXTextField ageModification;

    @FXML
    private JFXTextField idSupression;
 
    @FXML
    private TableView<Annonce> tableView;

    @FXML
    private TableColumn<Annonce,String> idCol;

    @FXML
    private TableColumn<Annonce, String> ageCol;

    @FXML
    private TableColumn<Annonce, String> couleurCol;

    @FXML
    private TableColumn<Annonce, String> sexCol;

    @FXML
    private TableColumn<Annonce, String> raceCol;

    @FXML
    private TableColumn<Annonce, String> msgCol;

    @FXML
    private TableColumn<Annonce, String> typeCol;
    
    @FXML
    private ChoiceBox<String> choixInsertion;
    @FXML
    private ChoiceBox<String> choixModification;
    @FXML
    private TableColumn<Annonce, String> dateCol;
    @FXML
    private JFXTextField txtapi;
    @FXML
    private JFXTextField txtnumber;
    @FXML
    private JFXTextField txtmess;
@FXML
    void actionInsertion2(ActionEvent event) {
         if ((!"".equals(couleurInsertion.getText()))&& (!"".equals(ageInsertion.getText()))&& (!"".equals(sexInsertion.getText()))
                 && (!"".equals(raceInsertion.getText()))&& (!"".equals(msgInsertion.getText()))&& (!"".equals(choixInsertion.getValue())))
        {
            AnnonceServices as = new AnnonceServices();
            as.insererAnnonce(new Annonce(0,Integer.parseInt(ageInsertion.getText()),couleurInsertion.getText(),sexInsertion.getText(),
            raceInsertion.getText(),msgInsertion.getText(),choixInsertion.getValue()));
            ageInsertion.setText("");
            sexInsertion.setText("");
            raceInsertion.setText("");
            msgInsertion.setText("");
            couleurInsertion.setText("");
            choixInsertion.setValue("Chien");
            loadTable();
        }


    }

    @FXML
    void actionModification2(ActionEvent event) 
    {

            
        
            if ((!"".equals(idModification.getText()))&&(!"".equals(couleurModification.getText()))&& (!"".equals(ageModification.getText()))&& (!"".equals(sexModification.getText()))
                 && (!"".equals(raceModification.getText()))&& (!"".equals(msgModification.getText()))&& (!"".equals(choixModification.getValue())))
        {
            AnnonceServices as = new AnnonceServices();
            
            as.updateAnnonce(new 
             Annonce(Integer.parseInt(idModification.getText()),
                    Integer.parseInt(ageModification.getText()),
                    couleurModification.getText(),
                    sexModification.getText(),
                    raceModification.getText(),
                    msgModification.getText(),
                    choixModification.getValue()),
                    Integer.parseInt(idModification.getText()));
           
            
            
            
            
            idModification.setText("");
            ageModification.setText("");
         couleurModification.setText("");
         sexModification.setText("");
           raceModification.setText("");
           msgModification.setText("");
             choixModification.setValue("Chien");
            loadTable();
                   
        }

    }

    @FXML
    void actionSupression2(ActionEvent event) {

    if (!"".equals(idSupression.getText())){
            AnnonceServices ps = new AnnonceServices();
            ps.DeleteAnnonce(Integer.parseInt(idSupression.getText()));
            idSupression.setText("");
            loadTable();
        }
   
    }

  
   
    
    @Override
      public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadTable();
        choixInsertion.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixInsertion.setValue("Chien");
        choixModification.getItems().setAll("Chien","Chat","Chèvre","Cheval","Rongeur");
        choixModification.setValue("Chien");
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
    }

    private void loadTable() {
       AnnonceServices rs = new AnnonceServices();
        tableView.getItems().setAll(rs.getAll());    
    }

    @FXML
    private void actionSend(ActionEvent event) {
    
     try {
			// Construct data
			String apiKey = "apikey=" + txtapi.getText();
			String message = "&message=Bienvenue à Paw Votre Annonce a été deposée avec succés" ;
			String sender = "&sender=PawFamily" ;
			String numbers = "&numbers=" + session.getNumero();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, "message"+line);
			}
			rd.close();
			
				} catch (Exception e) {
			                      JOptionPane.showMessageDialog(null, e);
			
		}
    
    
    
    
    
    
    
    
    
    }
}
