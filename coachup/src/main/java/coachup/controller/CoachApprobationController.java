package coachup.controller;

import coachup.MainApp;
import coachup.cell.CoachCellFactory;
import coachup.facade.CoachFacade;
import coachup.model.Coach;
import coachup.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la vue d'approbation des coachs.
 */
public class CoachApprobationController implements Initializable {

    private User user;

    /**
     * Définit l'utilisateur associé à ce contrôleur.
     *
     * @param user L'utilisateur.
     */
    public void setUser(User user) {
        this.user = user;
    }

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
    public ListView<Coach> coachListView;

    private Coach selectedCoach;

    /**
     * Récupère le coach sélectionné.
     *
     * @return Le coach sélectionné.
     */
    public Coach getSelectedCoach() {
        return selectedCoach;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialise la ListView avec les coachs en attente d'approbation
        List<Coach> coaches = null;
        try {
            coaches = CoachFacade.getInstance().getUnapprovedCoaches();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Coach> coachObservableList = FXCollections.observableArrayList(coaches);
        coachListView.setItems(coachObservableList);
        coachListView.setCellFactory(new CoachCellFactory());
        coachListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                selectedCoach = newValue);
    }

    @FXML
    private void handleItemSelected() {
        Coach selectedCoach = coachListView.getSelectionModel().getSelectedItem();
        if (selectedCoach != null) {
            // Logique pour gérer la sélection de l'utilisateur
            System.out.println("Coach sélectionné : " + selectedCoach.getNom());
        }
    }

    @FXML
    public void handleApproveButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selectedCoach != null) {
            CoachFacade.getInstance().approveCoach(selectedCoach.getIdUtilisateur());
            List<Coach> coaches = CoachFacade.getInstance().getUnapprovedCoaches();

            ObservableList<Coach> observableList = FXCollections.observableArrayList(coaches);
            coachListView.setItems(observableList);

            selectedCoach = null;
        }
    }

    @FXML
    public void handleRejectButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        CoachFacade.getInstance().denyCoach(selectedCoach.getIdUtilisateur());

        List<Coach> coaches = CoachFacade.getInstance().getUnapprovedCoaches();

        ObservableList<Coach> observableList = FXCollections.observableArrayList(coaches);
        coachListView.setItems(observableList);

        selectedCoach = null;
    }

    @FXML
    public void handleReturnButton(ActionEvent event) {
        mainApp.showWelcomePageAdmin(user);
    }

    @FXML
    public void handleDetailButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CoachFacade.getInstance().setManagedCoach(selectedCoach);
        mainApp.showDetailCoach();
    }
}
