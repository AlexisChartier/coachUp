package coachup.controller;

import coachup.MainApp;
import coachup.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

/**
 * Contrôleur pour la vue du hub de paiement.
 */
public class HubPaymentController {

    private MainApp mainApp;

    /**
     * Définit l'application principale.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Gère le bouton pour afficher les paiements en attente.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handlePendingPaymentsButton(ActionEvent actionEvent) {
        mainApp.showPendingPayment();
    }

    /**
     * Gère le bouton pour afficher les sessions à venir.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handleUpcomingSessionsButton(ActionEvent actionEvent) {
        mainApp.showUpComingSessions();
    }

    /**
     * Gère le bouton de retour.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
