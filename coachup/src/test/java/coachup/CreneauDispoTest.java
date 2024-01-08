package coachup;

import coachup.facade.CreneauDispoFacade;
import org.junit.jupiter.api.Test;
import coachup.model.Creneau_dispo;

import java.sql.SQLException;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class CreneauDispoTest {
    ZonedDateTime date_debut = ZonedDateTime.now();
    ZonedDateTime date_fin = ZonedDateTime.now();
    Integer CoachId = 1;
    Integer Creneau_dispo_id = -1;
    Creneau_dispo creneau = new Creneau_dispo(date_debut,date_fin,CoachId,Creneau_dispo_id);
    public CreneauDispoTest(){

    }

    @Test
    public void testAddCreneau() throws SQLException, ClassNotFoundException {
        creneau.setCreneauDispoId(CreneauDispoFacade.getInstance().addCreneauDispo(creneau));
        assertTrue(CreneauDispoFacade.getInstance().deleteCreneauDispo(creneau.getCreneauDispoId()));
    }
}
