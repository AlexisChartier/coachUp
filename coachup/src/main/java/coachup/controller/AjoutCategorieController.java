package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AjoutCategorieController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    @FXML
    public void handleAjouterButton(ActionEvent actionEvent) {
        try {
            Categorie categorie = new Categorie();
            categorie.setDescription(descriptionField.getText());
            categorie.setNom(nomField.getText());
            CategorieFacade.getInstance().addCategory(categorie);
            mainApp.showCategoriesList(UserFacade.getInstance().getCurrentUser());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePageAdmin(UserFacade.getInstance().getCurrentUser());
    }
}
