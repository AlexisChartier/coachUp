package coachup.dao;

import coachup.model.Creneau_dispo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Implémentation de l'interface CreneauDispoDAO pour la gestion des opérations CRUD (Create, Read, Update, Delete) des créneaux disponibles dans une base de données PostgreSQL.
 */
public class CreneauDispoDAOPGSQL extends CreneauDispoDAO {

    private Connection connection;

    /**
     * Établit une connexion à la base de données PostgreSQL.
     *
     * @throws SQLException           Si une erreur survient lors de la connexion à la base de données.
     * @throws ClassNotFoundException Si la classe du pilote JDBC n'est pas trouvée.
     */
    private void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://dpg-clotmjpoh6hc73bo1gng-a.oregon-postgres.render.com/coachup";
        String username = "root";
        String password = "fv1kOEqOAKVg5xiHKJkfaUI46u3sGlO6";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Constructeur de la classe. Établit une connexion à la base de données PostgreSQL.
     *
     * @throws SQLException           Si une erreur survient lors de la connexion à la base de données.
     * @throws ClassNotFoundException Si la classe du pilote JDBC n'est pas trouvée.
     */
    public CreneauDispoDAOPGSQL() throws SQLException, ClassNotFoundException {
        connect();
    }

    /**
     * Ajoute un créneau disponible à la base de données.
     *
     * @param creneau_dispo L'objet Creneau_dispo à ajouter.
     * @return L'identifiant du créneau ajouté.
     */
    @Override
    public int addCreneauDispo(Creneau_dispo creneau_dispo) {
        try {
            String[] returnid = {"creneau_dispo_id"};
            String query = "INSERT INTO creneau_dispo(date_debut, date_fin, coachid) VALUES (?, ?, ?)";
            try (java.sql.PreparedStatement statement = connection.prepareStatement(query, returnid)) {
                statement.setTimestamp(1, creneau_dispo.getDateDebutTimestamp());
                statement.setTimestamp(2, creneau_dispo.getDateFinTimestamp());
                statement.setInt(3, creneau_dispo.getCoachId());

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();

                System.out.println("Rows affected: " + rowsAffected); // Ajout d'un message de débogage

                if (rowsAffected > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int generatedId = resultSet.getInt(1);
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
    public Creneau_dispo getCreneauById(int id) throws SQLException {
        Creneau_dispo creneau = null;

        try {
            // Ouverture de la connexion à la base de données
            // Préparation de la requête SQL pour récupérer le créneau par son ID
            String query = "SELECT * FROM creneau_dispo WHERE creneau_dispo_id = ?";
            try (PreparedStatement statement = this.connection.prepareStatement(query)) {
                // Assignation de la valeur à l'ID du créneau
                statement.setInt(1, id);

                // Exécution de la requête
                ResultSet resultSet = statement.executeQuery();

                // Traitement du résultat
                if (resultSet.next()) {
                    // Création d'un objet Creneau_dispo à partir des données récupérées
                    // Assurez-vous d'avoir une classe Creneau_dispo avec un constructeur approprié
                    LocalDateTime date_debutWithoutTimezone = resultSet.getTimestamp("date_debut").toLocalDateTime();
                    ZonedDateTime date_debut = date_debutWithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    LocalDateTime date_finWithoutTimezone = resultSet.getTimestamp("date_fin").toLocalDateTime();
                    ZonedDateTime date_fin = date_finWithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    creneau = new Creneau_dispo(
                            date_debut,
                           date_fin,
                            resultSet.getInt("coachid"),
                            resultSet.getInt("creneau_dispo_id")
                    );
                    creneau.setReserve(resultSet.getBoolean("reserve"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return creneau;
    }

    /**
     * Renvoie une liste de créneaux disponibles pour une date spécifiée.
     *
     * @param year  L'année de la date.
     * @param month Le mois de la date.
     * @param day   Le jour de la date.
     * @return Une liste d'objets Creneau_dispo correspondant à la date spécifiée.
     */
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
                    LocalDateTime date_debutWithoutTimezone = resultSet.getTimestamp("date_debut").toLocalDateTime();
                    ZonedDateTime date_debut = date_debutWithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    LocalDateTime date_finWithoutTimezone = resultSet.getTimestamp("date_fin").toLocalDateTime();
                    ZonedDateTime date_fin = date_finWithoutTimezone.atZone(ZoneId.of("Europe/Paris"));
                    Creneau_dispo creneau = new Creneau_dispo(date_debut, date_fin, resultSet.getInt("coachid"), resultSet.getInt("creneau_dispo_id"));
                    creneaux.add(creneau);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creneaux;
    }

    /**
     * Renvoie une liste de créneaux disponibles pour une date spécifiée et un coach spécifié.
     *
     * @param year    L'année de la date.
     * @param month   Le mois de la date.
     * @param day     Le jour de la date.
     * @param coachId L'identifiant du coach.
     * @return Une liste d'objets Creneau_dispo correspondant à la date et au coach spécifiés.
     */
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

    /**
     * Réserve un créneau disponible dans la base de données.
     *
     * @param creneau_dispo L'objet Creneau_dispo à réserver.
     * @param iduser        L'identifiant de l'utilisateur réservant le créneau.
     * @param idCategorie   L'identifiant de la catégorie associée à l'utilisateur.
     * @throws SQLException Si une erreur survient lors de l'accès à la base de données.
     */
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Marque un créneau comme réservé dans la base de données.
     *
     * @param id L'identifiant du créneau à marquer comme réservé.
     * @throws SQLException Si une erreur survient lors de l'accès à la base de données.
     */
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Supprime un créneau disponible de la base de données.
     *
     * @param creneauDispoId L'identifiant du créneau à supprimer.
     * @return True si la suppression a réussi, False sinon.
     */
    @Override
    public boolean deleteCreneauDispo(int creneauDispoId) {
        try {
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

    /**
     * Modifie les informations d'un créneau disponible dans la base de données.
     *
     * @param creneau_dispo L'objet Creneau_dispo modifié.
     * @return True si la modification a réussi, False sinon.
     */
    @Override
    public boolean modifyCreneauDispo(Creneau_dispo creneau_dispo) {
        try {
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
