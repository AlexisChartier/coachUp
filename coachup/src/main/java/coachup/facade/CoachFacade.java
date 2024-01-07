package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.dao.CoachDAO;
import coachup.model.Categorie;
import coachup.model.Coach;

import java.sql.SQLException;
import java.util.List;

/**
 * Facade pour la gestion des coachs, fournissant des méthodes pour effectuer des opérations sur les coachs.
 */
public class CoachFacade {

    private static CoachFacade instance;
    private Coach currentCoach;

    public void setCurrentCoach(Coach currentCoach) {
        this.currentCoach = currentCoach;
    }

    private Coach managedCoach;

    public Coach getManagedCoach() {
        return managedCoach;
    }


    /**
     * Constructeur privé pour assurer que seule une instance de CoachFacade est créée (Singleton).
     */
    private CoachFacade() {
    }

    public Coach getCurrentCoach() {
        return this.currentCoach;
    }

    /**
     * Méthode pour obtenir l'instance unique de CoachFacade.
     *
     * @return L'instance unique de CoachFacade.
     */
    public static synchronized CoachFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CoachFacade();
        }
        return instance;
    }

    /**
     * Récupère un coach par son identifiant.
     *
     * @param coachId L'identifiant du coach.
     * @return L'objet Coach correspondant à l'identifiant, ou null s'il n'existe pas.
     */
    public Coach getCoachById(int coachId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().getCoachById(coachId);
    }

    public void denyCoach(int id) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        daoFactory.getCoachDAO().denyCoach(id);
    }

    public List<Categorie> getCategoriesByCoachID(int id) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().getCategoriesByCoachId(id);
    }

    /**
     * Récupère la liste de tous les coachs.
     *
     * @return La liste des coachs.
     */
    public List<Coach> getAllCoaches() throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().getAllCoaches();
    }

    /**
     * Ajoute un nouveau coach.
     *
     * @param coach L'objet Coach à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     */
    public boolean addCoach(Coach coach) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().addCoach(coach);
    }

    /**
     * Met à jour les informations d'un coach existant.
     *
     * @param coach L'objet Coach contenant les nouvelles informations.
     * @return true si la mise à jour est réussie, false sinon.
     */
    public boolean updateCoach(Coach coach) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().updateCoach(coach);
    }

    /**
     * Supprime un coach par son identifiant.
     *
     * @param coachId L'identifiant du coach à supprimer.
     * @return true si la suppression est réussie, false sinon.
     */
    public boolean deleteCoach(int coachId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().deleteCoach(coachId);
    }

    /**
     * Récupère la liste des coachs en attente d'approbation.
     *
     * @return La liste des coachs en attente d'approbation.
     */
    public List<Coach> getUnapprovedCoaches() throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        return daoFactory.getCoachDAO().getUnapprovedCoaches();
    }

    /**
     * Approuve un coach.
     *
     * @param coachId L'identifiant du coach à approuver.
     * @return true si l'approbation est réussie, false sinon.
     */
    public boolean approveCoach(int coachId) throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();
        //TODO METHODE POUR APPROUVER LES COACHS DANS COACH DAO
        return false;
        //return daoFactory.getCoachDAO().approveCoach(coachId);
    }


    public void setManagedCoach(Coach selectedCoach) {
        managedCoach = selectedCoach;
    }
}
