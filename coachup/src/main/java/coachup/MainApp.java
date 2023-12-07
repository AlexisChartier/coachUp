package coachup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import coachup.controller.LoginController;
import coachup.controller.WelcomeController;
import coachup.facade.UserFacade;
import coachup.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Classe principale de l'application CoachUp.
 */
public class MainApp extends Application {

    /**
     * Méthode principale de l'application qui lance l'interface graphique.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;

    /**
     * Méthode appelée au démarrage de l'application pour initialiser l'interface graphique.
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @throws Exception En cas d'erreur lors du chargement de l'interface graphique.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CoachUp");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        Parent root = (Parent) loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        Scene scene = new Scene(root);
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * Affiche la page de bienvenue après une authentification réussie.
     *
     * @param user L'utilisateur authentifié.
     */
    public void showWelcomePage(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/welcome.fxml")));
            Parent root = (Parent) loader.load();

            WelcomeController welcomeController = loader.getController();
            welcomeController.setUser(user);

            Stage welcomeStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = welcomeStage;
            Scene scene = new Scene(root);
            welcomeStage.setScene(scene);
            welcomeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authentifie l'utilisateur en vérifiant les informations de connexion.
     *
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si l'authentification est réussie, false sinon.
     */
    public boolean authenticateUser(String email, String password) throws SQLException, ClassNotFoundException {
        UserFacade userFacade = UserFacade.getInstance();

        boolean isAuthenticated = userFacade.loginUser(email, password);

        if (isAuthenticated) {
            User user = userFacade.getUserByEmail(email);
            showWelcomePage(user);
        } else {
            System.out.println("Authentication failed. Invalid email or password.");
        }

        return isAuthenticated;
    }
}
