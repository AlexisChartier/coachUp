package coachup.dao;

import coachup.model.Categorie;

import java.util.List;

/**
 * Interface décrivant les opérations CRUD (Create, Read, Update, Delete) pour l'entité Categorie.
 */
public abstract class CategorieDAO {

    /**
     * Récupère la liste de toutes les catégories.
     *
     * @return La liste de toutes les catégories.
     */
    public abstract List<Categorie> getAllCategories();

    /**
     * Récupère une catégorie par son identifiant.
     *
     * @param id L'identifiant de la catégorie.
     * @return La catégorie correspondante, ou null si non trouvée.
     */
    public abstract Categorie getCategorieById(int id);

    /**
     * Ajoute une nouvelle catégorie.
     *
     * @param category La catégorie à ajouter.
     * @return L'identifiant de la catégorie ajoutée.
     */
    public abstract int addCategorie(Categorie category);

    /**
     * Met à jour une catégorie existante.
     *
     * @param category La catégorie mise à jour.
     */
    public abstract void updateCategorie(Categorie category);

    /**
     * Supprime une catégorie par son identifiant.
     *
     * @param id L'identifiant de la catégorie à supprimer.
     */
    public abstract void deleteCategorie(int id);

    /**
     * Récupère une catégorie par son nom.
     *
     * @param nom Le nom de la catégorie.
     * @return La catégorie correspondante, ou null si non trouvée.
     */
    public abstract Categorie getCategorieByNom(String nom);
}
