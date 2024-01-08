package coachup.controller;

import coachup.MainApp;
import coachup.facade.CreneauDispoFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import coachup.model.Creneau_dispo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    private MainApp mainApp = new MainApp();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        try {
            drawCalendar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backOneMonth(ActionEvent event) throws SQLException, ClassNotFoundException {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) throws SQLException, ClassNotFoundException {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() throws SQLException, ClassNotFoundException {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<Creneau_dispo>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int finalJ = j;
                int finalI = i;
                stackPane.setOnMouseClicked(event ->{
                    int day = (finalJ + 1) + (7 * finalI) - dateOffset;
                    System.out.println(stackPane.getChildren().size());
                    if (stackPane.getChildren().size() > 2 ) {
                        mainApp.openModifyAvailabilityPage(calendarActivityMap.get(day));
                    }
                    if (stackPane.getChildren().size() <= 2){
                        mainApp.openAddAvailabilityPage(new Creneau_dispo(ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), (finalJ + 1) + (7 * finalI) - dateOffset, 0, 0, 0, 0, dateFocus.getZone()),ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), (finalJ + 1) + (7 * finalI) - dateOffset, 0, 0, 0, 0, dateFocus.getZone()),1,-1));
                    }

                });


                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<Creneau_dispo> creneau_dispo = calendarActivityMap.get(currentDate);
                        if (creneau_dispo != null) {
                            createCalendarActivity(creneau_dispo, rectangleHeight, rectangleWidth, stackPane);

                        }

                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<Creneau_dispo> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getDateDebut().toLocalTime() + "h / " + calendarActivities.get(k).getDateFin().toLocalTime() + "h");
            calendarActivityBox.getChildren().add(text);
            final int finalK = k;
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                Creneau_dispo creneau_dispo = calendarActivities.get(finalK);
                mainApp.openAddAvailabilityPage(creneau_dispo);
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<Creneau_dispo>> createCalendarMap(List<Creneau_dispo> calendarActivities) {
        Map<Integer, List<Creneau_dispo>> calendarActivityMap = new HashMap<>();

        for (Creneau_dispo activity : calendarActivities) {
        int activityDate = activity.getDateDebut().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<Creneau_dispo> OldListByDate = calendarActivityMap.get(activityDate);

                List<Creneau_dispo> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<Creneau_dispo>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) throws SQLException, ClassNotFoundException {
        List<Creneau_dispo> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 1; i < dateFocus.getMonth().maxLength(); i++) {
            List<Creneau_dispo> list_creneau_dispo = CreneauDispoFacade.getInstance().getCreneauByDay(year, month, i);
            calendarActivities.addAll(list_creneau_dispo);
        }

        return createCalendarMap(calendarActivities);
    }

    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showWelcomePageCoach();
    }
}