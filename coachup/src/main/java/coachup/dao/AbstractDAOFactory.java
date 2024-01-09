package coachup.dao;

import java.sql.SQLException;

/**
 * Interface décrivant une fabrique abstraite pour créer des DAO (Data Access Object).
 */
public abstract class AbstractDAOFactory {

    private static AbstractDAOFactory instance;

    /**
     * Renvoie l'instance actuelle de la fabrique de DAO.
     *
     * @return L'instance de la fabrique de DAO.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static AbstractDAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = PGSQLDAOFactory.getInstance();
        }
        return instance;
    }

    /**
     * Renvoie une instance de UserDAO pour interagir avec la table des utilisateurs.
     *
     * @return Une instance de UserDAO.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public abstract UserDAO getUserDAO() throws SQLException, ClassNotFoundException;

    /**
     * Renvoie une instance de CoachDAO pour interagir avec la table des coachs.
     *
     * @return Une instance de CoachDAO.
     */
    public abstract CoachDAO getCoachDAO();

    /**
     * Renvoie une instance de NotationDAO pour interagir avec la table des notations.
     *
     * @return Une instance de NotationDAO.
     */
    public abstract NotationDAO getNotationDAO();

    /**
     * Renvoie une instance de CategorieDAO pour interagir avec la table des catégories.
     *
     * @return Une instance de CategorieDAO.
     */
    public abstract CategorieDAO getCategorieDAO();

    /**
     * Renvoie une instance de SeanceDAO pour interagir avec la table des séances.
     *
     * @return Une instance de SeanceDAO.
     */
    public abstract SeanceDAO getSeanceDAO();

    /**
     * Renvoie une instance de CreneauDispoDAO pour interagir avec la table des créneaux disponibles.
     *
     * @return Une instance de CreneauDispoDAO.
     */
    public abstract CreneauDispoDAO getCreneauDispoDAO();
}
