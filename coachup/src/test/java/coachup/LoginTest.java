package coachup;

import coachup.facade.UserFacade;
import junit.framework.TestCase;


public class LoginTest extends TestCase{

    public LoginTest(){}
    public String goodemail ;
    public String goodpassword ;
    public String bademail ;
    public String badpassword ;
    public LoginTest(String goodemail, String goodpassword, String bademail, String badpassword){
        this.goodemail = goodemail;
        this.goodpassword = goodpassword;
        this.bademail = bademail;
        this.badpassword = badpassword;
    }

    public void testLogin() throws Exception{
        UserFacade userFacade = UserFacade.getInstance();
        boolean goodlogin = userFacade.loginUser(this.goodemail, this.goodpassword);
        boolean badlogin = userFacade.loginUser(this.bademail, this.badpassword);
        assertFalse(badlogin);
        assertTrue(goodlogin);
    }
}