package coachup.controller;

import coachup.MainApp;
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

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        mainApp.showLoginPage();
    }

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
        // Par exemple : mainApp.getUserFacade().addUser(newUser);
        if(mainApp.registerStudentUser(newUser) != -1){
            mainApp.showLoginPage();
        }
        else{

        }
        // Réinitialiser les champs du formulaire après l'inscription
        nomField.clear();
        emailField.clear();
        passwordField.clear();
    }
}
