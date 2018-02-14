/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
    
    public Vets(Veterinaire v , Float rate)
    {
        this.rate=rate;
        this.id = v.getId();
        this.nom =v.getNom();
        this.prenom = v.getPrenom();
        this.adresse = v.getAdresse();
        this.region = v.getRegion();
        this.numero = v.getNumero();
        this.email = v.getEmail();
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
}
