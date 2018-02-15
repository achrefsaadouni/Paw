/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.messagerieInstantane;

import com.jfoenix.controls.JFXTextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLmessagerieServeurController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private JFXTextArea msg_area;

    @FXML
    private TextField msg_text;
 static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    @FXML
    void send(ActionEvent event) {
        
           try{
            String msgout="";
            msgout=msg_text.getText().trim();
            dout.writeUTF(msgout);// pour envoyer le mssage mn serveur au client
    }catch(Exception e){}
        
        
        
        
        
        
        
        
        
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
