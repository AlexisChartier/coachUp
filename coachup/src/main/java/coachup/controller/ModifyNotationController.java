package coachup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import coachup.MainApp;
import coachup.model.Notation;
import javafx.scene.control.TextArea;


import java.sql.SQLException;

public class ModifyNotationController {

    @FXML
    private Slider notationSlider;
    @FXML
    private TextArea commentField;

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void modifyNotationButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        float note= (float) notationSlider.getValue();
        int CoachId = 1;
        int UserId = 1;
        Notation notation = new Notation();
        notation.setComment(commentField.getText());
        notation.setNote(note);
        notation.setCoachId(CoachId);
        notation.setUserId(UserId);
        if (mainApp.addNotation(notation)){
            System.out.println("Notation added");
        }
        else {

            System.out.println("Notation failed");
        }
    }

    @FXML
    private void deleteNotationButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        float note= (float) notationSlider.getValue();
        int CoachId = 1;
        int UserId = 1;
        Notation notation = new Notation();
        notation.setComment(commentField.getText());
        notation.setNote(note);
        notation.setCoachId(CoachId);
        notation.setUserId(UserId);
        if (mainApp.addNotation(notation)){
            System.out.println("Notation added");
        }
        else {

            System.out.println("Notation failed");
        }
    }

}
