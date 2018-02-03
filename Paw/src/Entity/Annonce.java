/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
   private String TypeAnimal  ; 

    public Annonce() {
    }

    public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String TypeAnimal) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.TypeAnimal = TypeAnimal;
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

    public String getTypeAnimal() {
        return TypeAnimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeAnimal(String TypeAnimal) {
        this.TypeAnimal = TypeAnimal;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", age=" + age + ", couleur=" + couleur + ", sex=" + sex + ", race=" + race + ", message_complementaire=" + message_complementaire + ", TypeAnimal=" + TypeAnimal + '}';
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
        hash = 61 * hash + Objects.hashCode(this.TypeAnimal);
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
        if (!Objects.equals(this.TypeAnimal, other.TypeAnimal)) {
            return false;
        }
        return true;
    }

   
   
    
    
    
    }
   
   
   
    

