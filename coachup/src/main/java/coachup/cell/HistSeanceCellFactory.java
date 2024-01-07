package coachup.cell;

import coachup.model.Seance;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class HistSeanceCellFactory implements Callback<ListView<Seance>, ListCell<Seance>> {

    @Override
    public ListCell<Seance> call(ListView<Seance> param) {
        return new ListCell<Seance>() {
            @Override
            protected void updateItem(Seance seance, boolean empty) {
                super.updateItem(seance, empty);
                if (empty || seance == null) {
                    setText(null);
                } else {
                    setText("Séance " + getIndex() + " - " + seance.getDate());
                }
            }
        };
    }
}
