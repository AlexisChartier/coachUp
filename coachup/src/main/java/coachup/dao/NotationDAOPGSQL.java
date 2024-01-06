package coachup.dao;

import coachup.model.Notation;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String query = "INSERT INTO notation(note, comment, coachid, userid) VALUES (?, ?, ?, ?)";
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
            String query = "DELETE FROM notation WHERE notationid = ?";
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
            String query = "UPDATE notation SET note = ?, comment = ?, coachid = ?, userid = ? WHERE notationid = ?";
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

    @Override
    public Notation[] getNotationByCoachId(int CoachId){
        List<Notation> notations = new ArrayList<>();
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM notation WHERE coachid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, CoachId);

                // Exécution de la requête
                ResultSet resultSet = statement.executeQuery();

                // Parcourir le ResultSet
                while (resultSet.next()) {
                    Notation notation = new Notation();
                    notation.setNotationId(resultSet.getInt("NotationId"));
                    notation.setNote(resultSet.getFloat("note"));
                    notation.setComment(resultSet.getString("comment"));
                    notation.setCoachId(resultSet.getInt("coachid"));
                    notation.setUserId(resultSet.getInt("userid"));
                    notations.add(notation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notations.toArray(new Notation[0]);
    }

    @Override
    public Notation[] getNotationByUserId(int UserId){
        List<Notation> notations = new ArrayList<>();
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM notation WHERE userid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, UserId);

                // Exécution de la requête
                ResultSet resultSet = statement.executeQuery();

                // Parcourir le ResultSet
                while (resultSet.next()) {
                    Notation notation = new Notation();
                    notation.setNotationId(resultSet.getInt("NotationId"));
                    notation.setNote(resultSet.getFloat("note"));
                    notation.setComment(resultSet.getString("comment"));
                    notation.setCoachId(resultSet.getInt("coachid"));
                    notation.setUserId(resultSet.getInt("userid"));
                    notations.add(notation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notations.toArray(new Notation[0]);
    }

    @Override
    public Notation getNotationById(int NotationId){
        Notation notation = new Notation();
        try {
            // Préparation de la requête SQL
            String query = "SELECT * FROM notation WHERE notationid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, NotationId);

                // Exécution de la requête
                ResultSet resultSet = statement.executeQuery();

                // Parcourir le ResultSet
                while (resultSet.next()) {
                    notation.setNotationId(resultSet.getInt("NotationId"));
                    notation.setNote(resultSet.getFloat("note"));
                    notation.setComment(resultSet.getString("comment"));
                    notation.setCoachId(resultSet.getInt("coachid"));
                    notation.setUserId(resultSet.getInt("userid"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notation;
    }

}
