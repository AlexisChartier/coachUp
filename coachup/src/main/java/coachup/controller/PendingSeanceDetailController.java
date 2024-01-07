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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        seance = SeanceFacade.getInstance().getManagedSeance();
        categorieLabel.setText(CategorieFacade.getInstance().getCategoryById(seance.getIdCategorie()).getNom());
        dateLabel.setText(seance.getDate().toString());
        coachLabel.setText(CoachFacade.getInstance().getCoachById(seance.getIdCoach()).getNom());
    }


    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showPendingPayment();
    }

    @FXML
    public void handlePayButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SeanceFacade.getInstance().paySeance(seance);
        mainApp.showHubPayment();
    }
}