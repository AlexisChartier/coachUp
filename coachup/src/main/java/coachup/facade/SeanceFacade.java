package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.model.Seance;

import java.sql.SQLException;
import java.util.List;

/**
 * La classe SeanceFacade est une façade pour gérer les opérations liées aux séances du côté de la logique métier.
 * Elle agit comme un Singleton et utilise la factory DAO pour accéder aux opérations de la base de données.
 */
public class SeanceFacade {

    private static SeanceFacade instance;
    private Seance managedSeance;
    private static AbstractDAOFactory daoFactory;

    static {
        try {
            daoFactory = AbstractDAOFactory.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructeur privé pour assurer que seule une instance de SeanceFacade est créée (Singleton).
     */
    private SeanceFacade() {
    }

    /**
     * Obtient l'instance unique de la façade (Singleton).
     *
     * @return L'instance de la façade.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static synchronized SeanceFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SeanceFacade();
        }
        return instance;
    }

    /**
     * Définit la séance gérée par la façade.
     *
     * @param managedSeance La séance à gérer.
     */
    public void setManagedSeance(Seance managedSeance) {
        this.managedSeance = managedSeance;
    }

    /**
     * Récupère la séance gérée par la façade.
     *
     * @return La séance gérée par la façade.
     */
    public Seance getManagedSeance() {
        return managedSeance;
    }

    /**
     * Récupère toutes les séances passées associées à un utilisateur.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste des séances passées associées à l'utilisateur spécifié.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Seance> getSeancesPassedByUserId(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getSeancesPassedByUserId(id);
    }

    /**
     * Récupère toutes les séances.
     *
     * @return Une liste de toutes les séances.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Seance> getAllSeances() throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getAllSeances();
    }

    /**
     * Récupère une séance par son identifiant.
     *
     * @param id L'identifiant de la séance.
     * @return L'objet Seance correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Seance getSeanceById(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getSeanceById(id);
    }

    /**
     * Ajoute une nouvelle séance.
     *
     * @param seance L'objet Seance à ajouter.
     * @return L'identifiant de la séance ajoutée.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public int addSeance(Seance seance) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().addSeance(seance);
    }

    /**
     * Met à jour les informations d'une séance existante.
     *
     * @param seance L'objet Seance contenant les nouvelles informations.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void updateSeance(Seance seance) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().updateSeance(seance);
    }

    /**
     * Supprime une séance par son identifiant.
     *
     * @param id L'identifiant de la séance à supprimer.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void deleteSeance(int id) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().deleteSeance(id);
    }

    /**
     * Récupère toutes les séances en attente de paiement associées à un utilisateur.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste des séances en attente de paiement associées à l'utilisateur spécifié.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Seance> getPendingSeances(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getPendingPaymentsByUserId(id);
    }

    /**
     * Récupère toutes les séances à venir associées à un utilisateur.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste des séances à venir associées à l'utilisateur spécifié.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Seance> getUpcomingSeances(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getUpcomingSessionsByUserId(id);
    }

    /**
     * Payer une séance.
     *
     * @param seance La séance à payer.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void paySeance(Seance seance) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().paySeance(seance.getIdSeance());
    }

    /**
     * Rembourser une séance.
     *
     * @param seance La séance à rembourser.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public void refundSeance(Seance seance) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().refundSeance(seance.getIdSeance());
    }
}
