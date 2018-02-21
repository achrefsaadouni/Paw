/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.videoConseil;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class FXMLVideoPlayerController implements Initializable {

    @FXML
    private MediaView mv ; 
    
    MediaPlayer mediaplayer ; 
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     String VUrl="http://localhost/aa.mp4" ; 
     Media media = new Media(VUrl) ; 
      mediaplayer = new MediaPlayer(media) ; 
      mv.setFitHeight(600);
      mv.setFitWidth(500);
     mv.setMediaPlayer(mediaplayer) ; 
    }   
   
    @FXML
    private void onClick_btn_stop(){
    
    mediaplayer.stop() ; 
    
    
    }
    @FXML
    private void onClick_btn_play(){
        if(mediaplayer.getStatus()==PLAYING){
        
        mediaplayer.stop() ; 
        mediaplayer.play();
        }else{
        
        mediaplayer.play() ; 
        
        }
    
    
    
    
    
    }
    
}
