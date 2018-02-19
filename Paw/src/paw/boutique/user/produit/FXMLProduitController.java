/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.boutique.user.produit;

import Entity.Panier;
import Entity.Produit;
import Service.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import paw.mainUI.FXMLCnxController;

public class FXMLProduitController {

    ProduitService produitservice;
    private List<Produit> all_articles;
    private int nbr_page;
    
    @FXML
    private StackPane dialogPane;
    
    @FXML
    private JFXDialogLayout layout;
    
    @FXML
    private ImageView image_id;
    
    @FXML
    private Label libelle_detail;
    
    @FXML
    private Label prix_detail;
    
    @FXML
    private Text description_detail;
    
    @FXML
    private JFXButton acheter;
    
    @FXML
    private JFXButton fermer;
    
    @FXML
    private Pane imageBox;
    
    @FXML
    private ImageView image_1;
    
    @FXML
    private ImageView image_2;
    
    
    @FXML
    private Label etat_detail;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane div1;
    
    @FXML
    private ImageView im1;
    
    @FXML
    private Label prix_produit1;
    
    @FXML
    private Label libelle_produit1;
    
    @FXML
    private Tooltip tool1;
    
    @FXML
    private JFXButton acheter1;
    
    @FXML
    private JFXButton detail1;
    
    @FXML
    private Pane div2;
    
    @FXML
    private ImageView im2;
    
    @FXML
    private Label prix_produit2;
    
    @FXML
    private Label libelle_produit2;
    
    @FXML
    private Tooltip tool2;
    
    @FXML
    private JFXButton acheter2;
    
    @FXML
    private JFXButton detail2;
    
    @FXML
    private Pane div3;
    
    @FXML
    private ImageView im3;
    
    @FXML
    private Label libelle_produit3;
    
    @FXML
    private Tooltip tool3;
    
    @FXML
    private Tooltip tool4;
    
    @FXML
    private Label prix_produit3;
    
    @FXML
    private JFXButton acheter3;
    
    @FXML
    private JFXButton detail3;
    
    @FXML
    private Pagination pagination;
    
    @FXML
    private Pane div4;
    
    @FXML
    private ImageView im4;
    
    @FXML
    private Label libelle_produit4;
    
    @FXML
    private Label prix_produit4;
    
    @FXML
    private JFXButton acheter4;
    
    @FXML
    private JFXButton detail4;
    
    @FXML
    private StackPane info;
    
    @FXML
    private JFXRadioButton tous;
    
    @FXML
    private JFXRadioButton laisse;
    
    @FXML
    private JFXRadioButton lit;
    
    @FXML
    private JFXRadioButton Shampoings;
    
    @FXML
    private JFXRadioButton vetement;
    
    @FXML
    private JFXRadioButton Bols;
    
    @FXML
    private JFXRadioButton Cadeaux;
    
    @FXML
    private JFXRadioButton Gâteries;
    
    @FXML
    private JFXRadioButton Jouets;
    
    @FXML
    private Pane div_articles;
    @FXML
    private ImageView acheter_produit1;
    @FXML
    private ImageView acheter_produit11;
    @FXML
    private Pane filter_type;
    @FXML
    private Label filt;
    @FXML
    private ToggleGroup type;
    @FXML
    private JFXButton filter;
    @FXML
    private ImageView acheter_produit114;
    @FXML
    private ImageView acheter_produit111;
    @FXML
    private ImageView acheter_produit112;
    @FXML
    private ImageView acheter_produit113;
     @FXML
    void initialize() {
        
        produitservice = ProduitService.getProduitService();
        all_articles = produitservice.findAll();
        if (all_articles.isEmpty()) {
            boutiqueVide();
        } else {
            setNbPages();
            initBoutiquePage(0);
        }        
    }

