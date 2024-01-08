package coachup.controller;

import coachup.MainApp;
import coachup.facade.CoachFacade;
import coachup.facade.NotationFacade;
import coachup.facade.UserFacade;
import coachup.model.Notation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowAllUserNotationsController implements Initializable {

    @FXML
    private VBox notationBox;

    private UserFacade userfacade = UserFacade.getInstance();

    private MainApp mainApp = new MainApp();

    public ShowAllUserNotationsController() throws SQLException, ClassNotFoundException {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {;
        // Suppose que l'ID du coach est 1
        Notation[] notations = new Notation[0];
        try {
        notations = NotationFacade.getInstance().getNotationByUserId(UserFacade.getInstance().getCurrentUser().getIdUtilisateur());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Notation notation : notations) {
            try {
                UserFacade userfacade = UserFacade.getInstance();
                userfacade.setNotationid(notation.getNotationId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            HBox notationHBox = new HBox();
            notationHBox.getChildren().add(new Label("Nom du coach: " + notation.getCoachName()));
            notationHBox.getChildren().add(new Label("Note: " + notation.getNote()));
            notationHBox.getChildren().add(new Label("Commentaire: " + notation.getComment()));
            Button modifyButton = new Button("Modifier");

            modifyButton.setOnAction(event -> {
                userfacade.setNotationid(notation.getNotationId());
                mainApp.showModifyNotation();
            });
            notationHBox.getChildren().add(modifyButton);


            notationBox.getChildren().add(notationHBox);
        }
    }


    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}