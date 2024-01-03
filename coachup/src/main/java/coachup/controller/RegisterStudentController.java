package coachup.controller;

import coachup.MainApp;
import javafx.event.ActionEvent;

import java.io.IOException;

public class RegisterStudentController {

    private MainApp mainApp = new MainApp();

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void registerButtonAction(ActionEvent actionEvent) {
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        mainApp.showLoginPage();
    }
}
