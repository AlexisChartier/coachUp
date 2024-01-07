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

public class HistSeanceController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private ListView<Seance> histSeanceListView;

    @FXML
    public void handleDetailButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Seance selectedSeance = histSeanceListView.getSelectionModel().getSelectedItem();
        SeanceFacade.getInstance().setManagedSeance(selectedSeance);
        mainApp.showDetailSeance();
    }

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

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
