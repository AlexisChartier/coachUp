package coachup.controller;

import coachup.MainApp;
import coachup.cell.HistSeanceCellFactory;
import coachup.facade.UserFacade;
import coachup.model.Seance;
import coachup.facade.SeanceFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

/**
 * Contrôleur pour la vue d'historique des séances.
 */
public class HistSeanceController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private ListView<Seance> histSeanceListView;

    /**
     * Gère le bouton pour afficher les détails d'une séance.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleDetailButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Seance selectedSeance = histSeanceListView.getSelectionModel().getSelectedItem();
        SeanceFacade.getInstance().setManagedSeance(selectedSeance);
        mainApp.showDetailSeance();
    }

    /**
     * Initialise la vue avec l'historique des séances de l'utilisateur actuel.
     */
    @FXML
    public void initialize() {
        try {
            SeanceFacade seanceFacade = SeanceFacade.getInstance();
            List<Seance> allSeances = seanceFacade.getSeancesPassedByUserId(UserFacade.getInstance().getCurrentUser().getIdUtilisateur());
            ObservableList<Seance> seancesObservableList = FXCollections.observableArrayList(allSeances);
            histSeanceListView.setItems(seancesObservableList);
            histSeanceListView.setCellFactory(new HistSeanceCellFactory());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère le bouton pour ajouter une notation à une séance.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleAddNotationButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Seance selectedSeance = histSeanceListView.getSelectionModel().getSelectedItem();
        UserFacade userFacade = UserFacade.getInstance();
        userFacade.setCoachId(selectedSeance.getIdCoach());
        mainApp.showAddNotation();
    }

    /**
     * Gère le bouton de retour.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
