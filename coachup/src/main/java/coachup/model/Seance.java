package coachup.model;

import java.util.Date;

/**
 * La classe Seance représente une séance de coaching planifiée entre un coach et un utilisateur.
 * Chaque séance est définie par un identifiant de séance, une date, l'identifiant du coach, l'identifiant de l'utilisateur,
 * l'identifiant de la catégorie et le statut de paiement.
 */
public class Seance {
    private int idSeance;
    private Date date;
    private int idCoach;
    private int idUser;
    private int idCategorie;
    private String statutPaiement;

    /**
     * Constructeur par défaut de la classe Seance.
     */
    public Seance(){}

    /**
     * Obtient l'identifiant de la séance.
     *
     * @return L'identifiant de la séance.
     */
    public int getIdSeance() {
        return idSeance;
    }

    /**
     * Définit l'identifiant de la séance.
     *
     * @param idSeance L'identifiant de la séance à définir.
     */
    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    /**
     * Obtient la date de la séance.
     *
     * @return La date de la séance.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Définit la date de la séance.
     *
     * @param date La date de la séance à définir.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Obtient l'identifiant du coach associé à la séance.
     *
     * @return L'identifiant du coach associé à la séance.
     */
    public int getIdCoach() {
        return idCoach;
    }

    /**
     * Définit l'identifiant du coach associé à la séance.
     *
     * @param idCoach L'identifiant du coach à définir.
     */
    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    /**
     * Obtient l'identifiant de l'utilisateur associé à la séance.
     *
     * @return L'identifiant de l'utilisateur associé à la séance.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Définit l'identifiant de l'utilisateur associé à la séance.
     *
     * @param idUser L'identifiant de l'utilisateur à définir.
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Obtient l'identifiant de la catégorie associée à la séance.
     *
     * @return L'identifiant de la catégorie associée à la séance.
     */
    public int getIdCategorie() {
        return idCategorie;
    }

    /**
     * Définit l'identifiant de la catégorie associée à la séance.
     *
     * @param idCategorie L'identifiant de la catégorie à définir.
     */
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    /**
     * Obtient le statut de paiement de la séance.
     *
     * @return Le statut de paiement de la séance.
     */
    public String getStatutPaiement() {
        return statutPaiement;
    }

    /**
     * Définit le statut de paiement de la séance.
     *
     * @param statutPaiement Le statut de paiement à définir.
     */
    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }
}