    public void setNbPages() {
        if (all_articles.size() % 4 != 0) {
            nbr_page = (all_articles.size() / 4) + 1;
        } else {
            nbr_page = all_articles.size() / 4;
        }
        pagination.setPageCount(nbr_page);
        
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initBoutiquePage(newIndex.intValue());
        });
    }
    
    public List<Produit> getArticlesPage(int index) {
        int start = 4 * index;
        int fin = start + 4;
        if (all_articles.size() > start) {
            if (all_articles.size() > fin) {
                return all_articles.subList(start, fin);
            } else {
                return all_articles.subList(start, all_articles.size());
            }
        }
        return all_articles.subList(0, 3);
    }
    
    @FXML
    void filtrerBoutique(ActionEvent event) {
        
        if (tous.isSelected()) {
            all_articles = produitservice.findAll();
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {                
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (laisse.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Laisse, Collier et Harnais");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (lit.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Lits et Couvertures");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (Shampoings.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Shampoings et Conditionneurs");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (vetement.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Vetements");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (Bols.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Bols");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (Cadeaux.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Cadeaux");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (Gâteries.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Gâteries Et Nourritures");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        } else if (Jouets.isSelected()) {
            all_articles = produitservice.findAllFiltrer("Jouets");
            if (all_articles.isEmpty()) {
                boutiqueVide();
            } else {
                setNbPages();
                initBoutiquePage(0);
            }            
        }
        
    }
    
    public void initBoutiquePage(int index) {
        pagination.setCurrentPageIndex(index);
        info.setVisible(false);
        List<Produit> article_page = getArticlesPage(index);
        System.out.println("nombre articles in this page " + article_page.size());        
        if (article_page.size() >= 1) {
            div1.setVisible(true);
            if (article_page.get(0).getQuantite() == 0) {
                acheter1.setDisable(true);
            } else {
                acheter1.setDisable(false);
                acheter1.setOnAction((event) -> {
                    Panier.addProduit(article_page.get(0));
                    
                });
            }            
            detail1.setOnAction((event) -> {
                showdetail(article_page.get(0));
            });
            
            Image img = new Image("file:///" + article_page.get(0).getImages().get(0).toPath().toString());
            im1.setImage(img);
            im1.setOnMouseClicked(value -> {
                
            });
            
            libelle_produit1.setText(article_page.get(0).getLibelle());
            tool1.setText(article_page.get(0).getLibelle());
            prix_produit1.setText(String.valueOf(article_page.get(0).getPrix()) + " DT");
            
        } else {
            
            div1.setVisible(false);
            
        }

        ///////////////////////////////////////////////////////
        if (article_page.size() >= 2) {
            div2.setVisible(true);
            if (article_page.get(1).getQuantite() == 0) {
                acheter2.setDisable(true);
            } else {
                acheter2.setDisable(false);
                acheter2.setOnAction((event) -> {
                    Panier.addProduit(article_page.get(1));
                });
            }            
            detail2.setOnAction((event) -> {
                showdetail(article_page.get(1));
            });
            
            Image img = new Image("file:///" + article_page.get(1).getImages().get(0).toPath().toString());
            im2.setImage(img);
            im2.setOnMouseClicked(value -> {
                
            });
            
            libelle_produit2.setText(article_page.get(1).getLibelle());
            tool2.setText(article_page.get(1).getLibelle());
            prix_produit2.setText(String.valueOf(article_page.get(1).getPrix()) + " DT");
            
        } else {
            
            div2.setVisible(false);
            
        }
        ///////////////////////////////////////////////////////////
        
        if (article_page.size() >= 3) {
            div3.setVisible(true);
            if (article_page.get(2).getQuantite() == 0) {
                acheter3.setDisable(true);
            } else {
                acheter3.setDisable(false);
                acheter3.setOnAction((event) -> {
                Panier.addProduit(article_page.get(2));    
                });
            }            
            detail3.setOnAction((event) -> {
                showdetail(article_page.get(2));
            });
            
            Image img = new Image("file:///" + article_page.get(2).getImages().get(0).toPath().toString());
            im3.setImage(img);
            im3.setOnMouseClicked(value -> {
                
            });
            
            libelle_produit3.setText(article_page.get(2).getLibelle());
            tool3.setText(article_page.get(2).getLibelle());
            prix_produit3.setText(String.valueOf(article_page.get(2).getPrix()) + " DT");
            
        } else {
            
            div3.setVisible(false);
            
        }

        ////////////////////////////////////////////
        if (article_page.size() >= 4) {
            div4.setVisible(true);
            if (article_page.get(3).getQuantite() == 0) {
                acheter4.setDisable(true);
            } else {
                acheter4.setDisable(false);
                acheter4.setOnAction((event) -> {
                    Panier.addProduit(article_page.get(3));
                });
            }            
            detail4.setOnAction((event) -> {
                showdetail(article_page.get(3));
            });
            
            Image img = new Image("file:///" + article_page.get(3).getImages().get(0).toPath().toString());
            im4.setImage(img);
            im4.setOnMouseClicked(value -> {
                
            });
            
            libelle_produit4.setText(article_page.get(3).getLibelle());
            tool4 = new Tooltip(article_page.get(3).getLibelle());
            prix_produit4.setText(String.valueOf(article_page.get(3).getPrix()) + " DT");
            
        } else {
            
            div4.setVisible(false);
            
        }
    }
    
    public void boutiqueVide() {      
        pagination.setPageCount(1);
        info.setVisible(true);
        div1.setVisible(false);
        div2.setVisible(false);
        div3.setVisible(false);
        div4.setVisible(false);
        
    }
    
    public void showdetail(Produit article) {
        
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(layout);
        JFXDialog dialog = new JFXDialog(dialogPane, content, JFXDialog.DialogTransition.CENTER);
        
        fermer.setOnAction((event) -> {
            dialog.close();
            dialogPane.setVisible(false);
        });
        libelle_detail.setText(article.getLibelle());
        description_detail.setText(article.getDescription());
        prix_detail.setText(String.valueOf(article.getPrix()) + " TND");
        etat_detail.setVisible(false);
        
        if (article.getQuantite() == 0) {
            acheter.setDisable(true);
            etat_detail.setText("OUT OF STOCK");
            etat_detail.setVisible(true);
        } else {
            acheter.setDisable(false);
            etat_detail.setVisible(false);
        }
        acheter.setOnAction((event) -> {
           Panier.addProduit(article);  
        });
        
        Image img = new Image("file:///" + article.getImages().get(0).toPath().toString());
        image_id.setImage(img);
        
        img = new Image("file:///" + article.getImages().get(0).toPath().toString());
        
        image_1.setImage(img);
        
        image_1.setVisible(true);
        
        image_1.setOnMouseClicked((event) -> {
            
            image_id.setImage(image_1.getImage());
            
        });
        
        img = new Image("file:///" + article.getImages().get(1).toPath().toString());
        image_2.setImage(img);
        image_2.setVisible(true);
        image_2.setOnMouseClicked((event) -> {
            
            image_id.setImage(image_2.getImage());
            
        });
        
        dialogPane.setVisible(true);
        dialog.show();
        
        dialog.setOnDialogClosed((event) -> {
            dialogPane.setVisible(false);
        });
        
    }
    
}
