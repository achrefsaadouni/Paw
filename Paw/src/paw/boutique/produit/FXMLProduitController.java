/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.boutique.produit;

import Entity.Produit;
import Service.ProduitService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FXMLProduitController {
    ProduitService produitservice;
    private List<Produit> myproduits;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane p1;

    @FXML
    private ImageView im1;

    @FXML
    private Label libelle_produit1;

    @FXML
    private Label prix_produit1;

    @FXML
    private JFXButton acheter1;

    @FXML
    private ImageView acheter_produit;

    @FXML
    private ImageView acheter_produit1;

    @FXML
    private Pane p2;

    @FXML
    private ImageView im2;

    @FXML
    private Label libelle_produit2;

    @FXML
    private Label prix_produit2;

    @FXML
    private JFXButton acheter2;

    @FXML
    private ImageView acheter_produit11;

    @FXML
    private Pane p3;

    @FXML
    private ImageView im3;

    @FXML
    private Label libelle_produit3;

    @FXML
    private Label prix_produit3;

    @FXML
    private JFXButton acheter3;

    @FXML
    private ImageView acheter_produit13;

    @FXML
    private Pane p4;

    @FXML
    private ImageView im4;

    @FXML
    private Label libelle_produit4;

    @FXML
    private Label prix_produit4;

    @FXML
    private JFXButton acheter4;

    @FXML
    private ImageView acheter_produit14;
       
        @FXML
    void initialize() {
        produitservice = ProduitService.getProduitService();
         myproduits=produitservice.findAll();
         Produit p = myproduits.get(0);
         Produit p1 = myproduits.get(1);
         libelle_produit1.setText(p.getLibelle());
         prix_produit1.setText(String.valueOf(p.getPrix()+" DT"));
         Image img =null;
         try{
         img =new Image("file:///"+p.getImages().get(0).toPath().toString());}
         catch(Exception ex)
             {
                 System.out.println("image non charger");
             }
         im1.setImage(img);
         
         libelle_produit2.setText(p1.getLibelle());
         prix_produit2.setText(String.valueOf(p1.getPrix()+" DT"));
         Image img1 =null;
         try{
         img1 =new Image("file:///"+p1.getImages().get(0).toPath().toString());}
         catch(Exception ex)
             {
                 System.out.println("image non charger");
             }
         im2.setImage(img1);

    }
     
}
