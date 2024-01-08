package coachup.controller;

import coachup.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeAdminController {

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void userListButtonAction(ActionEvent event) {
        mainApp.showUserList();
    }

    @FXML
    public void coachApprovalListButtonAction(ActionEvent event) {
    }
}
