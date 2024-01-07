package coachup.cell;

import coachup.facade.CategorieFacade;
import coachup.model.Categorie;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.sql.SQLException;

public class CategorieCellFactory implements Callback<ListView<Categorie>, ListCell<Categorie>> {
    @Override
    public ListCell<Categorie> call(ListView<Categorie> param) {
        return new ListCell<Categorie>() {
            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getNom());
                } else {
                    setText("");
                }
            }
        };
    }
}

