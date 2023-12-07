package coachup.facade;
import coachup.dao.AbstractDAOFactory;
import coachup.dao.SQLDAOFactory;
import coachup.dao.UserDAO;
import coachup.model.User;

import java.sql.SQLException;

/**
 * Facade pour la gestion des utilisateurs, fournissant des méthodes pour effectuer des opérations sur les utilisateurs.
 */
public class UserFacade {

    private UserDAO userDAO;
    private static UserFacade instance;
    private User currentUser;

    /**
     * Constructeur privé pour assurer que seul une instance de UserFacade est créée (Singleton).
     */
    private UserFacade() throws SQLException, ClassNotFoundException {
        AbstractDAOFactory daoFactory = SQLDAOFactory.getInstance();
        try {
            userDAO = daoFactory.getUserDAO();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser(){
        return this.currentUser;
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
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    /**
     * Récupère un utilisateur par son adresse e-mail.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @return L'objet User correspondant à l'adresse e-mail, ou null s'il n'existe pas.
     */
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user L'objet User à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     */
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param user L'objet User contenant les nouvelles informations.
     * @return true si la mise à jour est réussie, false sinon.
     */
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur à supprimer.
     * @return true si la suppression est réussie, false sinon.
     */
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    /**
     * Vérifie les informations de connexion d'un utilisateur.
     *
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si les informations de connexion sont valides, false sinon.
     */
    public boolean loginUser(String email, String password) {
        if(userDAO.loginUser(email,password)){
            this.currentUser = getUserByEmail(email);
            return true;
        }
        else{
            return false;
        }
    }
}
