package coachup.model;

import java.sql.Array;

public class Coach extends User {

    private String categories;
    private Array disponibilites;

    public Coach(int idCoach, String nom, String email, String motDePasse, String categories, Array disponibilites) {
        super(idCoach, nom, email, motDePasse, "coach");
        this.categories = categories;
        this.disponibilites = disponibilites;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Array getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(Array disponibilites) {
        this.disponibilites = disponibilites;
    }
}
