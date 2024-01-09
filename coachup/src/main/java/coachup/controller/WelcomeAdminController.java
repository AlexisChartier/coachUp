package coachup.controller;

import coachup.MainApp;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeAdminController {

    private MainApp mainApp = new MainApp();

    private User currentUser;

    /**
     * Définit l'utilisateur actuellement connecté.
     *
     * @param currentUser L'utilisateur actuellement connecté.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Gère l'action du bouton pour afficher la liste des utilisateurs.
     *
     * @param event L'événement d'action.
     */
    @FXML
    public void userListButtonAction(ActionEvent event) {
        mainApp.showUserList(currentUser);
    }

    /**
     * Gère l'action du bouton pour afficher la liste des coachs en attente d'approbation.
     *
     * @param event L'événement d'action.
     */
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
