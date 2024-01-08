package coachup.controller;

import coachup.model.Categorie;
import coachup.model.Coach;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.sql.SQLException;

public class ListCoachController {
    @FXML
    public ComboBox<Categorie> categorieComboBox;

    @FXML
    public ComboBox<String> noteComboBox;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ListView<Coach> coachListView;

    @FXML
    public void initialize() {
        // Logique pour initialiser les ComboBox en récupérant les données nécessaires depuis la base de données
        // Utilisez les façades appropriées (CoachFacade, CategorieFacade, etc.)
    }
}
