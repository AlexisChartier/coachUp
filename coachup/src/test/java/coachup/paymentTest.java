package coachup;

import coachup.dao.SeanceDAO;
import coachup.model.Seance;
import org.junit.jupiter.api.Test;
import coachup.facade.SeanceFacade;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class paymentTest {


    Seance seance = new Seance();

    public paymentTest(){
        Date date = new java.sql.Date(2024,1,6);
        seance.setDate(date);
        seance.setIdCoach(23);
        seance.setIdUser(1);
        seance.setIdCategorie(1);
        seance.setStatutPaiement("en attente");
    }

    @Test
    public void paymentTest() throws SQLException, ClassNotFoundException {
        int id = SeanceFacade.getInstance().addSeance(seance);
        assertEquals(id,SeanceFacade.getInstance().getSeanceById(id).getIdSeance());
        seance = SeanceFacade.getInstance().getSeanceById(id);
        SeanceFacade.getInstance().paySeance(seance);
        assertEquals("Payé", SeanceFacade.getInstance().getSeanceById(id).getStatutPaiement());
        SeanceFacade.getInstance().deleteSeance(seance.getIdSeance());
    }
}
