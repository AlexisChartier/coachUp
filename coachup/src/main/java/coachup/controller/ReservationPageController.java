package coachup.controller;

import coachup.MainApp;
import coachup.cell.CoachSearchCellFactory;
import coachup.cell.CreneauDispoCellFactory;
import coachup.facade.CreneauDispoFacade;
import coachup.facade.UserFacade;
import coachup.model.Coach;
import coachup.model.Creneau_dispo;
import coachup.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReservationPageController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    public ListView<Creneau_dispo> creneauListView;

    @FXML
    public void handleReserverButton(ActionEvent actionEvent) {
        Creneau_dispo selectedCreneau = creneauListView.getSelectionModel().getSelectedItem();
        if (selectedCreneau != null) {
            try {
                // Logique pour réserver le créneau sélectionné
                CreneauDispoFacade.getInstance().reserverCreneau(selectedCreneau, UserFacade.getInstance().getCurrentUser().getIdUtilisateur(), UserFacade.getInstance().getSearchedCategory().getIdcategorie());
                showSuccessPopup("Réservation réussie", "Le créneau a été réservé avec succès.");
            } catch (SQLException | ClassNotFoundException e) {
                showErrorPopup("Erreur de réservation", "Une erreur est survenue lors de la réservation du créneau.");
            }
        }
    }

    // Méthode pour initialiser la liste des créneaux disponibles pour un jour spécifique
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        try {
            // Logique pour récupérer les créneaux disponibles pour la date spécifiée
            Date date = UserFacade.getInstance().getSearchedDate();
            List<Creneau_dispo> creneauxDispos = CreneauDispoFacade.getInstance().getCreneauByDayAndCoachId(date.getYear(), date.getMonth(), date.getDay(), UserFacade.getInstance().getReserveCoach().getIdUtilisateur());
            ObservableList<Creneau_dispo> creneauDispos = FXCollections.observableArrayList(creneauxDispos);
            creneauListView.setItems(creneauDispos);
            creneauListView.setCellFactory(new CreneauDispoCellFactory());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void showSuccessPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showRechercheCoach();
    }
}
