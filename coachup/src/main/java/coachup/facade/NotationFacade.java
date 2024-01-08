package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.dao.NotationDAO;
import coachup.model.Notation;

import java.sql.SQLException;

/**
 * Facade pour la gestion des notations, fournissant des méthodes pour effectuer des opérations sur les notations.
 */
public class NotationFacade {

    private static NotationFacade instance;

    private Notation currentNotation;

    /**
     * Constructeur privé pour assurer que seule une instance de NotationFacade est créée (Singleton).
     */
    private NotationFacade() {
    }

    private static AbstractDAOFactory daoFactory;

    static {
        try {
            daoFactory = AbstractDAOFactory.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Méthode pour obtenir l'instance unique de CoachFacade.
     *
     * @return L'instance unique de CoachFacade.
     */
    public static synchronized NotationFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new NotationFacade();
        }
        return instance;
    }

    public boolean addNotation(Notation notation) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().addNotation(notation);
    }

    public float getAvgNotationByCoachId(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getAverageNotationByCoachId(id);
    }

    public boolean deleteNotation(int NotationId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().deleteNotation(NotationId);
    }

    public boolean modifyNotation(Notation notation) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().modifyNotation(notation);
    }

    public Notation getNotationById(int NotationId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getNotationById(NotationId);
    }

    public Notation[] getNotationByCoachId(int CoachId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getNotationByCoachId(CoachId);
    }

    public Notation[] getNotationByUserId(int UserId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getNotationByUserId(UserId);
    }
    
}