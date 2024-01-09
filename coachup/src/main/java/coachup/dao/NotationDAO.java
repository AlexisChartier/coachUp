package coachup.dao;

import coachup.model.Notation;

/**
 * Interface définissant les opérations CRUD (Create, Read, Update, Delete) pour la gestion des notations dans une base de données.
 */
public abstract class NotationDAO {

    /**
     * Ajoute une nouvelle notation à la base de données.
     *
     * @param notation L'objet Notation à ajouter.
     * @return L'identifiant de la notation ajoutée.
     */
    public abstract int addNotation(Notation notation);

    /**
     * Modifie une notation existante dans la base de données.
     *
     * @param notation L'objet Notation modifié.
     * @return True si la modification a réussi, False sinon.
     */
    public abstract boolean modifyNotation(Notation notation);

    /**
     * Supprime une notation de la base de données.
     *
     * @param notationId L'identifiant de la notation à supprimer.
     * @return True si la suppression a réussi, False sinon.
     */
    public abstract boolean deleteNotation(int notationId);

    /**
     * Récupère la moyenne des notations pour un coach spécifié.
     *
     * @param coachId L'identifiant du coach.
     * @return La moyenne des notations pour le coach.
     */
    public abstract float getAverageNotationByCoachId(int coachId);

    /**
     * Récupère toutes les notations pour un coach spécifié.
     *
     * @param coachId L'identifiant du coach.
     * @return Un tableau d'objets Notation pour le coach.
     */
    public abstract Notation[] getNotationByCoachId(int coachId);

    /**
     * Récupère toutes les notations associées à un utilisateur spécifié.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return Un tableau d'objets Notation pour l'utilisateur.
     */
    public abstract Notation[] getNotationByUserId(int userId);

    /**
     * Récupère une notation spécifiée par son identifiant.
     *
     * @param notationId L'identifiant de la notation.
     * @return L'objet Notation correspondant à l'identifiant spécifié.
     */
    public abstract Notation getNotationById(int notationId);
}
