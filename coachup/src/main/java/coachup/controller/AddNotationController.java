package coachup.controller;

import coachup.MainApp;
import coachup.facade.NotationFacade;
import coachup.model.Notation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.sql.SQLException;

public class AddNotationController {

    @FXML
    private Slider notationSlider;
    @FXML
    private TextArea commentField;

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void addNotationButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        float note= (float) notationSlider.getValue();
        int CoachId = 1;
        int UserId = 1;
        Notation notation = new Notation();
        notation.setComment(commentField.getText());
        notation.setNote(note);
        notation.setCoachId(CoachId);
        notation.setUserId(UserId);
        if (NotationFacade.getInstance().addNotation(notation)){
            System.out.println("Notation added");
        }
        else {

            System.out.println("Notation failed");
        }
    }
}
