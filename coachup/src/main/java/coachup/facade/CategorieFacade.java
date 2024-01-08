package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.model.Categorie;

import java.sql.SQLException;
import java.util.List;

public class CategorieFacade {

    private Categorie managedCategorie;

    public Categorie getManagedCategorie() {
        return managedCategorie;
    }

    public void setManagedCategorie(Categorie managedCategorie) {
        this.managedCategorie = managedCategorie;
    }

    private static CategorieFacade instance;
    private Categorie managedCategory;

    public void setManagedCategory(Categorie managedCategory) {
        this.managedCategory = managedCategory;
    }

    public Categorie getManagedCategory() {
        return managedCategory;
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

    public static synchronized CategorieFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CategorieFacade();
        }
        return instance;
    }

    public Categorie getCategoryById(int categoryId) throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().getCategorieById(categoryId);
    }

    public Categorie getCategoryByNom(String nom) throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().getCategorieByNom(nom);
    }

    public List<Categorie> getAllCategories() throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().getAllCategories();
    }

    public int addCategory(Categorie category) throws SQLException, ClassNotFoundException {
        return daoFactory.getCategorieDAO().addCategorie(category);
    }

    public void updateCategory(Categorie category) throws SQLException, ClassNotFoundException {
        daoFactory.getCategorieDAO().updateCategorie(category);
    }

    public void deleteCategory(int categoryId) throws SQLException, ClassNotFoundException {
        daoFactory.getCategorieDAO().deleteCategorie(categoryId);
    }
}
