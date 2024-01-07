package coachup.controller;

import coachup.MainApp;
import coachup.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import coachup.model.User; // Assurez-vous d'avoir votre classe User importée ici

import java.sql.SQLException;

public class DetailProfileController {

    private MainApp mainApp;

    private User adminUser;

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label currentNameLabel;

    @FXML
    private Label currentEmailLabel;

    @FXML
    private Label currentPasswordField;

    private User user; // Vous devrez définir cet utilisateur lorsque vous chargez la page

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        // Afficher les informations actuelles de l'utilisateur lors de l'initialisation
        user = UserFacade.getInstance().getCurrentUser();
        currentNameLabel.setText("Nom actuel : " + user.getNom());
        currentEmailLabel.setText("E-mail actuel : " + user.getEmail());
        currentPasswordField.setText("Mot de passe actuel : " + user.getMotDePasse());
    }

    @FXML
    public void handleSaveButton() {
        // Mettre à jour les informations de l'utilisateur avec les nouvelles valeurs
        user.setNom(nameField.getText());
        user.setEmail(emailField.getText());
        user.setMotDePasse(passwordField.getText());

        // Mettre à jour les labels affichant les informations actuelles
        currentNameLabel.setText("Nom actuel : " + user.getNom());
        currentEmailLabel.setText("E-mail actuel : " + user.getEmail());
        currentPasswordField.setText("Mot de passe actuel : " + user.getMotDePasse());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void handleReturnButton(ActionEvent actionEvent) {
        if(adminUser != null){
            mainApp.showWelcomePageAdmin(user);
        }
        else if(user.getRole().equals("student")){
            mainApp.showWelcomePage(user);
        }
        else if(user.getRole().equals("coach")){
            //mainApp.showWelcomePageCoach(user);
        }
    }
}
