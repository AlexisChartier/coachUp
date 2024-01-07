package coachup.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coach extends User {

    private ArrayList<Integer> categories;
    private ArrayList<Integer> disponibilites;

    private String diplome;

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


    public Coach(){}


    public Coach(int idCoach, String nom, String email, String motDePasse, ArrayList<Integer> categories, ArrayList<Integer> disponibilites, String diplome) {
        super(idCoach, nom, email, motDePasse, "coach");
        this.categories = categories;
        this.disponibilites = disponibilites;
        this.diplome = diplome;
    }

    public ArrayList<Integer> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Integer> categories) {
        this.categories = categories;
    }

    public ArrayList<Integer> getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(ArrayList<Integer> disponibilites) {
        this.disponibilites = disponibilites;
    }

    public String getDiplome() {
        return diplome;
    }
}
