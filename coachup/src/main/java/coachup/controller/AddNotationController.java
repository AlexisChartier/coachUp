package coachup.controller;

import coachup.MainApp;
import coachup.facade.NotationFacade;
import coachup.facade.UserFacade;
import coachup.model.Notation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import coachup.model.User;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNotationController implements Initializable{

    @FXML
    private Slider notationSlider;
    @FXML
    private TextArea commentField;

    @FXML
    private Label nomducoachLabel;

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UserFacade userFacade = UserFacade.getInstance();
            User coach= userFacade.getUserById(userFacade.getCoachId());
            String coachName = coach.getNom();
            nomducoachLabel.setText(coachName);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void addNotationButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        float note= (float) notationSlider.getValue();
        UserFacade userFacade = UserFacade.getInstance();
        int CoachId = userFacade.getCoachId();
        int UserId = userFacade.getCurrentUser().getIdUtilisateur();
        Notation notation = new Notation();
        notation.setComment(commentField.getText());
        notation.setNote(note);
        notation.setCoachId(CoachId);
        notation.setUserId(UserId);
        if (NotationFacade.getInstance().addNotation(notation)!=-1){
            System.out.println("Notation added");
        }
        else {

            System.out.println("Notation failed");
        }
    }


    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
