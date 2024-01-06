package coachup.controller;
import coachup.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import coachup.model.User;

import java.io.IOException;

/**
 * Contrôleur pour la page de bienvenue (Welcome).
 */
public class WelcomeController {

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private Label welcomeLabel;

    private User user;

    /**
     * Définit l'utilisateur pour cette page de bienvenue.
     *
     * @param user L'utilisateur connecté.
     */
    public void setUser(User user) {
        this.user = user;
        updateWelcomeLabel();
    }

    /**
     * Met à jour l'étiquette de bienvenue avec le nom de l'utilisateur.
     */
    private void updateWelcomeLabel() {
        if (user != null) {
            System.out.println(user.getNom());
            String welc = "Welcome, " + user.getNom();
            welcomeLabel.setText(welc);
            //welcomeLabel.setText("Welcome, ".concat(user.getNom()));
        }
    }

    /**
     * Gère l'action du bouton de déconnexion.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void logoutButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        this.user = null;
        mainApp.showLoginPage();
    }

    @FXML
    public void AddNotationButtonAction(javafx.event.ActionEvent event) throws IOException {
        mainApp.showAddNotation();
    }

    @FXML
    public void ModifyNotationButtonAction(javafx.event.ActionEvent event) throws IOException {
        mainApp.showModifyNotation();
    }

    @FXML
    public void showAllCoachNotationsButtonAction(javafx.event.ActionEvent event) throws IOException {
        mainApp.ShowAllCoachNotations();
    }

    @FXML
    public void showAllUserNotationsButtonAction(javafx.event.ActionEvent event) throws IOException {
        mainApp.ShowAllUserNotations();
    }
}
