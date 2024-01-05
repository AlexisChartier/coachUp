package coachup.dao;

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
            String query = "SELECT * FROM coach WHERE id = ?";
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
            String query = "INSERT INTO coach (nom, email, motDePasse, categories, disponibilites) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, coach.getNom());
                statement.setString(2, coach.getEmail());
                statement.setString(3, coach.getMotDePasse());
                statement.setString(4, coach.getCategories());
                statement.setArray(5, (Array) coach.getDisponibilites());
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
                statement.setString(1, coach.getNom());
                statement.setString(2, coach.getEmail());
                statement.setString(3, coach.getMotDePasse());
                statement.setString(4, coach.getCategories());
                statement.setArray(5, (Array) coach.getDisponibilites());
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
            String query = "SELECT * FROM coach WHERE approved = false";
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

    private Coach mapResultSetToCoach(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("idCoach");
        String nom = resultSet.getString("nom");
        String email = resultSet.getString("email");
        String motDePasse = resultSet.getString("motdepasse");
        String categories = resultSet.getString("categories");
        Array disponibilites = resultSet.getArray("disponibilites");
        return new Coach(id, nom, email, motDePasse, categories, disponibilites);
    }
}
