package coachup.dao;
import coachup.model.User;

import java.sql.*;
import java.util.List;

/**
 * Implémentation de l'interface UserDAO pour la gestion des opérations CRUD (Create, Read, Update, Delete) d'un utilisateur dans une base de données MySQL.
 */
public class UserDAOSQL extends UserDAO {

    private Connection connection; // initialisation de la connexion

    /**
     * Constructeur de la classe. Établit une connexion à la base de données MySQL.
     *
     * @throws SQLException            Si une erreur survient lors de la connexion à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote JDBC n'est pas trouvée.
     */
    public UserDAOSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

    // Méthode pour établir la connexion à la base de données MySQL
    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/coachup";
        String username = "root";
        String password = "Alexis12*";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public User getUserById(int userId) {
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM users WHERE id = ?";
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

    @Override
    public int addUser(User user) {
        try {
            // Préparation de la requête SQL
            String query = "INSERT INTO users (nom, email, motDePasse, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getNom());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getMotDePasse());
                statement.setString(4, user.getRole());

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();
                return statement.getGeneratedKeys().getInt(1);
                //return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            // Préparation de la requête SQL
            String query = "UPDATE users SET nom = ?, email = ?, motDePasse = ?, role = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getNom());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getMotDePasse());
                statement.setString(4, user.getRole());
                statement.setInt(5, user.getIdUtilisateur());

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        try {
            // Préparation de la requête SQL
            String query = "DELETE FROM users WHERE id = ?";
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

    // Méthode pour mapper les résultats d'une requête à un objet User
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUtilisateur(resultSet.getInt("id"));
        user.setNom(resultSet.getString("nom"));
        user.setEmail(resultSet.getString("email"));
        user.setMotDePasse(resultSet.getString("motDePasse"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}
