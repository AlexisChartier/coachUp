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

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public ListView<Creneau_dispo> creneauListView;

    /**
     * Gère l'action du bouton de réservation d'un créneau.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
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

    /**
     * Méthode pour initialiser la liste des créneaux disponibles pour un jour spécifique.
     *
     * @throws SQLException           En cas d'erreur SQL.
     * @throws ClassNotFoundException Si la classe spécifiée n'a pas pu être trouvée.
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        try {
            // Logique pour récupérer les créneaux disponibles pour la date spécifiée
            Date date = UserFacade.getInstance().getSearchedDate();
            List<Creneau_dispo> creneauxDispos = CreneauDispoFacade.getInstance().getCreneauByDayAndCoachId(date.getYear(), date.getMonth(), date.getDay(), UserFacade.getInstance().getReserveCoach().getIdUtilisateur());
            ObservableList<Creneau_dispo> creneauDisposObservableList = FXCollections.observableArrayList(creneauxDispos);
            creneauListView.setItems(creneauDisposObservableList);
            creneauListView.setCellFactory(new CreneauDispoCellFactory());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Affiche une boîte de dialogue d'information en cas de succès.
     *
     * @param title   Le titre de la boîte de dialogue.
     * @param message Le message à afficher.
     */
    public void showSuccessPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Affiche une boîte de dialogue d'erreur en cas d'échec.
     *
     * @param title   Le titre de la boîte de dialogue.
     * @param message Le message à afficher.
     */
    public void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Gère l'action du bouton de retour vers la page de recherche des coachs.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showRechercheCoach();
    }
}
