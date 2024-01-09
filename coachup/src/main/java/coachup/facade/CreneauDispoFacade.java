package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.dao.CreneauDispoDAO;
import coachup.model.Creneau_dispo;

import java.sql.SQLException;
import java.util.List;

/**
 * La classe CreneauDispoFacade est une façade pour gérer les opérations liées aux créneaux disponibles du côté de la logique métier.
 * Elle agit comme un Singleton et utilise la factory DAO pour accéder aux opérations de la base de données.
 */
public class CreneauDispoFacade {

    private static CreneauDispoFacade instance;
    private Creneau_dispo currentCreneauDispo;

    private AbstractDAOFactory daoFactory;

    /**
     * Constructeur privé pour assurer que seule une instance de CreneauDispoFacade est créée (Singleton).
     */
    private CreneauDispoFacade() {
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
    public static synchronized CreneauDispoFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CreneauDispoFacade();
        }
        return instance;
    }

    /**
     * Ajoute un nouveau créneau disponible.
     *
     * @param creneau_dispo L'objet Creneau_dispo à ajouter.
     * @return L'identifiant du créneau ajouté.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public int addCreneauDispo(Creneau_dispo creneau_dispo) throws SQLException, ClassNotFoundException {
        return daoFactory.getCreneauDispoDAO().addCreneauDispo(creneau_dispo);
    }

    /**
     * Récupère les créneaux disponibles pour une journée spécifique.
     *
     * @param year  L'année.
     * @param month Le mois.
     * @param day   Le jour.
     * @return La liste des créneaux disponibles pour la journée spécifiée.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day) throws SQLException, ClassNotFoundException {
        return daoFactory.getCreneauDispoDAO().getCreneauByDay(year, month, day);
    }

    /**
     * Supprime un créneau disponible.
     *
     * @param creneauDispoId L'identifiant du créneau disponible à supprimer.
     * @return true si la suppression est réussie, false sinon.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean deleteCreneauDispo(int creneauDispoId) throws SQLException, ClassNotFoundException {
        return daoFactory.getCreneauDispoDAO().deleteCreneauDispo(creneauDispoId);
    }

    /**
     * Modifie un créneau disponible.
     *
     * @param creneau_dispo L'objet Creneau_dispo contenant les nouvelles informations.
     * @return true si la modification est réussie, false sinon.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean modifyCreneauDispo(Creneau_dispo creneau_dispo) throws SQLException, ClassNotFoundException {
        return daoFactory.getCreneauDispoDAO().modifyCreneauDispo(creneau_dispo);
    }

    /**
     * Récupère les créneaux disponibles pour une journée spécifique associée à un coach.
     *
     * @param year         L'année.
     * @param monthValue   Le mois.
     * @param dayOfMonth   Le jour.
     * @param idUtilisateur L'identifiant du coach.
     * @return La liste des créneaux disponibles pour la journée spécifiée associée au coach.
     */
    public List<Creneau_dispo> getCreneauByDayAndCoachId(int year, int monthValue, int dayOfMonth, int idUtilisateur) {
        return daoFactory.getCreneauDispoDAO().getCreneauByDayAndCoachId(year, monthValue, dayOfMonth, idUtilisateur);
    }

    /**
     * Réserve un créneau disponible pour un utilisateur et une catégorie donnés.
     *
     * @param selectedCreneau L'objet Creneau_dispo à réserver.
     * @param iduser          L'identifiant de l'utilisateur.
     * @param idcategorie     L'identifiant de la catégorie.
     * @throws SQLException Si une erreur survient lors de l'accès à la base de données.
     */
    public void reserverCreneau(Creneau_dispo selectedCreneau, int iduser, int idcategorie) throws SQLException {
        daoFactory.getCreneauDispoDAO().reserverCreneau(selectedCreneau, iduser, idcategorie);
        daoFactory.getCreneauDispoDAO().setReserved(selectedCreneau.getCreneauDispoId());
    }

    public void reserved(int id) throws SQLException {
        daoFactory.getCreneauDispoDAO().setReserved(id);
    }

    public Creneau_dispo getCreneauById(int id) throws SQLException {
        return daoFactory.getCreneauDispoDAO().getCreneauById(id);
    }
}
