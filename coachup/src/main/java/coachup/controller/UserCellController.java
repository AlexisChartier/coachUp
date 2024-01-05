package coachup.controller;

import coachup.facade.UserFacade;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserCellController extends ListCell<User> {


    public Text userNameText;
    public Button detailButton;
    public ImageView image;
    private User user;
    

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {
            setGraphic(null);
        } else {
            this.setUser(user);
            setGraphic(this);
        }
    }

    private void setUser(User user) {
        this.user = user;
    }

    public void detailButtonAction(ActionEvent event) {
    }

    public void deleteButtonAction(ActionEvent event) {
    }
}
