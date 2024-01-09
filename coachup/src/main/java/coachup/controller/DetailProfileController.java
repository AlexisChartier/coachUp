package coachup.controller;

import coachup.MainApp;
import coachup.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import coachup.model.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Contrôleur pour la vue de détail du profil utilisateur.
 */
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

    private User user;

    /**
     * Initialise la vue avec les informations actuelles de l'utilisateur.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        // Afficher les informations actuelles de l'utilisateur lors de l'initialisation
        if (UserFacade.getInstance().getCurrentUser().getRole().equals("admin")) {
            user = UserFacade.getInstance().getManagedUser();
        } else {
            user = UserFacade.getInstance().getCurrentUser();
        }
        currentNameLabel.setText("Nom actuel : " + user.getNom());
        currentEmailLabel.setText("E-mail actuel : " + user.getEmail());
        currentPasswordField.setText("Mot de passe actuel : " + user.getMotDePasse());
    }

    /**
     * Gère le bouton de sauvegarde.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleSaveButton() throws SQLException, ClassNotFoundException {
        // Mettre à jour les informations de l'utilisateur avec les nouvelles valeurs
        user.setNom(nameField.getText());
        user.setEmail(emailField.getText());
        user.setMotDePasse(passwordField.getText());
        UserFacade.getInstance().updateUser(user);

        // Mettre à jour les labels affichant les informations actuelles
        currentNameLabel.setText("Nom actuel : " + user.getNom());
        currentEmailLabel.setText("E-mail actuel : " + user.getEmail());
        currentPasswordField.setText("Mot de passe actuel : " + user.getMotDePasse());
    }

    /**
     * Définit l'utilisateur associé à ce contrôleur.
     *
     * @param user L'utilisateur à définir.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gère le bouton de retour.
     *
     * @param actionEvent L'événement d'action.
     */
    public void handleReturnButton(ActionEvent actionEvent) {
        if (adminUser != null) {
            mainApp.showWelcomePageAdmin(user);
        } else if (user.getRole().equals("student")) {
            mainApp.showWelcomePage(user);
        } else if (user.getRole().equals("coach")) {
            //mainApp.showWelcomePageCoach(user);
        }
    }

    /**
     * Gère le bouton de suppression.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     * @throws IOException              En cas d'erreur d'entrée/sortie.
     */
    public void handleDeleteButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        UserFacade.getInstance().deleteUser(user.getIdUtilisateur());
        mainApp.showLoginPage();
    }
}
