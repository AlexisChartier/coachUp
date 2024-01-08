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
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        NotationDAO notationDAO = daoFactory.getNotationDAO();
        return notationDAO.addNotation(notation);
    }

    public float getAvgNotationByCoachId(int id) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory abstractDAOFactory = AbstractDAOFactory.getInstance();
        return abstractDAOFactory.getNotationDAO().getAverageNotationByCoachId(id);
    }

    public boolean deleteNotation(int NotationId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        NotationDAO notationDAO = daoFactory.getNotationDAO();
        return notationDAO.deleteNotation(NotationId);
    }

    public boolean modifyNotation(Notation notation) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        NotationDAO notationDAO = daoFactory.getNotationDAO();
        return notationDAO.modifyNotation(notation);
    }

    public Notation getNotationById(int NotationId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        NotationDAO notationDAO = daoFactory.getNotationDAO();
        return notationDAO.getNotationById(NotationId);
    }

    public Notation[] getNotationByCoachId(int CoachId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        NotationDAO notationDAO = daoFactory.getNotationDAO();
        return notationDAO.getNotationByCoachId(CoachId);
    }

    public Notation[] getNotationByUserId(int UserId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        NotationDAO notationDAO = daoFactory.getNotationDAO();
        return notationDAO.getNotationByUserId(UserId);
    }
    
}