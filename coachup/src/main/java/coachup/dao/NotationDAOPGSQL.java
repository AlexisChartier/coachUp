package coachup.dao;

import coachup.model.Notation;


import java.sql.*;

public class NotationDAOPGSQL extends NotationDAO {

    private Connection connection;

    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup"; // Remplacez avec les informations de votre serveur PostgreSQL
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    public NotationDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }
    @Override
    public boolean addNotation(Notation notation) {
        try {
            // Préparation de la requête SQL
            String query = "INSERT INTO notation (note, comment, CoachId, UserId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setFloat(1, notation.getNote());
                statement.setString(2, notation.getComment());
                statement.setInt(3, notation.getCoachId());
                statement.setInt(4, notation.getUserId());

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
    public boolean deleteNotation(int NotationId) {
        try {
            // Préparation de la requête SQL
            String query = "DELETE FROM notation WHERE NotationId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, NotationId);

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
    public boolean modifyNotation(Notation notation) {
        try {
            // Préparation de la requête SQL
            String query = "UPDATE notation SET note = ?, comment = ?, CoachId = ?, UserId = ? WHERE NotationId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setFloat(1, notation.getNote());
                statement.setString(2, notation.getComment());
                statement.setInt(3, notation.getCoachId());
                statement.setInt(4, notation.getUserId());
                statement.setInt(5, notation.getNotationId());

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
