package coachup.dao;

import coachup.model.User;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface UserDAO pour la gestion des opérations CRUD (Create, Read, Update, Delete) d'un utilisateur dans une base de données PostgreSQL.
 */
public class UserDAOPGSQL extends UserDAO {

    private Connection connection; // initialisation de la connexion

    /**
     * Constructeur de la classe. Établit une connexion à la base de données PostgreSQL.
     *
     * @throws SQLException            Si une erreur survient lors de la connexion à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote JDBC n'est pas trouvée.
     */
    public UserDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

    // Méthode pour établir la connexion à la base de données PostgreSQL
    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup"; // Remplacez avec les informations de votre serveur PostgreSQL
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Obtient un utilisateur à partir de son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return L'utilisateur correspondant à l'identifiant.
     */
    @Override
    public User getUserById(int userId) {
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM users WHERE iduser = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                // Exécution de la requête
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtient un utilisateur à partir de son adresse e-mail.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @return L'utilisateur correspondant à l'adresse e-mail.
     */
    @Override
    public User getUserByEmail(String email) {
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);

                // Exécution de la requête
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Création d'un objet User à partir des résultats de la requête
                        return mapResultSetToUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param user L'utilisateur à ajouter.
     * @return L'identifiant de l'utilisateur ajouté.
     */
    @Override
    public int addUser(User user) {
        try {
            String[] returnId = {"iduser"};
            // Préparation de la requête SQL
            String query = "INSERT INTO users (nom, email, motDePasse, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, returnId)) {
                statement.setString(1, user.getNom());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getMotDePasse());
                statement.setString(4, user.getRole());

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
                //return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Met à jour les informations d'un utilisateur dans la base de données.
     *
     * @param user L'utilisateur avec les nouvelles informations.
     * @return L'identifiant de l'utilisateur mis à jour.
     */
    @Override
    public int updateUser(User user) {
        try {
            String[] returnId = {"iduser"};
            // Préparation de la requête SQL
            String query = "UPDATE users SET nom = ?, email = ?, motDePasse = ?, role = ? WHERE iduser = ?";
            try (PreparedStatement statement = connection.prepareStatement(query, returnId)) {
                statement.setString(1, user.getNom());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getMotDePasse());
                statement.setString(4, user.getRole());
                statement.setInt(5, user.getIdUtilisateur());

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
                //return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Supprime un utilisateur de la base de données.
     *
     * @param userId L'identifiant de l'utilisateur à supprimer.
     * @return True si l'utilisateur est supprimé avec succès, sinon False.
     */
    @Override
    public boolean deleteUser(int userId) {
        try {
            // Préparation de la requête SQL
            String query = "DELETE FROM users WHERE iduser = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Vérifie les informations de connexion d'un utilisateur.
     *
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return True si les informations de connexion sont valides, sinon False.
     */
    @Override
    public boolean loginUser(String email, String password) {
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM users WHERE email = ? AND motDePasse = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, password);

                // Exécution de la requête
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Si le resultSet a au moins une ligne, les informations de connexion sont valides
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtient la liste de tous les utilisateurs dans la base de données.
     *
     * @return La liste des utilisateurs.
     */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM users";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Exécution de la requête
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Parcourir les résultats et créer des objets User
                    while (resultSet.next()) {
                        User user = mapResultSetToUser(resultSet);
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    /**
     * Méthode pour mapper les résultats d'une requête à un objet User.
     *
     * @param resultSet Le ResultSet contenant les résultats de la requête.
     * @return L'objet User correspondant aux résultats.
     * @throws SQLException Si une erreur survient lors du mapping.
     */
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUtilisateur(resultSet.getInt("iduser"));
        user.setNom(resultSet.getString("nom"));
        user.setEmail(resultSet.getString("email"));
        user.setMotDePasse(resultSet.getString("motDePasse"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}
