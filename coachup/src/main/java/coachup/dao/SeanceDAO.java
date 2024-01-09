package coachup.dao;

import coachup.model.Seance;

import java.util.List;

/**
 * Interface abstraite définissant les opérations CRUD (Create, Read, Update, Delete) pour l'entité Seance dans la base de données.
 */
public abstract class SeanceDAO {

    /**
     * Récupère toutes les séances disponibles.
     *
     * @return Une liste de toutes les séances.
     */
    public abstract List<Seance> getAllSeances();

    /**
     * Récupère une séance par son identifiant.
     *
     * @param id L'identifiant de la séance à récupérer.
     * @return La séance correspondante, ou null si non trouvée.
     */
    public abstract Seance getSeanceById(int id);

    /**
     * Ajoute une nouvelle séance à la base de données.
     *
     * @param seance La séance à ajouter.
     * @return L'identifiant de la séance ajoutée, ou -1 en cas d'erreur.
     */
    public abstract int addSeance(Seance seance);

    /**
     * Met à jour les informations d'une séance dans la base de données.
     *
     * @param seance La séance à mettre à jour.
     */
    public abstract void updateSeance(Seance seance);

    /**
     * Supprime une séance de la base de données.
     *
     * @param id L'identifiant de la séance à supprimer.
     */
    public abstract void deleteSeance(int id);

    /**
     * Récupère toutes les séances passées pour un utilisateur donné.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste de séances passées pour l'utilisateur.
     */
    public abstract List<Seance> getSeancesPassedByUserId(int id);

    /**
     * Récupère toutes les séances à venir pour un utilisateur donné.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste de séances à venir pour l'utilisateur.
     */
    public abstract List<Seance> getUpcomingSessionsByUserId(int id);

    /**
     * Récupère toutes les séances avec paiements en attente pour un utilisateur donné.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste de séances avec paiements en attente pour l'utilisateur.
     */
    public abstract List<Seance> getPendingPaymentsByUserId(int id);

    /**
     * Marque une séance comme payée dans la base de données.
     *
     * @param idSeance L'identifiant de la séance à marquer comme payée.
     */
    public abstract void paySeance(int idSeance);

    /**
     * Rembourse une séance dans la base de données.
     *
     * @param idSeance L'identifiant de la séance à rembourser.
     */
    public abstract void refundSeance(int idSeance);
}
