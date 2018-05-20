/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.boutique.user.achat;

import Entity.Achat;
import Entity.LigneAchat;
import Entity.Utilisateur;
import Service.AchatService;

import Service.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.TreeTableColumn;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static paw.Paw.session;
import paw.mainUI.FXMLCnxController;

public class FXMLMesAchatController{
    AchatService achatservice;
    Utilisateur user ;
    List<Achat> mes_achat ;
    List<LigneAchat> ligneachat;
    int nbr_page;
    
    @FXML
    private StackPane stackPane;


    @FXML
    private JFXDialogLayout layout;

    @FXML
    private JFXTreeTableView<LigneAchat> consulterTreeView;

    @FXML
    private TreeTableColumn<LigneAchat, ImageView> image_cons;

    @FXML
    private TreeTableColumn<LigneAchat, String> libelle_cons;

    @FXML
    private TreeTableColumn<LigneAchat, Number> quantite_cons;

    @FXML
    private TreeTableColumn<LigneAchat, Number> prix_cons;
    
    
    @FXML
    private VBox box1;


    @FXML
    private Label etatBox1;



    @FXML
    private Label prixBox1;


    @FXML
    private Label dateBox1;


    @FXML
    private JFXButton payer1;
    @FXML
    private Label nbrArticlebox1;
    @FXML
    private Label nbrArticlebox2;
    @FXML
    private Label nbrArticlebox3;
    @FXML
    private JFXButton consulter1;
    @FXML
    private VBox box2;

    @FXML
    private Label etatBox2;


    @FXML
    private Label prixBox2;


    @FXML
    private Label dateBox2;


    @FXML
    private JFXButton payer2;

    @FXML
    private JFXButton consulter2;
    @FXML
    private VBox box3;

    @FXML
    private Label etatBox3;


    @FXML
    private Label prixBox3;


    @FXML
    private Label dateBox3;


    @FXML
    private JFXButton payer3;

    @FXML
    private JFXButton consulter3;

    @FXML
    private Pagination paginator;

    @FXML
    private StackPane stackPaneconsulter;
   @FXML
    private JFXButton fermer;
   @FXML
    void initialize() {
        achatservice = AchatService.getAchatService();
        mes_achat = achatservice.findAll(session.getId());

        if (mes_achat.isEmpty()) {
            achatVide();
        } else {
            setNbPages();
            initachatPage(0);
        }        
    }
    
    
    
     public void setNbPages() {
        if (mes_achat.size() % 3 != 0) {
            nbr_page = (mes_achat.size() / 3) + 1;
        } else {
            nbr_page = mes_achat.size() / 3;
        }
        paginator.setPageCount(nbr_page);
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initachatPage(newIndex.intValue());
        });
    }
     
    public List<Achat> getArticlesPage(int index) {
        int start = 3 * index;
        int fin = start + 3;
        if (mes_achat.size() > start) {
            if (mes_achat.size() > fin) {
                return mes_achat.subList(start, fin);
            } else {
                return mes_achat.subList(start, mes_achat.size());
            }
        }
        return mes_achat.subList(0, 2);
    } 
     
    public void initachatPage(int index) {
        stackPane.setVisible(false);
        paginator.setCurrentPageIndex(index);
        List<Achat> article_page = getArticlesPage(index);       
        if (article_page.size() >= 1) {
            box1.setVisible(true);         
            consulter1.setOnAction((event) -> {
                consulter(article_page.get(0).getList());
            });
            
            nbrArticlebox1.setText("NOMBRE ARTICLE : "+String.valueOf(article_page.get(0).getList().size()));
            etatBox1.setText(article_page.get(0).getEtat());
            dateBox1.setText(article_page.get(0).getDate_achat().toString());
            prixBox1.setText("PRIX TOTAL : "+String.valueOf(article_page.get(0).getPrix()) + " DT");
            
        } else {
            
            box1.setVisible(false);
            
        }

        ///////////////////////////////////////////////////////
         if (article_page.size() >= 2) {
            box2.setVisible(true);
            consulter2.setOnAction((event) -> {
                consulter(article_page.get(1).getList());
            });
        
            etatBox2.setText(article_page.get(1).getEtat());
            dateBox2.setText(article_page.get(1).getDate_achat().toString());
            prixBox2.setText("PRIX TOTAL : "+String.valueOf(article_page.get(1).getPrix()) + " DT");
            nbrArticlebox2.setText("NOMBRE ARTICLE : "+String.valueOf(article_page.get(1).getList().size()));
            
        } else {
            
            box2.setVisible(false);
            
        }
        ///////////////////////////////////////////////////////////
        
         if (article_page.size() >= 3) {
            box3.setVisible(true);          
            consulter3.setOnAction((event) -> {
                consulter(article_page.get(2).getList());
            });
        
            etatBox3.setText(article_page.get(2).getEtat());
            dateBox3.setText(article_page.get(2).getDate_achat().toString());
            prixBox3.setText("PRIX TOTAL : "+String.valueOf(article_page.get(2).getPrix()) + " DT");
            nbrArticlebox3.setText("NOMBRE ARTICLE : "+String.valueOf(article_page.get(2).getList().size()));
            
        } else {
            
            box3.setVisible(false);
            
        }  
    }
    
    public void achatVide() {
      stackPane.setVisible(true);
      box1.setVisible(false);
      box2.setVisible(false);
      box3.setVisible(false);
      paginator.setVisible(false);
    }
    
    
    public void consulter(List<LigneAchat> liste)
    {   JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(layout);
        JFXDialog dialog = new JFXDialog(stackPaneconsulter, content, JFXDialog.DialogTransition.CENTER);
        
        fermer.setOnAction((event) -> {
            dialog.close();
            stackPaneconsulter.setVisible(false);
        });
            ProduitService produitservice = ProduitService.getProduitService();
         image_cons.setCellValueFactory(param -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            LigneAchat ligne = (LigneAchat) param.getValue().getValue();
            ImageView im = new ImageView();
            try {
              Image  img = new Image("http://localhost/paw_web/web/images/pawBoutique/" + ligne.getProduit().getImage1());
                im.setFitHeight(100);
                im.setFitWidth(100);
                im.setImage(img);

            } catch (Exception ex) {
                System.out.println("image non charger");
            }
            property.set(im);
            return property;
        });
        libelle_cons.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.set(achat.getProduit().getLibelle());
            return property;
        });
        
        
         quantite_cons.setCellValueFactory(param -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.setValue(achat.getNbr_produit());
            return property;
        });
        
        
          prix_cons.setCellValueFactory(param -> {
            SimpleDoubleProperty property = new SimpleDoubleProperty();
            LigneAchat achat = (LigneAchat) param.getValue().getValue();
            property.set(achat.getProduit().getPrix()*achat.getNbr_produit());
            return property;
        });
        ObservableList<LigneAchat> achats = FXCollections.observableArrayList(liste);
        TreeItem<LigneAchat> root = new RecursiveTreeItem<>(achats, RecursiveTreeObject::getChildren);
        consulterTreeView.setRoot(root);
        consulterTreeView.setShowRoot(false);
        stackPaneconsulter.setVisible(true);
        dialog.setVisible(true);
        dialog.show();
        dialog.setOnDialogClosed((event) -> {
            stackPaneconsulter.setVisible(false);
        });
    }

    


    
    
    
    
}
