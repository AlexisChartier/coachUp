package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.facade.SeanceFacade;
import coachup.model.Seance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class SeanceDetailController {

    private MainApp mainApp;
    private Seance currentSeance;

    @FXML
    private Label categorieLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label coachLabel;

    @FXML
    private Label statutPaiementLabel;

    @FXML
    private Button returnButton;

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Définit les détails de la séance à afficher.
     *
     * @param seance La séance dont les détails doivent être affichés.
     */
    public void setSeanceDetails(Seance seance) {
        this.currentSeance = seance;
    }

    /**
     * Initialise le contrôleur, appelé automatiquement après le chargement du fichier FXML.
     * Affiche les détails de la séance sélectionnée.
     */
    @FXML
    public void initialize() {
        try {
            currentSeance = SeanceFacade.getInstance().getManagedSeance();
            if (currentSeance != null) {
                categorieLabel.setText(CategorieFacade.getInstance().getCategoryById(currentSeance.getIdCategorie()).getNom());
                dateLabel.setText(currentSeance.getDate().toString());
                coachLabel.setText(CoachFacade.getInstance().getCoachById(currentSeance.getIdCoach()).getNom());
                statutPaiementLabel.setText(currentSeance.getStatutPaiement());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton de retour vers la page d'historique des séances.
     */
    @FXML
    public void handleReturnButton() {
        mainApp.showHistSeance();
    }
}
