package coachup.cell;

import coachup.model.Creneau_dispo;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Cette classe implémente l'interface JavaFX Callback pour personnaliser l'affichage des éléments
 * de la ListView pour les objets de type Creneau_dispo.
 */
public class CreneauDispoCellFactory implements Callback<ListView<Creneau_dispo>, ListCell<Creneau_dispo>> {

    /**
     * Appelé lorsqu'une cellule de la ListView doit être créée ou mise à jour.
     *
     * @param param La ListView à laquelle cette cellule appartient.
     * @return Une instance de ListCell pour les objets Creneau_dispo avec des détails spécifiques au créneau.
     */
    @Override
    public ListCell<Creneau_dispo> call(ListView<Creneau_dispo> param) {
        return new ListCell<Creneau_dispo>() {

            /**
             * Appelé lorsqu'une cellule doit être mise à jour avec un nouvel élément.
             *
             * @param creneau L'objet Creneau_dispo à afficher dans la cellule.
             * @param empty Indique si la cellule doit être vide.
             */
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
