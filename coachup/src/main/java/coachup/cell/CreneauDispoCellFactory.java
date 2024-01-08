package coachup.cell;

import coachup.model.Creneau_dispo;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CreneauDispoCellFactory implements Callback<ListView<Creneau_dispo>, ListCell<Creneau_dispo>> {

    @Override
    public ListCell<Creneau_dispo> call(ListView<Creneau_dispo> param) {
        return new ListCell<Creneau_dispo>() {
            @Override
            protected void updateItem(Creneau_dispo creneau, boolean empty) {
                super.updateItem(creneau, empty);
                if (empty || creneau == null) {
                    setText(null);
                } else {
                    // Affichage des détails du créneau
                    setText("Début : " + creneau.getDateDebut().toLocalTime() +
                            " | Fin : " + creneau.getDateFin().toLocalTime());
                }
            }
        };
    }
}
