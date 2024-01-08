package coachup.controller;


import coachup.facade.CreneauDispoFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import coachup.model.Creneau_dispo;
import coachup.MainApp;
import javafx.scene.control.Label;
import coachup.facade.UserFacade;


import java.net.URL;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

public class AddAvailabilityController implements Initializable {

    private Creneau_dispo creneau_dispo;
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField startTimePicker;

    @FXML
    private TextField endTimePicker;

    @FXML
    private Label dateLabel;

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCreneau_dispo(Creneau_dispo creneau_dispo) {
        this.creneau_dispo = creneau_dispo;
        updateDateLabel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cancel() {
        mainApp.showCalendarPage();
    }

    @FXML
    private void submitAvailability() throws SQLException, ClassNotFoundException {
        String startTime = startTimePicker.getText();
        ZonedDateTime newStartDate = creneau_dispo.getDateDebut().withHour(Integer.parseInt(startTime.substring(0,2)));
        String endTime = endTimePicker.getText();
        ZonedDateTime newEndDate = creneau_dispo.getDateFin().withHour(Integer.parseInt(endTime.substring(0,2)));
        UserFacade userFacade = UserFacade.getInstance();
        int coachId = userFacade.getCurrentUser().getIdUtilisateur();

        Creneau_dispo creneau_dispo = new Creneau_dispo(newStartDate,newEndDate,coachId,-1);
        CreneauDispoFacade.getInstance().addCreneauDispo(creneau_dispo);
    }

    public void updateDateLabel() {
        String currentDate = creneau_dispo.getDateDebut().getDayOfWeek().toString() + " " + creneau_dispo.getDateDebut().getDayOfMonth() + " " + creneau_dispo.getDateDebut().getMonth().toString() + " " + creneau_dispo.getDateDebut().getYear();
        dateLabel.setText(currentDate);
    }
}