package coachup.cell;

import coachup.model.Coach;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CoachCellFactory implements Callback<ListView<Coach>, ListCell<Coach>> {

    @Override
    public ListCell<Coach> call(ListView<Coach> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Coach coach, boolean empty) {
                super.updateItem(coach, empty);
                if (empty || coach == null) {
                    setText(null);
                } else {
                    setText(coach.getNom()); // Remplacez avec la propriété que vous souhaitez afficher
                }
            }
        };
    }
}
