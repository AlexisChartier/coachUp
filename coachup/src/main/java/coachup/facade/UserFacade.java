package coachup.facade;
import coachup.dao.AbstractDAOFactory;
import coachup.dao.SQLDAOFactory;
import coachup.dao.UserDAO;
import coachup.model.Categorie;
import coachup.model.Coach;
import coachup.model.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Facade pour la gestion des utilisateurs, fournissant des méthodes pour effectuer des opérations sur les utilisateurs.
 */
public class UserFacade {

    private static UserFacade instance;
    private User currentUser;

    private int coachId;
    private User managedUser;

    private int notationid;

    private Date searchedDate;

    private Categorie searchedCategory;

    public Coach getReserveCoach() {
        return reserveCoach;
    }

    public void setReserveCoach(Coach reserveCoach) {
        this.reserveCoach = reserveCoach;
    }

    private Coach reserveCoach;



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

    private List<Coach> coachSearch;

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

    /**
     * Constructeur privé pour assurer que seul une instance de UserFacade est créée (Singleton).
     */
    private UserFacade(){
    }

    public User getCurrentUser(){
        return this.currentUser;
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
     * Méthode pour obtenir l'instance unique de UserFacade.
     *
     * @return L'instance unique de UserFacade.
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
     */
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().getUserById(userId);
    }

    /**
     * Récupère un utilisateur par son adresse e-mail.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @return L'objet User correspondant à l'adresse e-mail, ou null s'il n'existe pas.
     */
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().getUserByEmail(email);
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user L'objet User à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     */
    public int addUser(User user) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().addUser(user);
    }
    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param user L'objet User contenant les nouvelles informations.
     * @return true si la mise à jour est réussie, false sinon.
     */
    public int updateUser(User user) throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().updateUser(user);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur à supprimer.
     * @return true si la suppression est réussie, false sinon.
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
     */
    public boolean loginUser(String email, String password) throws SQLException, ClassNotFoundException {
        if(daoFactory.getUserDAO().loginUser(email,password)){
            this.currentUser = getUserByEmail(email);
            return true;
        }
        else{
            return false;
        }
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return daoFactory.getUserDAO().getAllUsers();
    }


}
