package coachup.cell;

import coachup.model.Categorie;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CategorieManagementCellFactory implements Callback<ListView<Categorie>, ListCell<Categorie>> {

    @Override
    public ListCell<Categorie> call(ListView<Categorie> param) {
        return new ListCell<Categorie>() {
            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom() + " - " + item.getDescription());
                }
            }
        };
    }
}
