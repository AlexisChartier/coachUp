package coachup;

import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Coach;
import coachup.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class registerCoachTest  {

    public User user = new User();

    public Coach coach = new Coach();


    public registerCoachTest(){
        user.setNom("CoachTest");
        user.setRole("coach");
        user.setEmail("coachTest");
        user.setMotDePasse("coachTest");
        coach.setNom(user.getNom());
        coach.setEmail(user.getEmail());
        coach.setMotDePasse(user.getMotDePasse());
        coach.setDiplome("Licence");
        Integer[] array = {1};
        coach.setCategories(array);
    }

    @Test
    public void registerCoachTest() throws SQLException, ClassNotFoundException {
        int id = UserFacade.getInstance().addUser(user);
        user = UserFacade.getInstance().getUserById(id);
        coach.setIdUtilisateur(user.getIdUtilisateur());
        CoachFacade.getInstance().addCoach(coach);
        assertTrue(UserFacade.getInstance().loginUser(user.getEmail(), user.getMotDePasse()));
        assertEquals(coach.getIdUtilisateur(),CoachFacade.getInstance().getCoachById(user.getIdUtilisateur()).getIdUtilisateur());
        assertEquals("coach", UserFacade.getInstance().getCurrentUser().getRole());
    }
}
