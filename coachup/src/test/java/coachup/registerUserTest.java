package coachup;

import coachup.facade.UserFacade;
import coachup.model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class registerUserTest {


    public User user = new User();
    public registerUserTest(){
        user.setNom("userTestCase");
        user.setEmail("testEmail");
        user.setMotDePasse("testMDP");
        user.setRole("student");
    }

    @Test
    public void registerTest() throws SQLException, ClassNotFoundException, IOException {
        UserFacade.getInstance().addUser(user);
        assertTrue(UserFacade.getInstance().loginUser(user.getEmail(), user.getMotDePasse()));
        assertEquals("student", UserFacade.getInstance().getCurrentUser().getRole());
    }
}
