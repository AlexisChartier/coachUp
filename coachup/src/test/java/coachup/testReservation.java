package coachup;

import coachup.facade.CreneauDispoFacade;
import coachup.model.Creneau_dispo;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.ZonedDateTime;

public class testReservation {

    private Creneau_dispo creneau_dispo = new Creneau_dispo();

    ZonedDateTime date_debut = ZonedDateTime.now();
    ZonedDateTime date_fin = ZonedDateTime.now();
    public testReservation(){
        creneau_dispo.setDateDebut(date_debut);
        creneau_dispo.setDateFin(date_fin);
        creneau_dispo.setCoachId(5);
    }

    @Test
    public void testReservationCreneau() throws SQLException, ClassNotFoundException {
        int id = CreneauDispoFacade.getInstance().addCreneauDispo(creneau_dispo);
        CreneauDispoFacade.getInstance().reserved(id);
        Assertions.assertTrue(CreneauDispoFacade.getInstance().getCreneauById(id).isReserve());
    }
}
