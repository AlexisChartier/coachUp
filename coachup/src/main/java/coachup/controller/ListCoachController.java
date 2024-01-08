package coachup.controller;

import coachup.MainApp;
import coachup.cell.CoachCellFactory;
import coachup.cell.CoachSearchCellFactory;
import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import coachup.model.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

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

    public void loadData(){

    }

    public void refreshList(){

    }

    @FXML
    public void handleSearchButton(ActionEvent actionEvent) {
        refreshList();
    }
}
