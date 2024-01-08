package coachup;

import coachup.model.Seance;
import org.junit.jupiter.api.Test;
import coachup.facade.SeanceFacade;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class paymentTest {


    Seance seance = new Seance();

    public paymentTest(){
        seance.setIdSeance(-1);
        seance.setDate(new Date());
        seance.setIdCoach(-1);
        seance.setIdUser(-1);
        seance.setIdCategorie(1);
        seance.setStatutPaiement("en attente");
    }

    @Test
    public void paymentTest() throws SQLException, ClassNotFoundException {
    assertTrue(SeanceFacade.getInstance().addSeance(seance));
    SeanceFacade.getInstance().paySeance(seance);
    assertEquals("payé", SeanceFacade.getInstance().getSeanceById(seance.getIdSeance()).getStatutPaiement());
    SeanceFacade.getInstance().deleteSeance(seance.getIdSeance());
    }
}
