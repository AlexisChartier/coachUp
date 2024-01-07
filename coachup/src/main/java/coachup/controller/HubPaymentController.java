package coachup.controller;

import coachup.MainApp;
import coachup.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class HubPaymentController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handlePendingPaymentsButton(ActionEvent actionEvent) {
        mainApp.showPendingPayment();
    }

    @FXML
    public void handleUpcomingSessionsButton(ActionEvent actionEvent) {
        mainApp.showUpComingSessions();
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
