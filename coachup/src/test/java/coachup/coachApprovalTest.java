package coachup;

import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Coach;
import coachup.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class coachApprovalTest {

    User user = new User();

    Coach unapprovecCoach = new Coach();

    public coachApprovalTest(){
        user.setNom("ApproveTest");
        user.setEmail("Approvetest");
        user.setMotDePasse("testApprove");
        user.setRole("coach");
        unapprovecCoach.setNom(user.getNom());
        unapprovecCoach.setDiplome("Licence");
        unapprovecCoach.setMotDePasse(user.getMotDePasse());
        unapprovecCoach.setEmail(user.getEmail());
    }

    @Test
    public void coachApprobationTest() throws SQLException, ClassNotFoundException {
        int id = UserFacade.getInstance().addUser(user);
        user = UserFacade.getInstance().getUserById(id);
        unapprovecCoach.setIdUtilisateur(id);
        CoachFacade.getInstance().addCoach(unapprovecCoach);
        CoachFacade.getInstance().approveCoach(unapprovecCoach.getIdUtilisateur());
        unapprovecCoach = CoachFacade.getInstance().getCoachById(id);
        assertTrue(unapprovecCoach.getApproved());
    }
}
