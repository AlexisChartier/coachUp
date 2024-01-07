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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    public void handleRefundButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SeanceFacade.getInstance().refundSeance(seance);
        mainApp.showHubPayment();
    }

    @FXML
    public void initialize(){
        try {
            seance = SeanceFacade.getInstance().getManagedSeance();
            categorieLabel.setText(CategorieFacade.getInstance().getCategoryById(seance.getIdCategorie()).getNom());
            dateLabel.setText(seance.getDate().toString());
            coachLabel.setText(CoachFacade.getInstance().getCoachById(seance.getIdCoach()).getNom());
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showUpComingSessions();
    }
}
