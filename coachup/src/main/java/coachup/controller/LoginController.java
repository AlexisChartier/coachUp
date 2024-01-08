package coachup.controller;

import coachup.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Contrôleur pour la page de connexion (Login).
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Instance de l'application principale
    private MainApp mainApp = new MainApp();

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Gère l'action du bouton de connexion.
     *
     * @param event L'événement déclenché par le bouton.
     */
    @FXML
    private void loginButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        // Récupère le nom d'utilisateur et le mot de passe depuis les champs de saisie
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Authentifie l'utilisateur en vérifiant les informations de connexion
        if (mainApp.authenticateUser(username, password)) {
            // Affiche la page de bienvenue si l'authentification est réussie
        } else {
            // Affiche un message en cas d'échec de l'authentification
            System.out.println("Login failed");
        }
    }

    /**
     * Gère l'action du bouton de retour.
     *
     * @param event L'événement déclenché par le bouton.
     */
    @FXML
    private void quitButtonAction(ActionEvent event) {
        // Ferme l'application en cas de clic sur le bouton de retour
        System.exit(1);
    }

    @FXML
    private void registerStudentButtonAction(ActionEvent event) {
        mainApp.showRegisterStudentPage();
    }

    @FXML
    private void registerCoachButtonAction(ActionEvent event){
        mainApp.showRegisterCoachPage();
    }
}
