package coachup.dao;

import coachup.model.Creneau_dispo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface décrivant les opérations CRUD (Create, Read, Update, Delete) pour la gestion des créneaux disponibles dans une base de données.
 */
public abstract class CreneauDispoDAO {

    /**
     * Ajoute un créneau disponible à la base de données.
     *
     * @param creneau_dispo L'objet Creneau_dispo à ajouter.
     * @return L'identifiant du créneau ajouté.
     */
    public abstract int addCreneauDispo(Creneau_dispo creneau_dispo);


    /**
     * Retourne le creneaudispo d'id
     * @param id
     * @return
     */
    public abstract Creneau_dispo getCreneauById(int id) throws SQLException;

    /**
     * Renvoie une liste de créneaux disponibles pour une date spécifiée.
     *
     * @param year  L'année de la date.
     * @param month Le mois de la date.
     * @param day   Le jour de la date.
     * @return Une liste d'objets Creneau_dispo correspondant à la date spécifiée.
     */
    public abstract List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day);

    /**
     * Marque un créneau comme réservé dans la base de données.
     *
     * @param id L'identifiant du créneau à marquer comme réservé.
     * @throws SQLException Si une erreur survient lors de l'accès à la base de données.
     */
    public abstract void setReserved(int id) throws SQLException;

    /**
     * Supprime un créneau disponible de la base de données.
     *
     * @param creneauDispoId L'identifiant du créneau à supprimer.
     * @return True si la suppression a réussi, False sinon.
     */
    public abstract boolean deleteCreneauDispo(int creneauDispoId);

    /**
     * Modifie les informations d'un créneau disponible dans la base de données.
     *
     * @param creneau_dispo L'objet Creneau_dispo modifié.
     * @return True si la modification a réussi, False sinon.
     */
    public abstract boolean modifyCreneauDispo(Creneau_dispo creneau_dispo);

    /**
     * Renvoie une liste de créneaux disponibles pour une date spécifiée et un coach spécifié.
     *
     * @param year    L'année de la date.
     * @param month   Le mois de la date.
     * @param day     Le jour de la date.
     * @param coachId L'identifiant du coach.
     * @return Une liste d'objets Creneau_dispo correspondant à la date et au coach spécifiés.
     */
    public abstract List<Creneau_dispo> getCreneauByDayAndCoachId(Integer year, Integer month, Integer day, Integer coachId);

    /**
     * Réserve un créneau disponible dans la base de données.
     *
     * @param creneau_dispo L'objet Creneau_dispo à réserver.
     * @param iduser        L'identifiant de l'utilisateur réservant le créneau.
     * @param idcategorie   L'identifiant de la catégorie associée à l'utilisateur.
     * @throws SQLException Si une erreur survient lors de l'accès à la base de données.
     */
    public abstract void reserverCreneau(Creneau_dispo creneau_dispo, int iduser, int idcategorie) throws SQLException;
}
