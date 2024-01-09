package coachup.cell;

import coachup.facade.NotationFacade;
import coachup.model.Coach;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.sql.SQLException;

/**
 * Cette classe implémente l'interface JavaFX Callback pour personnaliser l'affichage des éléments
 * de la ListView pour les objets de type Coach lors de la recherche de coachs.
 */
public class CoachSearchCellFactory implements Callback<ListView<Coach>, ListCell<Coach>> {

    /**
     * Appelé lorsqu'une cellule de la ListView doit être créée ou mise à jour.
     *
     * @param param La ListView à laquelle cette cellule appartient.
     * @return Une instance de ListCell pour les objets Coach avec des détails spécifiques à la recherche.
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
                    // Affichage des détails du coach
                    try {
                        setText("Nom: " + coach.getNom() +
                                "\nNotation Moyenne: " + NotationFacade.getInstance().getAvgNotationByCoachId(coach.getIdUtilisateur()) +
                                "\nPrix: " + coach.getPrix());
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }
}
