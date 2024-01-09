package coachup.dao;

import java.sql.SQLException;

/**
 * Fabrique concrète pour créer des objets DAO liés à une base de données SQL.
 */
public class SQLDAOFactory extends AbstractDAOFactory {

    private static SQLDAOFactory instance;
    private static UserDAOSQL userDAO;

    /**
     * Obtient une instance unique de SQLDAOFactory.
     *
     * @return L'instance de SQLDAOFactory.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static SQLDAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLDAOFactory();
            userDAO = new UserDAOSQL();
        }
        return instance;
    }

    /**
     * Renvoie une instance de UserDAOImpl pour interagir avec la table des utilisateurs dans une base de données SQL.
     *
     * @return Une instance de UserDAOImpl.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    @Override
    public UserDAO getUserDAO() throws SQLException, ClassNotFoundException {
        return userDAO;
    }

    /**
     * Obtient une instance de CoachDAO pour interagir avec la table des coachs dans une base de données SQL.
     *
     * @return Une instance de CoachDAOSQL.
     */
    @Override
    public CoachDAO getCoachDAO() {
        // Implémentez cette méthode pour renvoyer une instance de CoachDAO appropriée
        return null;
    }

    /**
     * Obtient une instance de NotationDAO pour interagir avec la table des notations dans une base de données SQL.
     *
     * @return Une instance de NotationDAOSQL.
     */
    @Override
    public NotationDAO getNotationDAO() {
        // Implémentez cette méthode pour renvoyer une instance de NotationDAO appropriée
        return null;
    }

    /**
     * Obtient une instance de CategorieDAO pour interagir avec la table des catégories dans une base de données SQL.
     *
     * @return Une instance de CategorieDAOSQL.
     */
    @Override
    public CategorieDAO getCategorieDAO() {
        // Implémentez cette méthode pour renvoyer une instance de CategorieDAO appropriée
        return null;
    }

    /**
     * Obtient une instance de SeanceDAO pour interagir avec la table des séances dans une base de données SQL.
     *
     * @return Une instance de SeanceDAOSQL.
     */
    @Override
    public SeanceDAO getSeanceDAO() {
        // Implémentez cette méthode pour renvoyer une instance de SeanceDAO appropriée
        return null;
    }

    /**
     * Obtient une instance de CreneauDispoDAO pour interagir avec la table des créneaux disponibles dans une base de données SQL.
     *
     * @return Une instance de CreneauDispoDAOSQL.
     */
    @Override
    public CreneauDispoDAO getCreneauDispoDAO() {
        // Implémentez cette méthode pour renvoyer une instance de CreneauDispoDAO appropriée
        return null;
    }
}
