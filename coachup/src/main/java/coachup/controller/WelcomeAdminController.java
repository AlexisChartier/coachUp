package coachup.controller;

import coachup.MainApp;
import coachup.facade.CoachFacade;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeAdminController {

    private MainApp mainApp = new MainApp();

    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void userListButtonAction(ActionEvent event) {
        mainApp.showUserList(currentUser);
    }

    @FXML
    public void coachApprovalListButtonAction(ActionEvent event) {
        mainApp.showCoachApprovalList(currentUser);
    }

    @FXML
    public void logoutButtonAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        this.currentUser = null;
        mainApp.showLoginPage();
    }
    public void manageCategoriesButtonAction(ActionEvent actionEvent) {
        mainApp.showCategoriesList(currentUser);
    }
}
