package coachup;

import coachup.facade.SeanceFacade;
import coachup.facade.UserFacade;
import org.junit.jupiter.api.Test;
import coachup.model.User;
import coachup.model.Seance;


import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class HistoriqueSeanceTest {

    Seance seance = new Seance();

    Date anciennedate = new Date(0);
    public HistoriqueSeanceTest(){
        seance.setDate(anciennedate);
        seance.setIdCoach(23);
        seance.setIdUser(1);
        seance.setIdCategorie(1);
        seance.setStatutPaiement("En Attente");

    }

    @Test
    public void showHistoriqueTest() throws SQLException, ClassNotFoundException {
        int idSeance = SeanceFacade.getInstance().addSeance(seance);
        assertEquals(idSeance,SeanceFacade.getInstance().getSeanceById(idSeance).getIdSeance());
        seance = SeanceFacade.getInstance().getSeanceById(idSeance);
        SeanceFacade.getInstance().paySeance(seance);
        assertNotNull(SeanceFacade.getInstance().getSeancesPassedByUserId(1).get(0));
    }
}
