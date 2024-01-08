package coachup;

import coachup.facade.UserFacade;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    public String goodemail ;
    public String goodpassword ;
    public String bademail ;
    public String badpassword ;



    public LoginTest(){
        this.goodemail = "test";
        this.goodpassword = "test";
        this.bademail = "bademail";
        this.badpassword = "badpassword";
    }

    @Test
    public void testLogin() throws SQLException, ClassNotFoundException {
        UserFacade userFacade = UserFacade.getInstance();
        boolean goodlogin = userFacade.loginUser(this.goodemail, this.goodpassword);
        boolean badlogin = userFacade.loginUser(this.bademail, this.badpassword);
        assertFalse(badlogin);
        assertTrue(goodlogin);
    }
}