/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.messagerieInstantane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class Messagerie extends Application {
    
    
    
     static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    
   public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLmessagerieClient.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
           try{
            s =new Socket("127.0.0.1",1201);// address ip local du client w serveur m7it khdamin f local 
            din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            String msgin="";
            
            while(!msgin.equals("exit")){
                
                msgin=din.readUTF();
              
                
            }
            
            
        }catch(Exception e){}
    
    }
    
}
