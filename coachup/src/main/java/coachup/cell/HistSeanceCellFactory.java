package coachup.cell;

import coachup.model.Seance;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Cette classe implémente l'interface JavaFX Callback pour personnaliser l'affichage des éléments
 * de la ListView pour les objets de type Seance dans l'historique des séances.
 */
public class HistSeanceCellFactory implements Callback<ListView<Seance>, ListCell<Seance>> {

    /**
     * Appelé lorsqu'une cellule de la ListView doit être créée ou mise à jour.
     *
     * @param param La ListView à laquelle cette cellule appartient.
     * @return Une instance de ListCell pour les objets Seance avec des détails spécifiques à l'historique des séances.
     */
    @Override
    public ListCell<Seance> call(ListView<Seance> param) {
        return new ListCell<Seance>() {

            /**
             * Appelé lorsqu'une cellule doit être mise à jour avec un nouvel élément.
             *
             * @param seance L'objet Seance à afficher dans la cellule.
             * @param empty Indique si la cellule doit être vide.
             */
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
