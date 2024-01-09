package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.dao.NotationDAO;
import coachup.model.Notation;

import java.sql.SQLException;

/**
 * La classe NotationFacade est une façade pour gérer les opérations liées aux notations du côté de la logique métier.
 * Elle agit comme un Singleton et utilise la factory DAO pour accéder aux opérations de la base de données.
 */
public class NotationFacade {

    private static NotationFacade instance;
    private Notation currentNotation;

    private AbstractDAOFactory daoFactory;

    /**
     * Constructeur privé pour assurer que seule une instance de NotationFacade est créée (Singleton).
     */
    private NotationFacade() {
        try {
            daoFactory = AbstractDAOFactory.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtient l'instance unique de la façade (Singleton).
     *
     * @return L'instance de la façade.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static synchronized NotationFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new NotationFacade();
        }
        return instance;
    }

    /**
     * Ajoute une nouvelle notation.
     *
     * @param notation L'objet Notation à ajouter.
     * @return L'identifiant de la notation ajoutée.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public int addNotation(Notation notation) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().addNotation(notation);
    }

    /**
     * Récupère la moyenne des notations pour un coach donné.
     *
     * @param id L'identifiant du coach.
     * @return La moyenne des notations pour le coach spécifié.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public float getAvgNotationByCoachId(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getAverageNotationByCoachId(id);
    }

    /**
     * Supprime une notation.
     *
     * @param notationId L'identifiant de la notation à supprimer.
     * @return true si la suppression est réussie, false sinon.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean deleteNotation(int notationId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().deleteNotation(notationId);
    }

    /**
     * Modifie une notation existante.
     *
     * @param notation L'objet Notation contenant les nouvelles informations.
     * @return true si la modification est réussie, false sinon.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean modifyNotation(Notation notation) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().modifyNotation(notation);
    }

    /**
     * Récupère une notation par son identifiant.
     *
     * @param notationId L'identifiant de la notation.
     * @return L'objet Notation correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Notation getNotationById(int notationId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getNotationById(notationId);
    }

    /**
     * Récupère toutes les notations associées à un coach.
     *
     * @param coachId L'identifiant du coach.
     * @return Un tableau des notations associées au coach spécifié.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Notation[] getNotationByCoachId(int coachId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getNotationByCoachId(coachId);
    }

    /**
     * Récupère toutes les notations associées à un utilisateur.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return Un tableau des notations associées à l'utilisateur spécifié.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public Notation[] getNotationByUserId(int userId) throws SQLException, ClassNotFoundException {
        return daoFactory.getNotationDAO().getNotationByUserId(userId);
    }
}
