/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.mainUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import static paw.Paw.session;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private JFXButton trouver1;
    @FXML
    private JFXButton trouver;
    @FXML
    private JFXButton perdu;
    @FXML
    private JFXButton vet;
    @FXML
    private JFXButton boutq;
    @FXML
    private JFXButton adoption;
    @FXML
    private JFXButton train;
    @FXML
    private GridPane icon_user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
