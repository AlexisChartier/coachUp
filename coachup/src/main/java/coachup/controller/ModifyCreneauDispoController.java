package coachup.controller;

import coachup.MainApp;
import coachup.facade.CreneauDispoFacade;
import coachup.model.Creneau_dispo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class ModifyCreneauDispoController{

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    private Creneau_dispo currentCreneauDispo;

    public void setCurrentCreneauDispo(Creneau_dispo currentCreneauDispo) {
        this.currentCreneauDispo = currentCreneauDispo;
    }

    @FXML
    private TextField startTimePicker ;

    @FXML
    private TextField endTimePicker;

    public void setup() {
        startTimePicker.setText(String.valueOf(currentCreneauDispo.getDateDebut().getHour()));
        endTimePicker.setText(String.valueOf(currentCreneauDispo.getDateFin().getHour()));
    }
    @FXML
    public void modifyCreneauButtonAction() throws SQLException, ClassNotFoundException {
        String startTime = startTimePicker.getText();
        String endTime = endTimePicker.getText();
        ZonedDateTime newStartDate = currentCreneauDispo.getDateDebut().withHour(Integer.parseInt(startTime.substring(0,2)));
        ZonedDateTime newEndDate = currentCreneauDispo.getDateFin().withHour(Integer.parseInt(endTime.substring(0,2)));
        currentCreneauDispo.setDateDebut(newStartDate);
        currentCreneauDispo.setDateFin(newEndDate);
        if (CreneauDispoFacade.getInstance().modifyCreneauDispo(currentCreneauDispo)){
            System.out.println("Creneau modified");
        }
        else {
            System.out.println("Creneau modification failed");
        }
    }
    @FXML
    public void close() {
        mainApp.showCalendarPage();
    }
}
