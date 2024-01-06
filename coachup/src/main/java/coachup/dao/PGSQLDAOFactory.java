package coachup.dao;

import java.sql.SQLException;

/**
 * Fabrique concrète pour créer des objets DAO liés à une base de données SQL.
 */
public class PGSQLDAOFactory extends AbstractDAOFactory {

    private static PGSQLDAOFactory instance;
    private static UserDAOPGSQL userDAO;

    private static NotationDAOPGSQL notationDAO;

    private static CoachDAOPGSQL coachDAO;

    public static PGSQLDAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null){
            instance = new PGSQLDAOFactory();
            userDAO = new UserDAOPGSQL();
            coachDAO = new CoachDAOPGSQL();
            notationDAO = new NotationDAOPGSQL();
        }
        return instance;
    }
    /**
     * Renvoie une instance de UserDAOImpl pour interagir avec la table des utilisateurs dans une base de données PostgresSQL.
     *
     * @return Une instance de UserDAOPGSQL.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    @Override
    public UserDAO getUserDAO() throws SQLException, ClassNotFoundException {
        return userDAO;
    }

    @Override
    public CoachDAO getCoachDAO() {
        return coachDAO;
    }

    @Override
    public NotationDAO getNotationDAO() {
        return notationDAO;
    }
}