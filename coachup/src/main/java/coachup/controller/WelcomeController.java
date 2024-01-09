package coachup.controller;

import coachup.MainApp;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Contrôleur pour la page de bienvenue (Welcome).
 */
public class WelcomeController {

    private MainApp mainApp = new MainApp();

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
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
            String welcomeMessage = "Welcome, " + user.getNom();
            welcomeLabel.setText(welcomeMessage);
        }
    }

    /**
     * Gère l'action du bouton de déconnexion.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page de connexion.
     */
    @FXML
    public void logoutButtonAction(ActionEvent actionEvent) throws IOException {
        this.user = null;
        mainApp.showLoginPage();
    }

    /**
     * Gère l'action du bouton de profil.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void profileButtonAction(ActionEvent actionEvent) {
        mainApp.showDetailPage(user, null);
    }

    /**
     * Gère l'action du bouton d'ajout de notation.
     *
     * @param event L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page d'ajout de notation.
     */
    @FXML
    public void AddNotationButtonAction(ActionEvent event) throws IOException {
        mainApp.showAddNotation();
    }

    /**
     * Gère l'action du bouton de modification de notation.
     *
     * @param event L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page de modification de notation.
     */
    @FXML
    public void ModifyNotationButtonAction(ActionEvent event) throws IOException {
        mainApp.showModifyNotation();
    }

    /**
     * Gère l'action du bouton pour afficher toutes les notations des coachs.
     *
     * @param event L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page des notations des coachs.
     */
    @FXML
    public void showAllCoachNotationsButtonAction(ActionEvent event) throws IOException {
        mainApp.ShowAllCoachNotations();
    }

    /**
     * Gère l'action du bouton pour afficher toutes les notations des utilisateurs.
     *
     * @param event L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page des notations des utilisateurs.
     */
    @FXML
    public void showAllUserNotationsButtonAction(ActionEvent event) throws IOException {
        mainApp.ShowAllUserNotations();
    }

    /**
     * Gère l'action du bouton pour afficher le calendrier.
     *
     * @param event L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur lors de la redirection vers la page du calendrier.
     */
    @FXML
    public void showCalendarButtonAction(ActionEvent event) throws IOException {
        mainApp.showCalendarPage();
    }

    /**
     * Gère l'action du bouton pour afficher l'historique des séances.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void seanceHistoryButtonAction(ActionEvent actionEvent) {
        mainApp.showHistSeance();
    }

    /**
     * Gère l'action du bouton pour la gestion des paiements.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void paymentGestionButtonAction(ActionEvent actionEvent) {
        mainApp.showHubPayment();
    }

    /**
     * Gère l'action du bouton pour la recherche de coachs.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void RechercheCoachButtonAction(ActionEvent actionEvent) {
        mainApp.showRechercheCoach();
    }
}
