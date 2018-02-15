package paw.veterinaires;

import Entity.Vets;
import Service.VeterinaireServices;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.Rating;


public class FXMLVeterinairesController implements Initializable {

    @FXML
    private Label nom1;
    @FXML
    private Label adr1;
    @FXML
    private Label mail1;
    @FXML
    private Label nom2;
    @FXML
    private Label adr2;
    @FXML
    private Label mail2;
    @FXML
    private Label nom3;
    @FXML
    private Label adr3;
    @FXML
    private Label mail3;
    @FXML
    private Label nom4;
    @FXML
    private Label adr4;
    @FXML
    private Label mail4;
    @FXML
    private Label tel1;
    @FXML
    private Label tel2;
    @FXML
    private Label tel3;
    @FXML
    private Label tel4;
    @FXML
    private Label reg1;

    @FXML
    private Label reg2;

    @FXML
    private Label reg3;

    @FXML
    private Label reg4;
    @FXML
    private Rating rate1;
    @FXML
    private Rating rate2;
    @FXML
    private Rating rate3;
    @FXML
    private Rating rate4;
    @FXML
    private Label note1;
    @FXML
    private Label note2;
    @FXML
    private Label note3;
    @FXML
    private Label note4;
    @FXML
    private AnchorPane box1;
    @FXML
    private AnchorPane box2;
    @FXML
    private AnchorPane box3;
    @FXML
    private AnchorPane box4;
    @FXML
    private Pagination paginator;

    ArrayList<Vets> list;
    @FXML
    private StackPane vide;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VeterinaireServices service = new VeterinaireServices();

