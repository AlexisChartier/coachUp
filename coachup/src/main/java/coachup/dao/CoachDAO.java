package coachup.dao;

import coachup.model.Categorie;
import coachup.model.Coach;

import java.util.List;

/**
 * Interface décrivant les opérations CRUD (Create, Read, Update, Delete) pour les objets Coach dans une base de données.
 */
public abstract class CoachDAO {

    /**
     * Renvoie un coach en fonction de son identifiant.
     *
     * @param coachId L'identifiant du coach.
     * @return Un objet Coach correspondant à l'identifiant donné.
     */
    public abstract Coach getCoachById(int coachId);

    /**
     * Renvoie la liste de tous les coaches.
     *
     * @return Une liste de tous les objets Coach dans la base de données.
     */
    public abstract List<Coach> getAllCoaches();

    /**
     * Ajoute un coach à la base de données.
     *
     * @param coach L'objet Coach à ajouter.
     * @return True si l'ajout a réussi, False sinon.
     */
    public abstract boolean addCoach(Coach coach);

    /**
     * Met à jour les informations d'un coach dans la base de données.
     *
     * @param coach L'objet Coach à mettre à jour.
     * @return True si la mise à jour a réussi, False sinon.
     */
    public abstract boolean updateCoach(Coach coach);

    /**
     * Supprime un coach de la base de données en fonction de son identifiant.
     *
     * @param coachId L'identifiant du coach à supprimer.
     * @return True si la suppression a réussi, False sinon.
     */
    public abstract boolean deleteCoach(int coachId);

    /**
     * Renvoie la liste des coaches non approuvés.
     *
     * @return Une liste des objets Coach non approuvés dans la base de données.
     */
    public abstract List<Coach> getUnapprovedCoaches();

    /**
     * Refuse l'approbation d'un coach en fonction de son identifiant.
     *
     * @param id L'identifiant du coach à refuser.
     */
    public abstract void denyCoach(int id);

    /**
     * Renvoie la liste des catégories associées à un coach en fonction de son identifiant.
     *
     * @param id L'identifiant du coach.
     * @return Une liste des objets Categorie associés au coach.
     */
    public abstract List<Categorie> getCategoriesByCoachId(int id);

    /**
     * Approuve un coach en fonction de son identifiant.
     *
     * @param id L'identifiant du coach à approuver.
     * @return True si l'approbation a réussi, False sinon.
     */
    public abstract boolean approveCoach(int id);

    /**
     * Renvoie la liste des coaches associés à une catégorie en fonction de l'identifiant de la catégorie.
     *
     * @param idCategorie L'identifiant de la catégorie.
     * @return Une liste des objets Coach associés à la catégorie.
     */
    public abstract List<Coach> getCoachesByCategoryId(int idCategorie);
}
