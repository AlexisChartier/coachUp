package coachup.dao;

import coachup.model.Creneau_dispo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class CreneauDispoDAOPGSQL extends CreneauDispoDAO {

    private Connection connection;

    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup"; // Remplacez avec les informations de votre serveur PostgreSQL
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    public CreneauDispoDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

@Override
public int addCreneauDispo(Creneau_dispo creneau_dispo) {
    try{
        String query = "INSERT INTO creneau_dispo(date_debut, date_fin, coachid) VALUES (?, ?, ?)";
        try (java.sql.PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, creneau_dispo.getDateDebutTimestamp());
            statement.setTimestamp(2, creneau_dispo.getDateFinTimestamp());
            statement.setInt(3, creneau_dispo.getCoachId());

            // Exécution de la requête
            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected); // Ajout d'un message de débogage

            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(4);
                    creneau_dispo.setCreneauDispoId(generatedId);
                    return generatedId;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQLException: " + e.getMessage()); // Ajout d'un message de débogage
    }
    return -1; // Retourner -1 ou une autre valeur pour indiquer qu'une erreur s'est produite
}

    @Override
    public List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day) {
        List<Creneau_dispo> creneaux = new ArrayList<>();
        try {
            String query = "SELECT * FROM creneau_dispo WHERE EXTRACT(YEAR FROM date_debut) = ? AND EXTRACT(MONTH FROM date_debut) = ? AND EXTRACT(DAY FROM date_debut) = ? ORDER BY date_debut ASC";
            try (java.sql.PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, day);


                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    LocalDateTime date_debutwithoutTimezone = resultSet.getTimestamp("date_debut").toLocalDateTime();
                    ZonedDateTime date_debut = date_debutwithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    LocalDateTime date_finwithoutTimezone = resultSet.getTimestamp("date_fin").toLocalDateTime();
                    ZonedDateTime date_fin = date_finwithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    Creneau_dispo creneau = new Creneau_dispo(date_debut,date_fin,resultSet.getInt("coachid"),resultSet.getInt("creneau_dispo_id"));
                    creneaux.add(creneau);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creneaux;
    }

    @Override
    public List<Creneau_dispo> getCreneauByDayAndCoachId(Integer year, Integer month, Integer day, Integer coachId) {
        List<Creneau_dispo> creneaux = new ArrayList<>();
        try {
            String query = "SELECT * FROM creneau_dispo WHERE EXTRACT(YEAR FROM date_debut) = ? " +
                    "AND EXTRACT(MONTH FROM date_debut) = ? " +
                    "AND EXTRACT(DAY FROM date_debut) = ? " +
                    "AND coachid = ? AND reserve = false ORDER BY date_debut ASC";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, day);
                statement.setInt(4, coachId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    LocalDateTime dateDebutWithoutTimezone = resultSet.getTimestamp("date_debut").toLocalDateTime();
                    ZonedDateTime dateDebut = dateDebutWithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    LocalDateTime dateFinWithoutTimezone = resultSet.getTimestamp("date_fin").toLocalDateTime();
                    ZonedDateTime dateFin = dateFinWithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    Creneau_dispo creneau = new Creneau_dispo(dateDebut, dateFin, resultSet.getInt("coachid"), resultSet.getInt("creneau_dispo_id"));
                    creneaux.add(creneau);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creneaux;
    }

    @Override
    public void reserverCreneau(Creneau_dispo creneau_dispo, int iduser, int idCategorie) throws SQLException {
        try {
            // Ouverture de la connexion à la base de données
            // Préparation de la requête SQL pour insérer une nouvelle séance
            String query = "INSERT INTO seance (date, idcoach, idcategorie, iduser, statut_paiement, datefin) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Extraction de la date de début et de fin du créneau
                LocalDateTime dateDebut = creneau_dispo.getDateDebut().toLocalDateTime();
                LocalDateTime dateFin = creneau_dispo.getDateFin().toLocalDateTime();

                Timestamp timestamp = creneau_dispo.getDateDebutTimestamp();

                // Assignation des valeurs aux paramètres de la requête
                Date date = new Date(timestamp.getTime());
                statement.setDate(1, date);
                statement.setInt(2, creneau_dispo.getCoachId());
                statement.setInt(3, idCategorie);
                statement.setInt(4, iduser);
                statement.setString(4, "En Attente");
                statement.setTimestamp(5, creneau_dispo.getDateFinTimestamp());

                // Exécution de la requête
                statement.executeUpdate();
            } finally {
                // Fermeture de la connexion
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void setReserved(int id) throws SQLException {
        try {
            // Ouverture de la connexion à la base de données
            // Préparation de la requête SQL pour mettre à jour l'attribut "reserve"
            String query = "UPDATE creneau_dispo SET reserve = true WHERE creneau_dispo_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Assignation de la valeur à l'ID du créneau
                statement.setInt(1, id);

                // Exécution de la requête
                statement.executeUpdate();
            } finally {
                // Fermeture de la connexion
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean deleteCreneauDispo(int creneauDispoId){
        try{
            String query = "DELETE FROM creneau_dispo WHERE creneau_dispo_id = ?";
            try (java.sql.PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, creneauDispoId);

                // Exécution de la requête
                System.out.println(statement);
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modifyCreneauDispo(Creneau_dispo creneau_dispo){
        try{
            String query = "UPDATE creneau_dispo SET date_debut = ?, date_fin = ? WHERE creneau_dispo_id = ?";
            try (java.sql.PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setTimestamp(1, creneau_dispo.getDateDebutTimestamp());
                statement.setTimestamp(2, creneau_dispo.getDateFinTimestamp());
                statement.setInt(3, creneau_dispo.getCreneauDispoId());

                // Exécution de la requête
                System.out.println(statement);
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
