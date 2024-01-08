package coachup.controller;

import coachup.MainApp;
import coachup.facade.CoachFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeControllerCoach {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public Label welcomeLabel;


    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        welcomeLabel.setText(CoachFacade.getInstance().getCurrentCoach().getNom());
    }

    @FXML
    public void logoutButtonAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        CoachFacade.getInstance().setCurrentCoach(null);
        mainApp.showLoginPage();
    }

    @FXML
    public void profileButtonAction(ActionEvent actionEvent) {
        mainApp.showDetailCoach();
    }

    @FXML
    public void showCalendarButtonAction(ActionEvent actionEvent) {
        mainApp.showCalendarPage();
    }

    @FXML
    public void showAllCoachNotationsButtonAction(ActionEvent actionEvent) {
        mainApp.ShowAllCoachNotations();
    }
}
