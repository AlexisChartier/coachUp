package coachup.controller;

import coachup.MainApp;
import coachup.cell.HistSeanceCellFactory;
import coachup.facade.SeanceFacade;
import coachup.facade.UserFacade;
import coachup.model.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class UpcomingSessionsController {

    private MainApp mainApp;

    @FXML
    private ListView<Seance> seanceListView;

    @FXML
    private Button detailButton;

    /**
     * Gère l'action du bouton de détail pour la séance sélectionnée.
     *
     * @throws SQLException           Si une erreur SQL survient.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée.
     */
    @FXML
    public void handleDetailButton() throws SQLException, ClassNotFoundException {
        Seance selectedSeance = seanceListView.getSelectionModel().getSelectedItem();
        SeanceFacade.getInstance().setManagedSeance(selectedSeance);
        if (selectedSeance != null) {
            mainApp.showUpcomingDetail();
        }
    }

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initialise le contrôleur, appelé automatiquement après le chargement du fichier FXML.
     * Initialise la liste des séances à venir.
     */
    @FXML
    public void initialize() {
        try {
            SeanceFacade seanceFacade = SeanceFacade.getInstance();
            List<Seance> upcomingSeances = seanceFacade.getUpcomingSeances(UserFacade.getInstance().getCurrentUser().getIdUtilisateur());
            ObservableList<Seance> seancesObservableList = FXCollections.observableArrayList(upcomingSeances);
            seanceListView.setItems(seancesObservableList);
            seanceListView.setCellFactory(new HistSeanceCellFactory());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton de retour vers la page du hub de paiement.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showHubPayment();
    }
}
