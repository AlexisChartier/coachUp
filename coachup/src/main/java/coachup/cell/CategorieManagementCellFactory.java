package coachup.cell;

import coachup.model.Categorie;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Cette classe implémente l'interface JavaFX Callback pour personnaliser l'affichage des éléments
 * de la ListView pour les objets de type Categorie utilisés dans la gestion des catégories.
 */
public class CategorieManagementCellFactory implements Callback<ListView<Categorie>, ListCell<Categorie>> {

    /**
     * Appelé lorsqu'une cellule de la ListView doit être créée ou mise à jour.
     *
     * @param param La ListView à laquelle cette cellule appartient.
     * @return Une instance de ListCell pour les objets Categorie avec gestion spécifique.
     */
    @Override
    public ListCell<Categorie> call(ListView<Categorie> param) {
        return new ListCell<Categorie>() {

            /**
             * Appelé lorsqu'une cellule doit être mise à jour avec un nouvel élément.
             *
             * @param item L'objet Categorie à afficher dans la cellule.
             * @param empty Indique si la cellule doit être vide.
             */
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
