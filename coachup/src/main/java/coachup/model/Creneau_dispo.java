package coachup.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * La classe Creneau_dispo représente un créneau de disponibilité d'un coach.
 * Chaque créneau est défini par une date de début, une date de fin, l'identifiant du coach, et l'identifiant du créneau de disponibilité.
 */
public class Creneau_dispo {

    private ZonedDateTime date_debut;
    private ZonedDateTime date_fin;
    private Integer CoachId;
    private Integer Creneau_dispo_id;
    private boolean reserve;

    public Creneau_dispo() {

    }

    /**
     * Constructeur de la classe Creneau_dispo avec des paramètres spécifiés.
     *
     * @param date_debut      La date de début du créneau.
     * @param date_fin        La date de fin du créneau.
     * @param CoachId         L'identifiant du coach associé au créneau.
     * @param Creneau_dispo_id L'identifiant du créneau de disponibilité.
     */
    public Creneau_dispo(ZonedDateTime date_debut, ZonedDateTime date_fin, Integer CoachId, Integer Creneau_dispo_id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.CoachId = CoachId;
        this.Creneau_dispo_id = Creneau_dispo_id;
    }

    /**
     * Vérifie si le créneau est réservé.
     *
     * @return true si le créneau est réservé, false sinon.
     */
    public boolean isReserve() {
        return reserve;
    }

    /**
     * Définit le statut de réservation du créneau.
     *
     * @param reserve Le statut de réservation à définir.
     */
    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    /**
     * Obtient la date de début du créneau.
     *
     * @return La date de début du créneau.
     */
    public ZonedDateTime getDateDebut() {
        return date_debut;
    }

    /**
     * Définit la date de début du créneau.
     *
     * @param date La date de début à définir.
     */
    public void setDateDebut(ZonedDateTime date) {
        this.date_debut = date;
    }

    /**
     * Obtient la date de fin du créneau.
     *
     * @return La date de fin du créneau.
     */
    public ZonedDateTime getDateFin() {
        return date_fin;
    }

    /**
     * Définit la date de fin du créneau.
     *
     * @param date La date de fin à définir.
     */
    public void setDateFin(ZonedDateTime date) {
        this.date_fin = date;
    }

    /**
     * Obtient l'identifiant du coach associé au créneau.
     *
     * @return L'identifiant du coach.
     */
    public Integer getCoachId() {
        return CoachId;
    }

    /**
     * Définit l'identifiant du coach associé au créneau.
     *
     * @param coachid L'identifiant du coach à définir.
     */
    public void setCoachId(Integer coachid) {
        this.CoachId = coachid;
    }

    /**
     * Obtient l'identifiant du créneau de disponibilité.
     *
     * @return L'identifiant du créneau de disponibilité.
     */
    public Integer getCreneauDispoId() {
        return Creneau_dispo_id;
    }

    /**
     * Définit l'identifiant du créneau de disponibilité.
     *
     * @param creneau_dispo_id L'identifiant du créneau de disponibilité à définir.
     */
    public void setCreneauDispoId(Integer creneau_dispo_id) {
        this.Creneau_dispo_id = creneau_dispo_id;
    }

    /**
     * Obtient une représentation sous forme de chaîne de caractères de l'objet Creneau_dispo.
     *
     * @return Une représentation sous forme de chaîne de caractères de l'objet Creneau_dispo.
     */
    @Override
    public String toString() {
        return "Creneau_dispo{" +
                "date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", CoachId=" + CoachId +
                ", Creneau_dispo_id=" + Creneau_dispo_id +
                ", reserve=" + reserve +
                '}';
    }

    /**
     * Obtient la date de début du créneau sous forme de timestamp.
     *
     * @return La date de début du créneau en format timestamp.
     */
    public Timestamp getDateDebutTimestamp() {
        LocalDateTime withoutTimezone = date_debut.toLocalDateTime();
        return Timestamp.valueOf(withoutTimezone);
    }

    /**
     * Obtient la date de fin du créneau sous forme de timestamp.
     *
     * @return La date de fin du créneau en format timestamp.
     */
    public Timestamp getDateFinTimestamp() {
        LocalDateTime withoutTimezone = date_fin.toLocalDateTime();
        return Timestamp.valueOf(withoutTimezone);
    }
}
