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

public class PendingPaymentsController {

    private MainApp mainApp;

    @FXML
    private ListView<Seance> seanceListView;

    @FXML
    private Button detailButton;

    @FXML
    public void handleDetailButton() throws SQLException, ClassNotFoundException {
        Seance selectedSeance = seanceListView.getSelectionModel().getSelectedItem();
        SeanceFacade.getInstance().setManagedSeance(selectedSeance);
        if (selectedSeance != null) {
            mainApp.showPaymentPage();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    // Méthode pour initialiser la liste des séances à payer
    @FXML
    public void initialize() {
        try {
            SeanceFacade seanceFacade = SeanceFacade.getInstance();
            List<Seance> pendingSeances = seanceFacade.getPendingSeances(UserFacade.getInstance().getCurrentUser().getIdUtilisateur());
            ObservableList<Seance> seancesObservableList = FXCollections.observableArrayList(pendingSeances);
            seanceListView.setItems(seancesObservableList);
            seanceListView.setCellFactory(new HistSeanceCellFactory());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showHubPayment();
    }
}
