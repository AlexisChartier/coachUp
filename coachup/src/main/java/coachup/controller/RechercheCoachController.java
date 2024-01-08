package coachup.controller;

import coachup.MainApp;
import coachup.facade.*;
import coachup.model.Categorie;
import coachup.model.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RechercheCoachController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private ComboBox<String> categorieComboBox;

    private int idCategorie;

    @FXML
    private ComboBox<String> noteComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    public void initialize() {
        try {
            // Initialiser la liste des catégories
            ObservableList<Categorie> categories = FXCollections.observableArrayList(CategorieFacade.getInstance().getAllCategories());
            ObservableList<String> categoryNames = FXCollections.observableArrayList(
                    CategorieFacade.getInstance().getAllCategories()
                            .stream()
                            .map(Categorie::getNom) // Utilisation de la méthode getNom pour extraire le nom de chaque catégorie
                            .collect(Collectors.toList())
            );
            categorieComboBox.setItems(categoryNames);

            // Initialiser la liste des notes
            ObservableList<String> notes = FXCollections.observableArrayList("0 ou +", "1 ou +", "2 ou +", "3 ou +", "4 ou +", "5");
            noteComboBox.setItems(notes);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRechercheButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            // Récupérer l'id de la catégorie sélectionnée
            idCategorie = CategorieFacade.getInstance().getCategoryByNom(categorieComboBox.getSelectionModel().getSelectedItem()).getIdcategorie();
            float selectedMinNote = -1;
            // Récupérer la note minimum
            if(noteComboBox.getSelectionModel().getSelectedItem().equals("0 ou +")){
                selectedMinNote = 0;
            }
            else if(noteComboBox.getSelectionModel().getSelectedItem().equals("1 ou +")){
                selectedMinNote = 1;
            }
            else if(noteComboBox.getSelectionModel().getSelectedItem().equals("2 ou +")){
                selectedMinNote = 2;
            }
            else if(noteComboBox.getSelectionModel().getSelectedItem().equals("3 ou +")){
                selectedMinNote = 3;
            }
            else if(noteComboBox.getSelectionModel().getSelectedItem().equals("4 ou +")){
                selectedMinNote = 4;
            }
            else if(noteComboBox.getSelectionModel().getSelectedItem().equals("5 ou +")){
                selectedMinNote = 5;
            }
            // Récupérer la date sélectionnée
            LocalDate selectedDate = datePicker.getValue();

            // Effectuer la première recherche pour récupérer les coachs de la catégorie
            List<Coach> coachesAvailable = CoachFacade.getInstance().getCoachesByCatId(idCategorie);

            // Effectuer la deuxième recherche pour récupérer les coachs avec une note supérieure à la note minimum
            for(Coach coach : coachesAvailable){
                if(NotationFacade.getInstance().getAvgNotationByCoachId(coach.getIdUtilisateur()) < selectedMinNote){
                    coachesAvailable.remove(coach);
                }
            }
            // Effectuer la troisième recherche pour vérifier la disponibilité des coachs à la date sélectionnée
            for(Coach coach : coachesAvailable){

            }

            /*List<Coach> availableCoaches = new ArrayList<>();
            for (Coach coach : coachesByMinNote) {
                if (CoachFacade.getInstance().isCoachAvailable(coach.getIdCoach(), selectedDate)) {
                    availableCoaches.add(coach);
                }
            }*/

            if (coachesAvailable.isEmpty() || selectedMinNote == -1 || selectedDate == null) {
                // Afficher un message indiquant qu'aucun coach n'est disponible
                showNoResultsAlert();
            } else {
                // Afficher la liste des coachs disponibles
                mainApp.showListCoachPage();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showNoResultsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aucun résultat");
        alert.setHeaderText(null);
        alert.setContentText("Aucun coach disponible.");

        ButtonType retourButton = new ButtonType("Retour");
        alert.getButtonTypes().setAll(retourButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == retourButton) {
            alert.close();
        }
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
