package paw.trainingService;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FXMLTrainingConseilsController implements Initializable {

    @FXML
    private JFXButton butt;
    @FXML 
    private WebView web1;
    private WebEngine engine1;
    @FXML 
    private WebView web2;
    private WebEngine engine2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         engine1 = web1.getEngine();
         engine1.load("http://youtube.com/embed/yMGKTrwSyuY?enablejsapi=1&origin");
         engine2 = web2.getEngine();
         engine2.load("http://youtube.com/embed/jFMA5ggFsXU?enablejsapi=1&origin");
         
//                    http://www.youtube.com/embed/M7lc1UVf-VE?enablejsapi=1&origin
    }    

   
        
        
    
}
