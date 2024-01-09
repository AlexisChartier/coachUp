package coachup.model;

import java.util.Arrays;

/**
 * La classe Coach représente un utilisateur de type coach.
 * Un coach hérite des propriétés d'un utilisateur et possède des caractéristiques spécifiques telles que des catégories, disponibilités, un diplôme, un prix et un statut d'approbation.
 */
public class Coach extends User {

    private Integer[] categories = {};
    private Integer[] disponibilites = {};
    private String diplome;
    private int prix;
    private boolean approved = false;

    /**
     * Constructeur par défaut de la classe Coach.
     * Initialise les propriétés à leurs valeurs par défaut.
     */
    public Coach() {
        super.setRole("coach");
    }

    /**
     * Constructeur de la classe Coach avec des paramètres spécifiés.
     *
     * @param idCoach        L'identifiant du coach.
     * @param nom            Le nom du coach.
     * @param email          L'adresse e-mail du coach.
     * @param motDePasse     Le mot de passe du coach.
     * @param categories     Les catégories dans lesquelles le coach excelle.
     * @param disponibilites Les disponibilités du coach.
     * @param diplome        Le diplôme du coach.
     * @param approved       Le statut d'approbation du coach.
     * @param prix           Le prix des services du coach.
     */
    public Coach(int idCoach, String nom, String email, String motDePasse, Integer[] categories, Integer[] disponibilites, String diplome, boolean approved, int prix) {
        super(idCoach, nom, email, motDePasse, "coach");
        this.categories = categories;
        this.disponibilites = disponibilites;
        this.diplome = diplome;
        this.approved = approved;
        this.prix = prix;
    }

    /**
     * Obtient les catégories dans lesquelles le coach excelle.
     *
     * @return Les catégories du coach.
     */
    public Integer[] getCategories() {
        return categories;
    }

    /**
     * Définit les catégories dans lesquelles le coach excelle.
     *
     * @param categories Les catégories à définir.
     */
    public void setCategories(Integer[] categories) {
        this.categories = categories;
    }

    /**
     * Obtient les disponibilités du coach.
     *
     * @return Les disponibilités du coach.
     */
    public Integer[] getDisponibilites() {
        return disponibilites;
    }

    /**
     * Définit les disponibilités du coach.
     *
     * @param disponibilites Les disponibilités à définir.
     */
    public void setDisponibilites(Integer[] disponibilites) {
        this.disponibilites = disponibilites;
    }

    /**
     * Obtient le diplôme du coach.
     *
     * @return Le diplôme du coach.
     */
    public String getDiplome() {
        return diplome;
    }

    /**
     * Définit le diplôme du coach.
     *
     * @param diplome Le diplôme à définir.
     */
    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    /**
     * Obtient le prix des services du coach.
     *
     * @return Le prix du coach.
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Définit le prix des services du coach.
     *
     * @param prix Le prix à définir.
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Obtient le statut d'approbation du coach.
     *
     * @return Le statut d'approbation du coach.
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Définit le statut d'approbation du coach.
     *
     * @param approved Le statut d'approbation à définir.
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Obtient une représentation sous forme de chaîne de caractères de l'objet Coach.
     *
     * @return Une représentation sous forme de chaîne de caractères de l'objet Coach.
     */
    @Override
    public String toString() {
        return "Coach{" +
                "idCoach=" + getIdUtilisateur() +
                ", nom='" + getNom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", motDePasse='" + getMotDePasse() + '\'' +
                ", role='" + getRole() + '\'' +
                ", categories=" + Arrays.toString(categories) +
                ", disponibilites=" + Arrays.toString(disponibilites) +
                ", diplome='" + diplome + '\'' +
                ", prix=" + prix +
                ", approved=" + approved +
                '}';
    }

    public boolean getApproved() {
        return approved;
    }
}
