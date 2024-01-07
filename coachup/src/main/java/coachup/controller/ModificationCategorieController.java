package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ModificationCategorieController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    private Categorie selectedCategorie;

    public void setSelectedCategorie(Categorie categorie) {
        this.selectedCategorie = categorie;
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        selectedCategorie = CategorieFacade.getInstance().getManagedCategorie();
        populateFields();
    }

    private void populateFields() {
        if (selectedCategorie != null) {
            nomField.setText(selectedCategorie.getNom());
            descriptionField.setText(selectedCategorie.getDescription());
        }
    }

    @FXML
    public void handleModifierButton() {
        if (selectedCategorie != null) {
            try {
                Categorie categorie = new Categorie();
                categorie.setIdcategorie(selectedCategorie.getIdcategorie());
                categorie.setNom(nomField.getText());
                categorie.setDescription(descriptionField.getText());
                CategorieFacade.getInstance().updateCategory(categorie);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleReturnButton() throws SQLException, ClassNotFoundException {
        mainApp.showCategoriesList(UserFacade.getInstance().getCurrentUser());
    }
}
