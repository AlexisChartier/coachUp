package coachup.controller;

import coachup.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterCoachController {

    @FXML
    public TextField diplomeField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField nameField;
    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    public void registerCoachButtonAction(ActionEvent event) {
    }

    public void backButtonAction(ActionEvent event) {
    }
}
