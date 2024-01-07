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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSeanceDetails(Seance seance) {
        this.currentSeance = seance;
    }

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

    @FXML
    public void handleReturnButton() {
        mainApp.showHistSeance();
    }
}
