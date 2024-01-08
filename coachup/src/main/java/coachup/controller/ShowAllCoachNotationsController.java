package coachup.controller;

import coachup.MainApp;
import coachup.facade.NotationFacade;
import coachup.model.Notation;
import coachup.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowAllCoachNotationsController implements Initializable {

    @FXML
    private VBox notationBox;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    public void backButtonAction() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {;
        // Suppose que l'ID du coach est 1
        Notation[] notations = new Notation[0];
        try {
            notations = NotationFacade.getInstance().getNotationByCoachId(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Notation notation : notations) {
            HBox notationHBox = new HBox();
            notationHBox.getChildren().add(new Label(" Note: " + notation.getNote()));
            notationHBox.getChildren().add(new Label(" Commentaire: " + notation.getComment()));
            notationHBox.getChildren().add(new Label(" Pseudo de l'utilisateur : " + notation.getUserName()));
            notationBox.getChildren().add(notationHBox);
        }
    }
}