package coachup;

import coachup.facade.UserFacade;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class LoginTest extends TestCase {
    public String goodemail ;
    public String goodpassword ;
    public String bademail ;
    public String badpassword ;



    public LoginTest(){
        this.goodemail = "usr1@gmail.com";
        this.goodpassword = "1234";
        this.bademail = "bademail";
        this.badpassword = "badpassword";
    }

    @Test
    public void testLogin() {
        UserFacade userFacade = UserFacade.getInstance();
        boolean goodlogin = userFacade.loginUser(this.goodemail, this.goodpassword);
        boolean badlogin = userFacade.loginUser(this.bademail, this.badpassword);
        assertFalse(badlogin);
        assertTrue(goodlogin);
    }
}