        list = service.getList();
        System.out.println(list);
        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
            vide.setVisible(true);
        } else {
            setNbPages();
            initVeterinairePage(0);
            vide.setVisible(false);
        }


    }

    private void setNbPages() {
        if (list.size() % 4 != 0) {
            paginator.setPageCount((list.size() / 4) + 1);
        } else {
            paginator.setPageCount(list.size() / 4);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initVeterinairePage(newIndex.intValue());
        });
    }

    public List<Vets> getVeterinairesPage(int index) {

        int start = 4 * index;
        int fin = start + 4;
        if (list.size() > start) {
            if (list.size() > fin) {
                return list.subList(start, fin);
            } else {
                return list.subList(start, list.size());
            }
        }
        return list.subList(0, 3);
    }

    private void initVeterinairePage(int index) {
        DecimalFormat df = new DecimalFormat("#.#");
        paginator.setCurrentPageIndex(index);
        List<Vets> QuatreVeterinaires = getVeterinairesPage(index);
        if (QuatreVeterinaires.size() >= 1) {
            box1.setVisible(true);

            nom1.setText(QuatreVeterinaires.get(0).getNom() + " " + QuatreVeterinaires.get(0).getPrenom());
            mail1.setText(QuatreVeterinaires.get(0).getEmail());
            tel1.setText(String.valueOf(QuatreVeterinaires.get(0).getNumero()));
            adr1.setText(QuatreVeterinaires.get(0).getAdresse());
            reg1.setText(QuatreVeterinaires.get(0).getRegion());
            rate1.setRating(QuatreVeterinaires.get(0).getRate());
            if ((QuatreVeterinaires.get(0).getRate() != 0) && (QuatreVeterinaires.get(0).getRate() != 1) && (QuatreVeterinaires.get(0).getRate() != 2) && (QuatreVeterinaires.get(0).getRate() != 3) && (QuatreVeterinaires.get(0).getRate() != 4) && (QuatreVeterinaires.get(0).getRate() != 5)) {
                note1.setText(String.valueOf(df.format(QuatreVeterinaires.get(0).getRate())) + "/5");
            } else {
                note1.setText(String.valueOf((QuatreVeterinaires.get(0).getRate()).intValue()) + "/5");
            }

        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (QuatreVeterinaires.size() >= 2) {
            box2.setVisible(true);

            nom2.setText(QuatreVeterinaires.get(1).getNom() + " " + QuatreVeterinaires.get(1).getPrenom());
            mail2.setText(QuatreVeterinaires.get(1).getEmail());
            tel2.setText(String.valueOf(QuatreVeterinaires.get(1).getNumero()));
            adr2.setText(QuatreVeterinaires.get(1).getAdresse());
            reg2.setText(QuatreVeterinaires.get(1).getRegion());
            rate2.setRating(QuatreVeterinaires.get(1).getRate());
            if ((QuatreVeterinaires.get(1).getRate() != 0) && (QuatreVeterinaires.get(1).getRate() != 1) && (QuatreVeterinaires.get(1).getRate() != 2) && (QuatreVeterinaires.get(1).getRate() != 3) && (QuatreVeterinaires.get(1).getRate() != 4) && (QuatreVeterinaires.get(1).getRate() != 5)) {
                note2.setText(String.valueOf(df.format(QuatreVeterinaires.get(1).getRate())) + "/5");
            } else {
                note2.setText(String.valueOf((QuatreVeterinaires.get(1).getRate()).intValue()) + "/5");
            }

        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (QuatreVeterinaires.size() >= 3) {
            box3.setVisible(true);

            nom3.setText(QuatreVeterinaires.get(2).getNom() + " " + QuatreVeterinaires.get(2).getPrenom());
            mail3.setText(QuatreVeterinaires.get(2).getEmail());
            tel3.setText(String.valueOf(QuatreVeterinaires.get(2).getNumero()));
            adr3.setText(QuatreVeterinaires.get(2).getAdresse());
            reg3.setText(QuatreVeterinaires.get(2).getRegion());
            rate3.setRating(QuatreVeterinaires.get(2).getRate());
            if ((QuatreVeterinaires.get(2).getRate() != 0) && (QuatreVeterinaires.get(2).getRate() != 1) && (QuatreVeterinaires.get(2).getRate() != 2) && (QuatreVeterinaires.get(2).getRate() != 3) && (QuatreVeterinaires.get(2).getRate() != 4) && (QuatreVeterinaires.get(2).getRate() != 5)) {
                note3.setText(String.valueOf(df.format(QuatreVeterinaires.get(2).getRate())) + "/5");
            } else {
                note3.setText(String.valueOf((QuatreVeterinaires.get(2).getRate()).intValue()) + "/5");
            }

        } else {
            box3.setVisible(false);
        }

        ///////////////////////////////////////////////////////////
        if (QuatreVeterinaires.size() >= 4) {
            box4.setVisible(true);

            nom4.setText(QuatreVeterinaires.get(3).getNom() + " " + QuatreVeterinaires.get(3).getPrenom());
            mail4.setText(QuatreVeterinaires.get(3).getEmail());
            tel4.setText(String.valueOf(QuatreVeterinaires.get(3).getNumero()));
            adr4.setText(QuatreVeterinaires.get(3).getAdresse());
            reg4.setText(QuatreVeterinaires.get(3).getRegion());
            rate4.setRating(QuatreVeterinaires.get(3).getRate());
            if ((QuatreVeterinaires.get(3).getRate() != 0) && (QuatreVeterinaires.get(3).getRate() != 1) && (QuatreVeterinaires.get(3).getRate() != 2) && (QuatreVeterinaires.get(3).getRate() != 3) && (QuatreVeterinaires.get(3).getRate() != 4) && (QuatreVeterinaires.get(3).getRate() != 5)) {
                note4.setText(String.valueOf(df.format(QuatreVeterinaires.get(3).getRate())) + "/5");
            } else {
                note4.setText(String.valueOf((QuatreVeterinaires.get(3).getRate()).intValue()) + "/5");
            }

        } else {
            box4.setVisible(false);
        }

    }

    @FXML
    private void actionProfil(ActionEvent event) {
        
    }

    @FXML
    private void actionRate(ActionEvent event) {
        
    }

}
