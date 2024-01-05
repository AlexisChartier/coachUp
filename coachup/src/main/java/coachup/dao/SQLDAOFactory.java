package coachup.dao;

import java.sql.SQLException;

/**
 * Fabrique concrète pour créer des objets DAO liés à une base de données SQL.
 */
public class SQLDAOFactory extends AbstractDAOFactory {

    private static SQLDAOFactory instance;
    private static UserDAOSQL userDAO;

    public static SQLDAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null){
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

    @Override
    public CoachDAO getCoachDAO() {
        return null;
    }
}