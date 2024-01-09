package coachup.cell;

import coachup.model.User;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Cette classe implémente l'interface JavaFX Callback pour personnaliser l'affichage des éléments
 * de la ListView pour les objets de type User.
 */
public class UserCellFactory implements Callback<ListView<User>, ListCell<User>> {

    /**
     * Appelé lorsqu'une cellule de la ListView doit être créée ou mise à jour.
     *
     * @param param La ListView à laquelle cette cellule appartient.
     * @return Une instance de ListCell pour les objets User avec des détails spécifiques à l'affichage.
     */
    @Override
    public ListCell<User> call(ListView<User> param) {
        return new ListCell<User>() {

            /**
             * Appelé lorsqu'une cellule doit être mise à jour avec un nouvel élément.
             *
             * @param item L'objet User à afficher dans la cellule.
             * @param empty Indique si la cellule doit être vide.
             */
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getNom()); // Adapter en fonction de votre classe User
                } else {
                    setText(null);
                }
            }
        };
    }
}
