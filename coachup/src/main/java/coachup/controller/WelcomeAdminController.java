package coachup.controller;

import coachup.MainApp;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeAdminController {

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    public void userListButtonAction(ActionEvent event) {
        mainApp.showUserList(currentUser);
    }

    @FXML
    public void coachApprovalListButtonAction(ActionEvent event) {
    }
}
