package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.model.Categorie;
import coachup.model.Coach;
import coachup.model.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * La classe UserFacade est une façade pour gérer les opérations liées aux utilisateurs du côté de la logique métier.
 * Elle agit comme un Singleton et utilise la factory DAO pour accéder aux opérations de la base de données.
 */
public class UserFacade {

    private static UserFacade instance;
    private User currentUser;
    private int coachId;
    private User managedUser;
    private int notationid;
    private Date searchedDate;
    private Categorie searchedCategory;
    private Coach reserveCoach;
    private List<Coach> coachSearch;

    public int getNotationid() {
        return notationid;
    }

    public void setNotationid(int notationid) {
        this.notationid = notationid;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public Date getSearchedDate() {
        return searchedDate;
    }

    public void setSearchedDate(Date searchedDate) {
        this.searchedDate = searchedDate;
    }

    public Categorie getSearchedCategory() {
        return searchedCategory;
    }

    public void setSearchedCategory(Categorie searchedCategory) {
        this.searchedCategory = searchedCategory;
    }

    public Coach getReserveCoach() {
        return reserveCoach;
    }

    public void setReserveCoach(Coach reserveCoach) {
        this.reserveCoach = reserveCoach;
    }

    public List<Coach> getCoachSearch() {
        return coachSearch;
    }

    public void setCoachSearch(List<Coach> coachSearch) {
        this.coachSearch = coachSearch;
    }

    public void setManagedUser(User managedUser) {
        this.managedUser = managedUser;
    }

    public User getManagedUser() {
        return managedUser;
    }

    private AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();

    /**
     * Constructeur privé pour assurer que seule une instance de UserFacade est créée (Singleton).
     */
    private UserFacade() throws SQLException, ClassNotFoundException {
    }

    /**
     * Obtient l'instance unique de la façade (Singleton).
     *
     * @return L'instance de la façade.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public static synchronized UserFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return L'objet User correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().getUserById(userId);
    }

    /**
     * Récupère un utilisateur par son adresse e-mail.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @return L'objet User correspondant à l'adresse e-mail, ou null s'il n'existe pas.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().getUserByEmail(email);
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user L'objet User à ajouter.
     * @return L'identifiant de l'utilisateur ajouté.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public int addUser(User user) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().addUser(user);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param user L'objet User contenant les nouvelles informations.
     * @return L'identifiant de l'utilisateur mis à jour.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public int updateUser(User user) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().updateUser(user);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur à supprimer.
     * @return true si la suppression est réussie, false sinon.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean deleteUser(int userId) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().deleteUser(userId);
    }

    /**
     * Vérifie les informations de connexion d'un utilisateur.
     *
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si les informations de connexion sont valides, false sinon.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public boolean loginUser(String email, String password) throws SQLException, ClassNotFoundException {
        if (daoFactory.getUserDAO().loginUser(email, password)) {
            this.currentUser = getUserByEmail(email);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return La liste de tous les utilisateurs.
     * @throws SQLException            Si une erreur survient lors de l'accès à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().getAllUsers();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
