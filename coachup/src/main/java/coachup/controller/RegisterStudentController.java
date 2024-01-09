package coachup.controller;

import coachup.MainApp;
import coachup.facade.UserFacade;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterStudentController {

    private MainApp mainApp = new MainApp();

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Gère l'action du bouton de retour vers la page de connexion.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        mainApp.showLoginPage();
    }

    /**
     * Gère l'action du bouton d'inscription d'un étudiant.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     * @throws SQLException           En cas d'erreur SQL.
     * @throws ClassNotFoundException Si la classe spécifiée n'a pas pu être trouvée.
     * @throws IOException            En cas d'erreur d'entrée/sortie.
     */
    @FXML
    public void registerButtonAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String nom = nomField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Créer un objet User avec les informations du formulaire
        User newUser = new User();
        newUser.setNom(nom);
        newUser.setEmail(email);
        newUser.setMotDePasse(password);
        newUser.setRole("student");

        // Appeler la méthode d'ajout d'utilisateur de votre façade ou service
        UserFacade userFacade = UserFacade.getInstance();
        int result = userFacade.addUser(newUser);

        // Vérifier le résultat de l'ajout et naviguer vers la page de connexion si réussi
        if (result != -1) {
            mainApp.showLoginPage();
        } else {
            // Gérer les erreurs d'inscription si nécessaire
        }

        // Réinitialiser les champs du formulaire après l'inscription
        nomField.clear();
        emailField.clear();
        passwordField.clear();
    }
}
