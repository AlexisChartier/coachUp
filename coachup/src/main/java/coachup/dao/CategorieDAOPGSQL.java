package coachup.dao;

import coachup.model.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface CategorieDAO pour la gestion des opérations CRUD (Create, Read, Update, Delete) d'une catégorie dans une base de données PostgreSQL.
 */
public class CategorieDAOPGSQL extends CategorieDAO {
    private Connection connection; // initialisation de la connexion

    /**
     * Constructeur de la classe. Établit une connexion à la base de données PostgreSQL.
     *
     * @throws SQLException           Si une erreur survient lors de la connexion à la base de données.
     * @throws ClassNotFoundException Si la classe du pilote JDBC n'est pas trouvée.
     */
    public CategorieDAOPGSQL() throws SQLException, ClassNotFoundException {
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

    @Override
    public List<Categorie> getAllCategories() {
        List<Categorie> categories = new ArrayList<>();

        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM categories";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Parcourir les résultats et créer des objets Categorie
                while (resultSet.next()) {
                    Categorie categorie = mapResultSetToCategorie(resultSet);
                    categories.add(categorie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Categorie getCategorieById(int id) {
        Categorie categorie = null;

        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM categories WHERE idCategorie = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                // Exécution de la requête
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        categorie = mapResultSetToCategorie(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorie;
    }

    @Override
    public void addCategorie(Categorie category) {
        try {
            // Préparation de la requête SQL
            String query = "INSERT INTO categories (nom, description) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, category.getNom());
                statement.setString(2, category.getDescription());

                // Exécution de la requête
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategorie(Categorie category) {
        try {
            // Préparation de la requête SQL
            String query = "UPDATE categories SET nom = ?, description = ? WHERE idCategorie = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, category.getNom());
                statement.setString(2, category.getDescription());
                statement.setInt(3, category.getIdcategorie());

                // Exécution de la requête
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategorie(int id) {
        try {
            // Préparation de la requête SQL
            String query = "DELETE FROM categories WHERE idCategorie = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                // Exécution de la requête
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour mapper les résultats d'une requête à un objet Categorie
     */
    private Categorie mapResultSetToCategorie(ResultSet resultSet) throws SQLException {
        Categorie categorie = new Categorie();
        categorie.setIdcategorie(resultSet.getInt("idCategorie"));
        categorie.setNom(resultSet.getString("nom"));
        categorie.setDescription(resultSet.getString("description"));
        return categorie;
    }
}
