package coachup.controller;

import coachup.MainApp;
import coachup.facade.CreneauDispoFacade;
import javafx.fxml.Initializable;
import coachup.model.Creneau_dispo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyAvailabilityController implements Initializable {


    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Creneau_dispo currentCreneauDispo;

    public void setCurrentCreneauDispo(Creneau_dispo currentCreneauDispo) {
        this.currentCreneauDispo = currentCreneauDispo;
    }

    @FXML
    private TextField startTimePicker;

    @FXML
    private TextField endTimePicker;

    @FXML
    private ListView<Creneau_dispo> creneauListView;

    public void setCreneaux(List<Creneau_dispo> creneaux) {
        ObservableList<Creneau_dispo> observableList = FXCollections.observableArrayList(creneaux);
        creneauListView.setItems(observableList);
    }

    @FXML
    public void addCreneauButtonAction() throws SQLException, ClassNotFoundException {
        mainApp.openAddAvailabilityPage(creneauListView.getItems().get(0));
    }

    @FXML
    public void close() {
        mainApp.showCalendarPage();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

            creneauListView.setCellFactory(param -> new ListCell<Creneau_dispo>() {
                @Override
                protected void updateItem(Creneau_dispo item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        HBox hBox = new HBox(10);
                        Label label = new Label(item.getDateDebut().getHour() + "h - " + item.getDateFin().getHour() + "h");
                        Button modifyButton = new Button("Modifier");
                        Button deleteButton = new Button("Supprimer");

                        modifyButton.setOnAction(event -> {
                            //mainApp.openModifyCreneauDispoPage(item);
                        });

                        deleteButton.setOnAction(event -> {
                            try {
                                mainApp.deleteCreneauDispo(item.getCreneauDispoId());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        hBox.getChildren().addAll(label, modifyButton, deleteButton);
                        setGraphic(hBox);
                    }
                }
            });
        }


    // ... (rest of the code is unchanged)
}