package coachup.dao;
import coachup.model.User;

import java.util.List;

/**
 * Interface définissant les opérations CRUD (Create, Read, Update, Delete) pour un utilisateur dans la base de données.
 */
public abstract class UserDAO {


    /**
     * Récupère un utilisateur par son ID depuis la base de données.
     *
     * @param userId L'ID de l'utilisateur.
     * @return L'objet User correspondant à l'ID, ou null s'il n'est pas trouvé.
     */
    public abstract User getUserById(int userId);

    /**
     * Récupère un utilisateur par son email depuis la base de données.
     *
     * @param email L'email de l'utilisateur.
     * @return L'objet User correspondant à l'email, ou null s'il n'est pas trouvé.
     */
    public abstract User getUserByEmail(String email);

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param user L'objet User à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     */
    public abstract boolean addUser(User user);

    /**
     * Met à jour les informations d'un utilisateur dans la base de données.
     *
     * @param user L'objet User à mettre à jour.
     * @return true si la mise à jour est réussie, false sinon.
     */
    public abstract boolean updateUser(User user);

    public abstract List<User> getAllUsers();

    /**
     * Supprime un utilisateur de la base de données.
     *
     * @param userId L'ID de l'utilisateur à supprimer.
     * @return true si la suppression est réussie, false sinon.
     */
    public abstract boolean deleteUser(int userId);

    /**
     * Vérifie les informations de connexion dans la base de données.
     *
     * @param email    L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si les informations de connexion sont valides, false sinon.
     */
    public abstract boolean loginUser(String email, String password);
}
