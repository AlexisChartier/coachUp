package coachup.controller;

import coachup.MainApp;
import coachup.cell.CoachCellFactory;
import coachup.cell.CoachSearchCellFactory;
import coachup.facade.*;
import coachup.model.Categorie;
import coachup.model.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ListCoachController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public ComboBox<Categorie> categorieComboBox;

    @FXML
    public ComboBox<String> noteComboBox;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ListView<Coach> coachListView;

    private Coach selectedCoach;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
// Initialise la ListView avec les coachs en attente d'approbation
        List<Coach> coaches = UserFacade.getInstance().getCoachSearch();
        try {
            coaches = UserFacade.getInstance().getCoachSearch();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Coach> coachObservableList = FXCollections.observableArrayList(coaches);
        coachListView.setItems(coachObservableList);
        coachListView.setCellFactory(new CoachSearchCellFactory());
        coachListView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->
                selectedCoach = newValue  );
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }

    public void refreshList(){
        try {
            // Récupérer l'id de la catégorie sélectionnée
            int idCategorie = CategorieFacade.getInstance().getCategoryByNom(String.valueOf(categorieComboBox.getSelectionModel().getSelectedItem())).getIdcategorie();
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
            for(Coach coach : coachesAvailable) {
                if (CreneauDispoFacade.getInstance().getCreneauByDayAndCoachId(selectedDate.getYear(), selectedDate.getMonthValue(), selectedDate.getDayOfMonth(), coach.getIdUtilisateur()).isEmpty()) {
                    coachesAvailable.remove(coach);
                }
            }
            if (coachesAvailable.isEmpty() || selectedMinNote == -1 || selectedDate == null) {
                // Afficher un message indiquant qu'aucun coach n'est disponible
                showNoResultsAlert();
            } else {
                // Afficher la liste des coachs disponibles
                UserFacade.getInstance().setCoachSearch(coachesAvailable);
                UserFacade.getInstance().setSearchedCategory(CategorieFacade.getInstance().getCategoryById(idCategorie));
                UserFacade.getInstance().setSearchedDate(Date.valueOf(selectedDate));
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
    public void handleSearchButton(ActionEvent actionEvent) {
        refreshList();
    }
    @FXML
    public void handleReserveButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserFacade.getInstance().setReserveCoach(selectedCoach);
        mainApp.showReservationPage();
    }
}
