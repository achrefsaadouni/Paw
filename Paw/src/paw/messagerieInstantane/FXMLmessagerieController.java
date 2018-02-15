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
public class FXMLmessagerieController implements Initializable {

    @FXML
    private JFXTextArea msg_area;
    @FXML
    private TextField msg_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    @FXML
    private void send(ActionEvent event) {
    
    
        
          try{
        
            String msgout="";
            msgout=msg_text.getText().trim();
            dout.writeUTF(msgout);
               String msgin="";
            
            while(!msgin.equals("exit")){
                
                msgin=din.readUTF();
                msg_area.setText(msg_area.getText().trim()+"\n Serveur:\t"+msgin);
                
            }
        }catch(Exception e){}
        
        
        
    }
    
}
