package coachup.controller;

import coachup.MainApp;
import coachup.cell.CategorieCellFactory;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.model.Categorie;
import coachup.model.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class CoachDetailController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Coach coach;


    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public ListView<Categorie> categoriesListView;
    @FXML
    public TextField diplomaField;
    @FXML
    public Label currentNameLabel;
    @FXML
    public Label currentEmailLabel;

    @FXML
    public ListView<Categorie> currentCategoriesListView;

    @FXML
    public Label currentDiplomaField;

    @FXML
    public void handleSaveButton() {
        // Logique pour sauvegarder les modifications
    }

    @FXML
    public void handleReturnButton() {
        // Logique pour retourner à la page précédente
    }

    @FXML
    public void handleDeleteButton() {
        // Logique pour supprimer le compte du coach
    }

    @FXML
    public void initialize() {
        try {
            CoachFacade coachFacade = CoachFacade.getInstance();
            coach = CoachFacade.getInstance().getManagedCoach();
            List<Categorie> allCategories = coachFacade.getCategoriesByCoachID(coach.getIdUtilisateur());

            ObservableList<Categorie> categoriesObservableList = FXCollections.observableArrayList(allCategories);
            categoriesListView.setItems(categoriesObservableList);
            categoriesListView.setCellFactory(new CategorieCellFactory());
            currentCategoriesListView.setItems(categoriesObservableList);
            currentCategoriesListView.setCellFactory(new CategorieCellFactory());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
