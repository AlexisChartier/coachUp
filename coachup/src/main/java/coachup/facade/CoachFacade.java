package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.dao.CoachDAO;
import coachup.model.Categorie;
import coachup.model.Coach;

import java.sql.SQLException;
import java.util.List;

/**
 * La classe CoachFacade est une façade (facade) pour gérer les opérations liées aux coachs du côté de la logique métier.
 * Elle agit comme un Singleton et utilise la factory DAO pour accéder aux opérations de la base de données.
 */
public class CoachFacade {

    private static CoachFacade instance;
    private Coach currentCoach;

    /**
     * Définit le coach actuellement connecté.
     *
     * @param currentCoach Le coach actuellement connecté.
     */
    public void setCurrentCoach(Coach currentCoach) {
        this.currentCoach = currentCoach;
    }

    /**
     * Obtient le coach actuellement géré par la façade.
     *
     * @return Le coach actuellement géré.
     */
    private Coach managedCoach;

    /**
     * Obtient le coach actuellement géré par la façade.
     *
     * @return Le coach actuellement géré.
     */
    public Coach getManagedCoach() {
        return managedCoach;
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
     * Constructeur privé pour assurer que seule une instance de CoachFacade est créée (Singleton).
     */
    private CoachFacade() {
    }

    /**
     * Obtient l'instance unique de la façade (Singleton).
     *
     * @return L'instance de la façade.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
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
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Coach getCoachById(int coachId) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().getCoachById(coachId);
    }

    /**
     * Récupère la liste des coachs par l'identifiant de la catégorie.
     *
     * @param id L'identifiant de la catégorie.
     * @return La liste des coachs correspondant à la catégorie.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Coach> getCoachesByCatId(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().getCoachesByCategoryId(id);
    }

    /**
     * Refuse la demande de coach en attente.
     *
     * @param id L'identifiant du coach à refuser.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void denyCoach(int id) throws SQLException, ClassNotFoundException {
        daoFactory.getCoachDAO().denyCoach(id);
    }

    /**
     * Récupère la liste des catégories associées à un coach.
     *
     * @param id L'identifiant du coach.
     * @return La liste des catégories associées au coach.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Categorie> getCategoriesByCoachID(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().getCategoriesByCoachId(id);
    }

    /**
     * Récupère la liste de tous les coachs.
     *
     * @return La liste des coachs.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Coach> getAllCoaches() throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().getAllCoaches();
    }

    /**
     * Ajoute un nouveau coach.
     *
     * @param coach L'objet Coach à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean addCoach(Coach coach) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().addCoach(coach);
    }

    /**
     * Met à jour les informations d'un coach existant.
     *
     * @param coach L'objet Coach contenant les nouvelles informations.
     * @return true si la mise à jour est réussie, false sinon.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean updateCoach(Coach coach) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().updateCoach(coach);
    }

    /**
     * Supprime un coach par son identifiant.
     *
     * @param coachId L'identifiant du coach à supprimer.
     * @return true si la suppression est réussie, false sinon.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean deleteCoach(int coachId) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().deleteCoach(coachId);
    }

    /**
     * Récupère la liste des coachs en attente d'approbation.
     *
     * @return La liste des coachs en attente d'approbation.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Coach> getUnapprovedCoaches() throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().getUnapprovedCoaches();
    }

    /**
     * Approuve un coach en attente.
     *
     * @param coachId L'identifiant du coach à approuver.
     * @return true si l'approbation est réussie, false sinon.
     * @throws SQLException            En cas d'erreur lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean approveCoach(int coachId) throws SQLException, ClassNotFoundException {
        return daoFactory.getCoachDAO().approveCoach(coachId);
    }

    /**
     * Définit le coach actuellement géré par la façade.
     *
     * @param selectedCoach Le coach à définir comme géré.
     */
    public void setManagedCoach(Coach selectedCoach) {
        managedCoach = selectedCoach;
    }

    public Coach getCurrentCoach() {
        return currentCoach;
    }
}
