package coachup;

import coachup.facade.UserFacade;
import coachup.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserManagementTest {

    User user = new User();

    public UserManagementTest(){
        user.setNom("userTestCase");
        user.setEmail("testEmail");
        user.setMotDePasse("testMDP");
        user.setRole("student");
    }

    @Test
    public void registerTest() throws Exception {
        int id = UserFacade.getInstance().addUser(user);
        assertTrue(UserFacade.getInstance().loginUser(user.getEmail(), user.getMotDePasse()));
        assertEquals("student", UserFacade.getInstance().getCurrentUser().getRole());
        assertTrue(UserFacade.getInstance().deleteUser(id));
    }

}
