package coachup;
import coachup.facade.NotationFacade;
import org.junit.jupiter.api.Test;
import coachup.model.Notation;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotationTest {

    Notation goodnote = new Notation();


        public NotationTest(){
            goodnote.setNote(5);
            goodnote.setComment("test");
            goodnote.setCoachId(1);
            goodnote.setUserId(1);
        }

        @Test
        public void testAddNotation() throws SQLException, ClassNotFoundException {
            goodnote.setNotationId(NotationFacade.getInstance().addNotation(goodnote));
            assertTrue(NotationFacade.getInstance().deleteNotation(goodnote.getNotationId()));
        }
}
