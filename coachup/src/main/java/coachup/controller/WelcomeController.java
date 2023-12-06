package coachup.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import coachup.model.User;

/**
 * Contrôleur pour la page de bienvenue (Welcome).
 */
public class WelcomeController {

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
    public void logoutButtonAction(javafx.event.ActionEvent actionEvent) {
        // Logique déconnexion
    }
}
