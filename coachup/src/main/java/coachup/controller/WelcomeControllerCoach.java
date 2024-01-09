package coachup.controller;

import coachup.MainApp;
import coachup.facade.CoachFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Contrôleur pour la page de bienvenue d'un coach (Welcome).
 */
public class WelcomeControllerCoach {

    private MainApp mainApp;

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public Label welcomeLabel;

    /**
     * Initialise le contrôleur après que son élément racine a été complètement traité.
     *
     * @throws SQLException En cas d'erreur SQL lors de l'initialisation.
     * @throws ClassNotFoundException Si la classe n'a pas été trouvée lors de l'initialisation.
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        welcomeLabel.setText(CoachFacade.getInstance().getCurrentCoach().getNom());
    }

    /**
     * Gère l'action du bouton de déconnexion.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page de connexion.
     * @throws SQLException En cas d'erreur SQL lors de la déconnexion.
     * @throws ClassNotFoundException Si la classe n'a pas été trouvée lors de la déconnexion.
     */
    @FXML
    public void logoutButtonAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        CoachFacade.getInstance().setCurrentCoach(null);
        mainApp.showLoginPage();
    }

    /**
     * Gère l'action du bouton pour afficher le profil du coach.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void profileButtonAction(ActionEvent actionEvent) {
        mainApp.showDetailCoach();
    }

    /**
     * Gère l'action du bouton pour afficher le calendrier.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void showCalendarButtonAction(ActionEvent actionEvent) {
        mainApp.showCalendarPage();
    }

    /**
     * Gère l'action du bouton pour afficher toutes les notations du coach.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void showAllCoachNotationsButtonAction(ActionEvent actionEvent) {
        mainApp.ShowAllCoachNotations();
    }
}
