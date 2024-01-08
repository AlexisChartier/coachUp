package coachup.dao;

import coachup.model.Creneau_dispo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
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
    public boolean addCreneauDispo(Creneau_dispo creneau_dispo) {
        try{
            String query = "INSERT INTO creneau_dispo(date_debut, date_fin, coachid) VALUES (?, ?, ?)";
            try (java.sql.PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setTimestamp(1, creneau_dispo.getDateDebutTimestamp());
                statement.setTimestamp(2, creneau_dispo.getDateFinTimestamp());
                statement.setInt(3, creneau_dispo.getCoachId());

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