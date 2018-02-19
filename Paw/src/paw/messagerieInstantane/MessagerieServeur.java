/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.messagerieInstantane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Guideinfo
 */
public class MessagerieServeur extends Application {
    
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
     
    
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLmessagerieServeur.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
     
     
    public static void main(String[] args) {
        launch(args);
        
        
            String msgin="";
        try {
            ss=new ServerSocket(1201);//numero de port dial serveur
            s=ss.accept();//serveur va accepter les connections
        
            din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            while(!msgin.equals("exit")){
                msgin=din.readUTF();
              // msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
               //afficher l massage mn le client
        }
        
        
        }catch(Exception e){}
        
        
    }

      
        
    
}
