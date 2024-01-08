package coachup.cell;

import coachup.facade.NotationFacade;
import coachup.model.Coach;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.sql.SQLException;

public class CoachSearchCellFactory implements Callback<ListView<Coach>, ListCell<Coach>> {

    @Override
    public ListCell<Coach> call(ListView<Coach> param) {
        return new ListCell<Coach>() {
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
