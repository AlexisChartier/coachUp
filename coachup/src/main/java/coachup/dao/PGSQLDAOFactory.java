package coachup.dao;

import java.sql.SQLException;

/**
 * Fabrique concrète pour créer des objets DAO liés à une base de données SQL.
 */
public class PGSQLDAOFactory extends AbstractDAOFactory {

    private static PGSQLDAOFactory instance;
    private static UserDAOPGSQL userDAO;
    private static NotationDAOPGSQL notationDAO;
    private static CreneauDispoDAOPGSQL creneauDispoDAO;
    private static CoachDAOPGSQL coachDAO;
    private static SeanceDAOPGSQL seanceDAO;
    private static CategorieDAOPGSQL categoriesDAOPGSQL;

    /**
     * Renvoie une instance de PGSQLDAOFactory. Si l'instance n'existe pas, elle est créée et les DAO associés sont initialisés.
     *
     * @return Une instance de PGSQLDAOFactory.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static PGSQLDAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new PGSQLDAOFactory();
            userDAO = new UserDAOPGSQL();
            coachDAO = new CoachDAOPGSQL();
            notationDAO = new NotationDAOPGSQL();
            categoriesDAOPGSQL = new CategorieDAOPGSQL();
            seanceDAO = new SeanceDAOPGSQL();
            creneauDispoDAO = new CreneauDispoDAOPGSQL();
        }
        return instance;
    }

    /**
     * Renvoie une instance de UserDAOImpl pour interagir avec la table des utilisateurs dans une base de données PostgreSQL.
     *
     * @return Une instance de UserDAOPGSQL.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    @Override
    public UserDAO getUserDAO() throws SQLException, ClassNotFoundException {
        return userDAO;
    }

    /**
     * Renvoie une instance de CoachDAO pour interagir avec la table des coachs dans une base de données PostgreSQL.
     *
     * @return Une instance de CoachDAOPGSQL.
     */
    @Override
    public CoachDAO getCoachDAO() {
        return coachDAO;
    }

    /**
     * Renvoie une instance de NotationDAO pour interagir avec la table des notations dans une base de données PostgreSQL.
     *
     * @return Une instance de NotationDAOPGSQL.
     */
    @Override
    public NotationDAO getNotationDAO() {
        return notationDAO;
    }

    /**
     * Renvoie une instance de CategorieDAO pour interagir avec la table des catégories dans une base de données PostgreSQL.
     *
     * @return Une instance de CategorieDAOPGSQL.
     */
    @Override
    public CategorieDAO getCategorieDAO() {
        return categoriesDAOPGSQL;
    }

    /**
     * Renvoie une instance de SeanceDAO pour interagir avec la table des séances dans une base de données PostgreSQL.
     *
     * @return Une instance de SeanceDAOPGSQL.
     */
    @Override
    public SeanceDAO getSeanceDAO() {
        return seanceDAO;
    }

    /**
     * Renvoie une instance de CreneauDispoDAO pour interagir avec la table des créneaux disponibles dans une base de données PostgreSQL.
     *
     * @return Une instance de CreneauDispoDAOPGSQL.
     */
    public CreneauDispoDAO getCreneauDispoDAO() {
        return creneauDispoDAO;
    }
}
