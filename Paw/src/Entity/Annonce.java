/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Guideinfo
 */
public class Annonce

{
   private int id  ; 
   private int age ; 
   private String couleur ;
   private String sex ; 
   private String race ; 
   private String message_complementaire ; 
   private String type  ; 
   private Date date;
   private File images;
   private int id_utilisateur;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Annonce() {
    }
     public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type, Date date,File images) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.date=date;
        this.images=images ; 
    }
    public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type, Date date) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.date=date;
    }
    
    public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type, Date date,int id_utilisateur,File images) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.date=date;
        this.id_utilisateur=id_utilisateur;
         this.images = images;
    }
    
                        

      public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type, Date date, int id_utilisateur) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.id_utilisateur=id_utilisateur;
        this.date=date;
    }                        
                        
      public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.id_utilisateur=id_utilisateur;
    }

       public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;

    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCouleur() {
        return couleur;
    }

      public File getImages() {
        return images;
    }

    public void setImages(File images) {
        this.images = images;
    }
    
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMessage_complementaire() {
        return message_complementaire;
    }

    public void setMessage_complementaire(String message_complementaire) {
        this.message_complementaire = message_complementaire;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", age=" + age + ", couleur=" + couleur + ", sex=" + sex + ", race=" + race + ", message_complementaire=" + message_complementaire + ", type=" + type + '}';
    }

   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + this.age;
        hash = 61 * hash + Objects.hashCode(this.couleur);
        hash = 61 * hash + Objects.hashCode(this.sex);
        hash = 61 * hash + Objects.hashCode(this.race);
        hash = 61 * hash + Objects.hashCode(this.message_complementaire);
        hash = 61 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Annonce other = (Annonce) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.couleur, other.couleur)) {
            return false;
        }
        if (!Objects.equals(this.sex, other.sex)) {
            return false;
        }
        if (!Objects.equals(this.race, other.race)) {
            return false;
        }
        if (!Objects.equals(this.message_complementaire, other.message_complementaire)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

   
   
    
    
    
    }
   
   
   
    

