package coachup.cell;

import coachup.model.User;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class UserCellFactory implements Callback<ListView<User>, ListCell<User>> {

    @Override
    public ListCell<User> call(ListView<User> param) {
        return new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getNom()); // Adapt this based on your User class
                } else {
                    setText(null);
                }
            }
        };
    }
}
