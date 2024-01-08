package coachup.controller;

import coachup.MainApp;
import coachup.facade.NotationFacade;
import coachup.facade.UserFacade;
import coachup.model.Notation;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyNotationController implements Initializable {

    @FXML
    private Slider notationSlider;
    @FXML
    private TextArea commentField;

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Récupérer la notation que vous modifiez
        Notation notation = null;
        try {
            notation = NotationFacade.getInstance().getNotationById(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Définir la valeur du Slider à la note de la notation
        notationSlider.setValue(notation.getNote());
        // Définir le texte du champ de commentaire à celui de la notation
        commentField.setText(notation.getComment());
    }

    @FXML
    private void modifyNotationButtonAction(ActionEvent event) {
        try {
        Notation notation = NotationFacade.getInstance().getNotationById(2);
            notation.setComment(commentField.getText());
            notation.setNote((float) notationSlider.getValue());
            if (NotationFacade.getInstance().modifyNotation(notation)){
                System.out.println("Notation modified");
            }
            else {
                System.out.println("Notation modification failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteNotationButtonAction(ActionEvent event) {
        try {
            int notationid = 1;
            if (NotationFacade.getInstance().deleteNotation(notationid)){
                System.out.println("Notation deleted");
            }
            else {
                System.out.println("Notation delete failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}