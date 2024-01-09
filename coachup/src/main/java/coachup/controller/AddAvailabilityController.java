package coachup.controller;

import coachup.facade.CreneauDispoFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import coachup.model.Creneau_dispo;
import coachup.MainApp;
import javafx.scene.control.Label;
import coachup.facade.UserFacade;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la vue d'ajout de disponibilité.
 */
public class AddAvailabilityController implements Initializable {

    private Creneau_dispo creneau_dispo;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField startTimePicker;

    @FXML
    private TextField endTimePicker;

    @FXML
    private Label dateLabel;

    private MainApp mainApp = new MainApp();

    /**
     * Configure l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Définit le créneau de disponibilité associé à ce contrôleur.
     *
     * @param creneau_dispo Le créneau de disponibilité.
     */
    public void setCreneau_dispo(Creneau_dispo creneau_dispo) {
        this.creneau_dispo = creneau_dispo;
        updateDateLabel();
    }

    /**
     * Initialise le contrôleur après que son élément racine a été complètement traité.
     *
     * @param url L'emplacement initial pour la racine de l'objet.
     * @param rb  Les ressources locales utilisées pour localiser l'objet racine.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Gère l'action d'annulation et affiche la page du calendrier principal.
     */
    @FXML
    private void cancel() {
        mainApp.showCalendarPage();
    }

    /**
     * Gère l'action de soumission de la disponibilité.
     *
     * @throws SQLException            En cas d'erreur SQL lors de l'ajout du créneau de disponibilité.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors de l'ajout du créneau de disponibilité.
     */
    @FXML
    private void submitAvailability() throws SQLException, ClassNotFoundException {
        String startTime = startTimePicker.getText();
        ZonedDateTime newStartDate = creneau_dispo.getDateDebut().withHour(Integer.parseInt(startTime.substring(0, 2)));
        String endTime = endTimePicker.getText();
        ZonedDateTime newEndDate = creneau_dispo.getDateFin().withHour(Integer.parseInt(endTime.substring(0, 2)));
        UserFacade userFacade = UserFacade.getInstance();
        int coachId = userFacade.getCurrentUser().getIdUtilisateur();

        Creneau_dispo creneau_dispo = new Creneau_dispo(newStartDate, newEndDate, coachId, -1);
        CreneauDispoFacade.getInstance().addCreneauDispo(creneau_dispo);
    }

    /**
     * Met à jour l'étiquette de date avec la date actuelle du créneau de disponibilité.
     */
    public void updateDateLabel() {
        String currentDate = creneau_dispo.getDateDebut().getDayOfWeek().toString() + " "
                + creneau_dispo.getDateDebut().getDayOfMonth() + " " + creneau_dispo.getDateDebut().getMonth().toString()
                + " " + creneau_dispo.getDateDebut().getYear();
        dateLabel.setText(currentDate);
    }
}
