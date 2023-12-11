package coachup.dao;

import java.sql.SQLException;

/**
 * Interface décrivant une fabrique abstraite pour créer des DAO (Data Access Object).
 */
public abstract class AbstractDAOFactory{

    private static AbstractDAOFactory instance;

    public static AbstractDAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null){
           instance = SQLDAOFactory.getInstance();
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


}