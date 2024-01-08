package coachup.facade;

import coachup.model.Creneau_dispo;
import coachup.dao.CreneauDispoDAO;
import coachup.dao.AbstractDAOFactory;


import java.sql.SQLException;
import java.util.List;

/**
 * Facade pour la gestion des notations, fournissant des méthodes pour effectuer des opérations sur les notations.
 */
public class CreneauDispoFacade {

    private static CreneauDispoFacade instance;

    private Creneau_dispo currentCreneauDispo;

    /**
     * Constructeur privé pour assurer que seule une instance de NotationFacade est créée (Singleton).
     */
    private CreneauDispoFacade() {
    }


    /**
     * Méthode pour obtenir l'instance unique de CoachFacade.
     *
     * @return L'instance unique de CoachFacade.
     */
    public static synchronized CreneauDispoFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CreneauDispoFacade();
        }
        return instance;
    }

    public boolean addCreneauDispo(Creneau_dispo creneau_dispo) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        CreneauDispoDAO creneauDispoDAO = daoFactory.getCreneauDispoDAO();
        return creneauDispoDAO.addCreneauDispo(creneau_dispo);
    }

    public List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        CreneauDispoDAO creneauDispoDAO = daoFactory.getCreneauDispoDAO();
        return creneauDispoDAO.getCreneauByDay(year, month, day);
    }

    public boolean deleteCreneauDispo(int creneauDispoId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        CreneauDispoDAO creneauDispoDAO = daoFactory.getCreneauDispoDAO();
        return creneauDispoDAO.deleteCreneauDispo(creneauDispoId);
    }

    public boolean modifyCreneauDispo(Creneau_dispo creneau_dispo) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        CreneauDispoDAO creneauDispoDAO = daoFactory.getCreneauDispoDAO();
        return creneauDispoDAO.modifyCreneauDispo(creneau_dispo);
    }

}