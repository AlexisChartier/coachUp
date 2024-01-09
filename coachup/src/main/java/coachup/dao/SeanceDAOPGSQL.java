package coachup.dao;

import coachup.model.Seance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation concrète de SeanceDAO pour une base de données PostgreSQL.
 */
public class SeanceDAOPGSQL extends SeanceDAO {

    private Connection connection;

    /**
     * Constructeur de la classe SeanceDAOPGSQL.
     *
     * @throws SQLException            En cas d'erreur lors de la connexion à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    public SeanceDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

    /**
     * Méthode privée pour établir la connexion à la base de données.
     *
     * @throws SQLException            En cas d'erreur lors de la connexion à la base de données.
     * @throws ClassNotFoundException  Si la classe du pilote de base de données n'est pas trouvée.
     */
    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup"; // Remplacez avec les informations de votre serveur PostgreSQL
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Récupère toutes les séances disponibles dans la base de données.
     *
     * @return Une liste de toutes les séances.
     */
    @Override
    public List<Seance> getAllSeances() {
        List<Seance> seances = new ArrayList<>();

        try {
            String query = "SELECT * FROM seance";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Seance seance = mapResultSetToSeance(resultSet);
                    seances.add(seance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }

    /**
     * Récupère toutes les séances passées pour un utilisateur donné.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Une liste de séances passées pour l'utilisateur.
     */
    public List<Seance> getSeancesPassedByUserId(int id) {
        List<Seance> seances = new ArrayList<>();

        try {
            String query = "SELECT * FROM seance WHERE idUser = ? AND date < ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.setDate(2, Date.valueOf(LocalDate.now()));

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Seance seance = mapResultSetToSeance(resultSet);
                        seances.add(seance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }

    /**
     * Récupère une séance par son identifiant.
     *
     * @param id L'identifiant de la séance à récupérer.
     * @return La séance correspondante, ou null si non trouvée.
     */
    @Override
    public Seance getSeanceById(int id) {
        Seance seance = null;

        try {
            String query = "SELECT * FROM seance WHERE idSeance = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        seance = mapResultSetToSeance(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seance;
    }

    /**
     * Ajoute une nouvelle séance à la base de données.
     *
     * @param seance La séance à ajouter.
     * @return L'identifiant de la séance ajoutée, ou -1 en cas d'erreur.
     */
    @Override
    public int addSeance(Seance seance) {
        try {
            String[] returnid = {"idseance"};
            String query = "INSERT INTO seance (date, idCoach, idUser, idCategorie, statutPaiement) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, returnid)) {
                statement.setDate(1, new Date(seance.getDate().getTime()));
                statement.setInt(2, seance.getIdCoach());
                statement.setInt(3, seance.getIdUser());
                statement.setInt(4, seance.getIdCategorie());
                statement.setString(5, seance.getStatutPaiement());

                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Met à jour les informations d'une séance dans la base de données.
     *
     * @param seance La séance à mettre à jour.
     */
    @Override
    public void updateSeance(Seance seance) {
        try {
            String query = "UPDATE seance SET date = ?, idCoach = ?, idUser = ?, idCategorie = ?, statutPaiement = ? WHERE idSeance = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, new Date(seance.getDate().getTime()));
                statement.setInt(2, seance.getIdCoach());
                statement.setInt(3, seance.getIdUser());
                statement.setInt(4, seance.getIdCategorie());
                statement.setString(5, seance.getStatutPaiement());
                statement.setInt(6, seance.getIdSeance());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime une séance de la base de données.
     *
     * @param id L'identifiant de la séance à supprimer.
     */
    @Override
    public void deleteSeance(int id) {
        try {
            String query = "DELETE FROM seance WHERE idSeance = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mappe les résultats d'une requête SQL à un objet Seance.
     *
     * @param resultSet Le ResultSet de la requête SQL.
     * @return Un objet Seance.
     * @throws SQLException En cas d'erreur lors de la récupération des données depuis le ResultSet.
     */
    private Seance mapResultSetToSeance(ResultSet resultSet) throws SQLException {
        Seance seance = new Seance();
        seance.setIdSeance(resultSet.getInt("idSeance"));
        seance.setDate(resultSet.getDate("date"));
        seance.setIdCoach(resultSet.getInt("idCoach"));
        seance.setIdUser(resultSet.getInt("idUser"));
        seance.setIdCategorie(resultSet.getInt("idCategorie"));
        seance.setStatutPaiement(resultSet.getString("statutPaiement"));
        return seance;
    }

    /**
     * Récupère toutes les séances à venir pour un utilisateur donné avec un statut de paiement en attente.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return Une liste de séances à venir avec paiement en attente pour l'utilisateur.
     */
    public List<Seance> getUpcomingSessionsByUserId(int userId) {
        List<Seance> upcomingSessions = new ArrayList<>();

        try {
            String query = "SELECT * FROM seance WHERE idUser = ? AND date > CURRENT_TIMESTAMP AND statutPaiement = 'En Attente'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Seance seance = mapResultSetToSeance(resultSet);
                        upcomingSessions.add(seance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return upcomingSessions;
    }

    /**
     * Récupère toutes les séances passées pour un utilisateur donné avec un statut de paiement en attente.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return Une liste de séances passées avec paiement en attente pour l'utilisateur.
     */
    public List<Seance> getPendingPaymentsByUserId(int userId) {
        List<Seance> pendingPayments = new ArrayList<>();

        try {
            String query = "SELECT * FROM seance WHERE idUser = ? AND date < CURRENT_TIMESTAMP AND statutPaiement = 'En Attente'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Seance seance = mapResultSetToSeance(resultSet);
                        pendingPayments.add(seance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingPayments;
    }

    /**
     * Marque une séance comme payée dans la base de données.
     *
     * @param idSeance L'identifiant de la séance à marquer comme payée.
     */
    @Override
    public void paySeance(int idSeance) {
        try {
            String query = "UPDATE seance SET statutPaiement = ? WHERE idSeance = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "Payé");
                statement.setInt(2, idSeance);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rembourse une séance dans la base de données.
     *
     * @param idSeance L'identifiant de la séance à rembourser.
     */
    @Override
    public void refundSeance(int idSeance) {
        try {
            String query = "UPDATE seance SET statutPaiement = ? WHERE idSeance = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "Remboursé");
                statement.setInt(2, idSeance);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
