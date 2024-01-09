package coachup.dao;

import coachup.model.Notation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe implémentant les opérations CRUD (Create, Read, Update, Delete) pour la gestion des notations dans une base de données PostgreSQL.
 */
public class NotationDAOPGSQL extends NotationDAO {

    private Connection connection;

    /**
     * Établit la connexion à la base de données PostgreSQL.
     *
     * @throws SQLException            En cas d'erreur lors de la connexion à la base de données.
     * @throws ClassNotFoundException Si la classe du pilote PostgreSQL n'est pas trouvée.
     */
    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup";
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Constructeur de la classe, établissant la connexion à la base de données lors de l'instanciation.
     *
     * @throws SQLException            En cas d'erreur lors de la connexion à la base de données.
     * @throws ClassNotFoundException Si la classe du pilote PostgreSQL n'est pas trouvée.
     */
    public NotationDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

    @Override
    public int addNotation(Notation notation) {

        try {
            String[] returnid = {"notationid"};
            String query = "INSERT INTO notation(note, comment, coachid, userid) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, returnid)) {
                statement.setFloat(1, notation.getNote());
                statement.setString(2, notation.getComment());
                statement.setInt(3, notation.getCoachId());
                statement.setInt(4, notation.getUserId());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            return resultSet.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean deleteNotation(int notationId) {
        try {
            String query = "DELETE FROM notation WHERE notationid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, notationId);

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
            String query = "UPDATE notation SET note = ?, comment = ?, coachid = ?, userid = ? WHERE notationid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setFloat(1, notation.getNote());
                statement.setString(2, notation.getComment());
                statement.setInt(3, notation.getCoachId());
                statement.setInt(4, notation.getUserId());
                statement.setInt(5, notation.getNotationId());

                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public float getAverageNotationByCoachId(int coachId) {
        float average = 0;
        try {
            String query = "SELECT AVG(note) FROM notation WHERE coachid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, coachId);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    average = resultSet.getFloat(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }

    @Override
    public Notation[] getNotationByCoachId(int coachId) {
        List<Notation> notations = new ArrayList<>();
        try {
            String query = "SELECT * FROM notation WHERE coachid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, coachId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Notation notation = new Notation();
                    notation.setNotationId(resultSet.getInt("notationid"));
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
    public Notation[] getNotationByUserId(int userId) {
        List<Notation> notations = new ArrayList<>();
        try {
            String query = "SELECT * FROM notation WHERE userid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Notation notation = new Notation();
                    notation.setNotationId(resultSet.getInt("notationid"));
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
    public Notation getNotationById(int notationId) {
        Notation notation = new Notation();
        try {
            String query = "SELECT * FROM notation WHERE notationid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, notationId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    notation.setNotationId(resultSet.getInt("notationid"));
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

    /**
     * Ferme la connexion à la base de données.
     *
     * @throws SQLException En cas d'erreur lors de la fermeture de la connexion.
     */
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
