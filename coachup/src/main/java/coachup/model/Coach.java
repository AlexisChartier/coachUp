package coachup.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coach extends User {

    private Integer[] categories = {};
    private Integer[] disponibilites = {};

    private String diplome;

    private int prix;

    private boolean approved = false;

    public void setApproved(boolean b){
        approved = b;
    }

    public boolean getApproved(){
        return approved;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isApproved() {
        return approved;
    }

    public Coach(){}


    public Coach(int idCoach, String nom, String email, String motDePasse, Integer[] categories, Integer[] disponibilites, String diplome, boolean approved, int prix) {
        super(idCoach, nom, email, motDePasse, "coach");
        this.categories = categories;
        this.disponibilites = disponibilites;
        this.diplome = diplome;
        this.approved = approved;
        this.prix = prix;
    }

    public Integer[] getCategories() {
        return categories;
    }

    public void setCategories(Integer[] categories) {
        this.categories = categories;
    }

    public Integer[] getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(Integer[] disponibilites) {
        this.disponibilites = disponibilites;
    }

    public String getDiplome() {
        return diplome;
    }


}
