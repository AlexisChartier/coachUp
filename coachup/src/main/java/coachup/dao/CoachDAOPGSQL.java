package coachup.dao;

import coachup.model.Categorie;
import coachup.model.Coach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoachDAOPGSQL extends CoachDAO {

    private Connection connection;

    public CoachDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup";
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public Coach getCoachById(int coachId) {
        try {
            String query = "SELECT * FROM coach WHERE idcoach = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, coachId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToCoach(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Coach> getAllCoaches() {
        List<Coach> coaches = new ArrayList<>();
        try {
            String query = "SELECT * FROM coach";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        coaches.add(mapResultSetToCoach(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coaches;
    }

    @Override
    public boolean addCoach(Coach coach) {
        try {
            String query = "INSERT INTO coach (idcoach,nom, email, motDePasse, categories, disponibilites, approved, diplome) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                Integer[] cats = coach.getCategories();
                Array arrayCat = connection.createArrayOf("Integer",cats);
                Integer[] disp = coach.getDisponibilites();
                Array arrayDisp = connection.createArrayOf("Integer", disp);
                statement.setInt(1, coach.getIdUtilisateur());
                statement.setString(2, coach.getNom());
                statement.setString(3, coach.getEmail());
                statement.setString(4, coach.getMotDePasse());
                statement.setArray(5, arrayCat);
                statement.setArray(6, arrayDisp);
                statement.setBoolean(7, false);
                statement.setString(8,coach.getDiplome());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCoach(Coach coach) {
        try {
            String query = "UPDATE coach SET nom = ?, email = ?, motDePasse = ?, categories = ?, disponibilites = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                Integer[] cats = coach.getCategories();
                Array arrayCat = connection.createArrayOf("Integer",cats);
                Integer[] disp = coach.getDisponibilites();
                Array arrayDisp = connection.createArrayOf("Integer", disp);
                statement.setString(1, coach.getNom());
                statement.setString(2, coach.getEmail());
                statement.setString(3, coach.getMotDePasse());
                statement.setArray(4, arrayCat);
                statement.setArray(5, arrayDisp);
                statement.setInt(6, coach.getIdUtilisateur());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCoach(int coachId) {
        try {
            String query = "DELETE FROM coach WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, coachId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Coach> getUnapprovedCoaches() {
        List<Coach> unapprovedCoaches = new ArrayList<>();
        try {
            String query = "SELECT * FROM coach WHERE approved = false AND denied= false";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        unapprovedCoaches.add(mapResultSetToCoach(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unapprovedCoaches;
    }

    /**
     * Met à jour l'attribut 'denied' du coach avec l'ID spécifié.
     *
     * @param coachId L'ID du coach à rejeter.
     * @return true si la mise à jour est réussie, false sinon.
     *
     */
    @Override
    public void denyCoach(int coachId) {
        try {
            // Préparation de la requête SQL
            String query = "UPDATE coach SET denied = true WHERE idcoach = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, coachId);

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère les catégories associées à un coach par son identifiant.
     *
     * @param id L'identifiant du coach.
     * @return La liste des catégories associées au coach.
     */
    public List<Categorie> getCategoriesByCoachId(int id) {
        List<Categorie> categories = new ArrayList<>();

        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM categories WHERE idCategorie = ANY(SELECT UNNEST(categories) FROM coach WHERE idcoach = ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                // Exécution de la requête
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Categorie categorie = new Categorie();
                        categorie.setIdcategorie(resultSet.getInt("idcategorie"));
                        categorie.setNom(resultSet.getString("nom"));
                        categories.add(categorie);
                    }
                    return categories;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }


    @Override
    public boolean approveCoach(int id) {
        try {
            // Préparation de la requête SQL
            String query = "UPDATE coachs SET approved = true WHERE idcoach = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                // Exécution de la requête
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private Coach mapResultSetToCoach(ResultSet resultSet) throws SQLException {
        Array cats = resultSet.getArray("categories");
        Array disp = resultSet.getArray("disponibilites");
        int id = resultSet.getInt("idCoach");
        String nom = resultSet.getString("nom");
        String email = resultSet.getString("email");
        String motDePasse = resultSet.getString("motdepasse");
        Integer[] categories = (Integer[])cats.getArray();
        Integer[] disponibilites = (Integer[]) disp.getArray();
        String diplome = resultSet.getString("diplome");
        return new Coach(id, nom, email, motDePasse, categories, disponibilites, diplome);
    }
}
