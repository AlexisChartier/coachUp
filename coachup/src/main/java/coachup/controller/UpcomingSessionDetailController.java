package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.facade.SeanceFacade;
import coachup.model.Seance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class UpcomingSessionDetailController {
    @FXML
    public Label categorieLabel;
    @FXML
    public Label dateLabel;

    @FXML
    public Label coachLabel;

    private MainApp mainApp;

    private Seance seance;

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Gère l'action du bouton de remboursement de la séance.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException           Si une erreur SQL survient.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée.
     */
    @FXML
    public void handleRefundButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SeanceFacade.getInstance().refundSeance(seance);
        mainApp.showHubPayment();
    }

    /**
     * Initialise le contrôleur, appelé automatiquement après le chargement du fichier FXML.
     * Affiche les détails de la séance à venir.
     */
    @FXML
    public void initialize() {
        try {
            seance = SeanceFacade.getInstance().getManagedSeance();
            categorieLabel.setText(CategorieFacade.getInstance().getCategoryById(seance.getIdCategorie()).getNom());
            dateLabel.setText(seance.getDate().toString());
            coachLabel.setText(CoachFacade.getInstance().getCoachById(seance.getIdCoach()).getNom());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gère l'action du bouton de retour vers la page des séances à venir.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showUpComingSessions();
    }
}
