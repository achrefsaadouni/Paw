/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;

/**
 *
 * @author gmehd
 */
public class Vets {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String region;
    private int numero;
    private String email;
    private Float rate;
    private double longitude;
    private double latitude;
    private File images;

    public Vets(Veterinaire v, Float rate) {
        this.rate = rate;
        this.id = v.getId();
        this.nom = v.getNom();
        this.prenom = v.getPrenom();
        this.adresse = v.getAdresse();
        this.region = v.getRegion();
        this.numero = v.getNumero();
        this.email = v.getEmail();
        this.latitude=v.getLatitude();
        this.longitude=v.getLongitude();
        this.images = v.getImages();
        
    }

    public Vets(int id, String nom, String prenom, String adresse, String region, int numero, String email, Float rate, double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.region = region;
        this.numero = numero;
        this.email = email;
        this.rate = rate;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Vets(int id, String nom, String prenom, String adresse, String region, int numero, String email, Float rate, double longitude, double latitude, File images) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.region = region;
        this.numero = numero;
        this.email = email;
        this.rate = rate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.images = images;
        
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public File getImages() {
        return images;
    }

    public void setImages(File images) {
        this.images = images;
    }
}
