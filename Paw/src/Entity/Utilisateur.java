/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author AYOUB
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String addresse;
    private String email;
    private String username;
    private String password;
    private String role;
    private String sexe;
    private int numero;
    private String avatar;
    private Date dateInscription;

    public Utilisateur(int id, String nom, String prenom,  String email, String username, String password,String addresse, int numero, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.numero = numero;
    }

    public Utilisateur(int id, String nom, String prenom, String addresse, String email, String username, String password, String role, int numero, String avatar, Date dateInscription, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.numero = numero;
        this.avatar = avatar;
        this.dateInscription = dateInscription;
        this.sexe = sexe;
    }

    public Utilisateur(int id_u, String nom_u, String prenom_u, int numero_u, String email_u, String adresse_u) {
        this.id = id_u;
        this.nom = nom_u;
        this.prenom = prenom_u;
        this.addresse = adresse_u;
        this.email = email_u;
        this.numero = numero_u;
    }

   

    @Override
    public String toString() {
        return ("{"+id+":"+nom+":"+prenom+":"+addresse+":"+email+":"+username+":"+password+":"+role+":"+numero+"}");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
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

    public String getAddresse() {
        return addresse;
    }

    public void setAdresse(String adresse) {
        this.addresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    
}
