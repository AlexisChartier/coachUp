package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.facade.SeanceFacade;
import coachup.model.Seance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class PendingSeanceDetailController {

    private MainApp mainApp;

    @FXML
    private Label categorieLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label coachLabel;

    private Seance seance;

    @FXML
    private Button refundButton;

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initialise les éléments graphiques avec les détails de la séance en attente.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        seance = SeanceFacade.getInstance().getManagedSeance();
        categorieLabel.setText(CategorieFacade.getInstance().getCategoryById(seance.getIdCategorie()).getNom());
        dateLabel.setText(seance.getDate().toString());
        coachLabel.setText(CoachFacade.getInstance().getCoachById(seance.getIdCoach()).getNom());
    }

    /**
     * Gère l'action du bouton de retour.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showPendingPayment();
    }

    /**
     * Gère l'action du bouton de paiement de la séance.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handlePayButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SeanceFacade.getInstance().paySeance(seance);
        mainApp.showHubPayment();
    }
}
