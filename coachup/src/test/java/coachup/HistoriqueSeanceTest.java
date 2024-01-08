package coachup;

import coachup.facade.SeanceFacade;
import coachup.facade.UserFacade;
import org.junit.jupiter.api.Test;
import coachup.model.User;
import coachup.model.Seance;


import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoriqueSeanceTest {

    User user = new User();

    Seance seance = new Seance();

    Date anciennedate = new Date(0);
    public HistoriqueSeanceTest(){
        user.setNom("userTestCase");
        user.setEmail("testEmail");
        user.setMotDePasse("testMDP");
        user.setRole("student");
        seance.setIdSeance(-1);
        seance.setDate(anciennedate);
        seance.setIdCoach(-1);
        seance.setIdUser(-1);
        seance.setIdCategorie(1);
        seance.setStatutPaiement("en attente");

    }

    @Test
    public void showHistoriqueTest() throws SQLException, ClassNotFoundException {
        UserFacade.getInstance().addUser(user);
        //assertTrue(SeanceFacade.getInstance().addSeance(seance));
        SeanceFacade.getInstance().paySeance(seance);
        assertEquals(seance,SeanceFacade.getInstance().getSeancesPassedByUserId(user.getIdUtilisateur()).get(0));
    }
}
