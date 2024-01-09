package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.model.Categorie;

import java.sql.SQLException;
import java.util.List;

/**
 * La classe CategorieFacade est une façade (facade) pour gérer les opérations liées aux catégories du côté de la logique métier.
 * Elle agit comme un Singleton et utilise la factory DAO pour accéder aux opérations de la base de données.
 */
public class CategorieFacade {

    private Categorie managedCategorie;

    /**
     * Obtient la catégorie gérée par la façade.
     *
     * @return La catégorie gérée.
     */
    public Categorie getManagedCategorie() {
        return managedCategorie;
    }

    /**
     * Définit la catégorie gérée par la façade.
     *
     * @param managedCategorie La catégorie à définir comme gérée.
     */
    public void setManagedCategorie(Categorie managedCategorie) {
        this.managedCategorie = managedCategorie;
    }

    private static CategorieFacade instance;
    private Categorie managedCategory;

    /**
     * Obtient la catégorie gérée par la façade (ancienne version).
     *
     * @return La catégorie gérée.
     * @deprecated Utilisez {@link #getManagedCategorie()} à la place.
     */
    @Deprecated
    public Categorie getManagedCategory() {
        return managedCategory;
    }

    /**
     * Définit la catégorie gérée par la façade (ancienne version).
     *
     * @param managedCategory La catégorie à définir comme gérée.
     * @deprecated Utilisez {@link #setManagedCategorie(Categorie)} à la place.
     */
    @Deprecated
    public void setManagedCategory(Categorie managedCategory) {
        this.managedCategory = managedCategory;
    }

    private static AbstractDAOFactory daoFactory;

    static {
        try {
            daoFactory = AbstractDAOFactory.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CategorieFacade() {
        // Constructeur privé pour assurer que seule une instance de CategoryFacade est créée (Singleton).
    }

    /**
     * Obtient l'instance unique de la façade (Singleton).
     *
     * @return L'instance de la façade.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static synchronized CategorieFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CategorieFacade();
        }
        return instance;
    }

    /**
     * Obtient une catégorie à partir de son identifiant.
     *
     * @param categoryId L'identifiant de la catégorie.
     * @return La catégorie correspondant à l'identifiant.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Categorie getCategoryById(int categoryId) throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().getCategorieById(categoryId);
    }

    /**
     * Obtient une catégorie à partir de son nom.
     *
     * @param nom Le nom de la catégorie.
     * @return La catégorie correspondant au nom.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Categorie getCategoryByNom(String nom) throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().getCategorieByNom(nom);
    }

    /**
     * Obtient la liste de toutes les catégories.
     *
     * @return La liste de toutes les catégories.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Categorie> getAllCategories() throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().getAllCategories();
    }

    /**
     * Ajoute une nouvelle catégorie à la base de données.
     *
     * @param category La catégorie à ajouter.
     * @return L'identifiant de la catégorie ajoutée.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public int addCategory(Categorie category) throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().addCategorie(category);
    }

    /**
     * Met à jour les informations d'une catégorie dans la base de données.
     *
     * @param category La catégorie avec les nouvelles informations.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void updateCategory(Categorie category) throws SQLException, ClassNotFoundException {
        daoFactory.getCategorieDAO().updateCategorie(category);
    }

    /**
     * Supprime une catégorie de la base de données.
     *
     * @param categoryId L'identifiant de la catégorie à supprimer.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void deleteCategory(int categoryId) throws SQLException, ClassNotFoundException {
        daoFactory.getCategorieDAO().deleteCategorie(categoryId);
    }
}
