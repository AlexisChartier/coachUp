package coachup.cell;

import coachup.model.Coach;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Cette classe implémente l'interface JavaFX Callback pour personnaliser l'affichage des éléments
 * de la ListView pour les objets de type Coach.
 */
public class CoachCellFactory implements Callback<ListView<Coach>, ListCell<Coach>> {

    /**
     * Appelé lorsqu'une cellule de la ListView doit être créée ou mise à jour.
     *
     * @param param La ListView à laquelle cette cellule appartient.
     * @return Une instance de ListCell pour les objets Coach.
     */
    @Override
    public ListCell<Coach> call(ListView<Coach> param) {
        return new ListCell<Coach>() {

            /**
             * Appelé lorsqu'une cellule doit être mise à jour avec un nouvel élément.
             *
             * @param coach L'objet Coach à afficher dans la cellule.
             * @param empty Indique si la cellule doit être vide.
             */
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
