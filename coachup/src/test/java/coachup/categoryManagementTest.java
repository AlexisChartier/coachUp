package coachup;

import coachup.facade.CategorieFacade;
import coachup.model.Categorie;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class categoryManagementTest {


    Categorie categorie = new Categorie();
    public categoryManagementTest(){
        categorie.setNom("categorieTest");
        categorie.setDescription("testCategorie");
    }

    @Test
    public void CRUDCategorieTest() throws SQLException, ClassNotFoundException {
        int id = CategorieFacade.getInstance().addCategory(categorie);
        assertNotNull(CategorieFacade.getInstance().getCategoryById(id));
        CategorieFacade.getInstance().deleteCategory(id);
        assertNull(CategorieFacade.getInstance().getCategoryById(id));
    }
}
