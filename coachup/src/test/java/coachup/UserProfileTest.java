package coachup;

import coachup.facade.UserFacade;
import coachup.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserProfileTest {

    User user = new User();

    public UserProfileTest(){
        user.setNom("userProfileTest");
        user.setEmail("usernameProfileTest");
        user.setRole("student");
        user.setMotDePasse("passwordTP");
    }

    @Test
    public void testUserProfile() throws SQLException, ClassNotFoundException {
        int id = UserFacade.getInstance().addUser(user);
        user = UserFacade.getInstance().getUserById(id);
        user.setMotDePasse("nvmdp");
        user.setNom("nvnom");
        UserFacade.getInstance().updateUser(user);
        user = UserFacade.getInstance().getUserById(id);
        Assertions.assertEquals("nvmdp", UserFacade.getInstance().getUserById(id).getMotDePasse());
        Assertions.assertEquals("nvnom", UserFacade.getInstance().getUserById(id).getNom());
        UserFacade.getInstance().deleteUser(id);
    }



}
