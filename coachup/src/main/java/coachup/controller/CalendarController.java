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

/**
 * Contrôleur pour la vue du calendrier.
 */
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

    /**
     * Configure l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initialise le contrôleur après que son élément racine a été complètement traité.
     *
     * @param url            L'emplacement initial pour la racine de l'objet.
     * @param resourceBundle Les ressources locales utilisées pour localiser l'objet racine.
     */
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

    /**
     * Gère l'événement de retour d'un mois dans le calendrier.
     *
     * @param event L'événement de l'action du bouton.
     * @throws SQLException            En cas d'erreur SQL lors du retour d'un mois dans le calendrier.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors du retour d'un mois dans le calendrier.
     */
    @FXML
    void backOneMonth(ActionEvent event) throws SQLException, ClassNotFoundException {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /**
     * Gère l'événement d'avancement d'un mois dans le calendrier.
     *
     * @param event L'événement de l'action du bouton.
     * @throws SQLException            En cas d'erreur SQL lors de l'avancement d'un mois dans le calendrier.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors de l'avancement d'un mois dans le calendrier.
     */
    @FXML
    void forwardOneMonth(ActionEvent event) throws SQLException, ClassNotFoundException {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /**
     * Dessine le calendrier en fonction de la date en cours.
     *
     * @throws SQLException            En cas d'erreur SQL lors du dessin du calendrier.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors du dessin du calendrier.
     */
    private void drawCalendar() throws SQLException, ClassNotFoundException {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // Liste des activités pour un mois donné
        Map<Integer, List<Creneau_dispo>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Vérification pour l'année bissextile
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
                stackPane.setOnMouseClicked(event -> {
                    int day = (finalJ + 1) + (7 * finalI) - dateOffset;
                    System.out.println(stackPane.getChildren().size());
                    if (stackPane.getChildren().size() > 2) {
                        mainApp.openModifyAvailabilityPage(calendarActivityMap.get(day));
                    }
                    if (stackPane.getChildren().size() <= 2) {
                        mainApp.openAddAvailabilityPage(new Creneau_dispo(ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), (finalJ + 1) + (7 * finalI) - dateOffset, 0, 0, 0, 0, dateFocus.getZone()), ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), (finalJ + 1) + (7 * finalI) - dateOffset, 0, 0, 0, 0, dateFocus.getZone()), 1, -1));
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

    /**
     * Crée une activité de calendrier avec la liste des créneaux disponibles.
     *
     * @param calendarActivities La liste des créneaux disponibles pour la journée.
     * @param rectangleHeight    La hauteur du rectangle de l'activité.
     * @param rectangleWidth     La largeur du rectangle de l'activité.
     * @param stackPane          Le conteneur de la journée dans le calendrier.
     */
    private void createCalendarActivity(List<Creneau_dispo> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    // En cas de clic sur ..., afficher toutes les activités pour la date donnée
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getDateDebut().toLocalTime() + "h / " + calendarActivities.get(k).getDateFin().toLocalTime() + "h");
            calendarActivityBox.getChildren().add(text);
            final int finalK = k;
            text.setOnMouseClicked(mouseEvent -> {
                // En cas de clic sur le texte
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

    /**
     * Crée une carte des activités du calendrier pour un mois donné.
     *
     * @param calendarActivities Les activités du calendrier pour le mois donné.
     * @return La carte des activités du calendrier.
     */
    private Map<Integer, List<Creneau_dispo>> createCalendarMap(List<Creneau_dispo> calendarActivities) {
        Map<Integer, List<Creneau_dispo>> calendarActivityMap = new HashMap<>();

        for (Creneau_dispo activity : calendarActivities) {
            int activityDate = activity.getDateDebut().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<Creneau_dispo> oldListByDate = calendarActivityMap.get(activityDate);

                List<Creneau_dispo> newList = new ArrayList<>(oldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    /**
     * Récupère les activités du calendrier pour le mois donné.
     *
     * @param dateFocus La date en cours du calendrier.
     * @return La liste des créneaux disponibles pour le mois donné.
     * @throws SQLException            En cas d'erreur SQL lors de la récupération des créneaux disponibles.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors de la récupération des créneaux disponibles.
     */
    private Map<Integer, List<Creneau_dispo>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) throws SQLException, ClassNotFoundException {
        List<Creneau_dispo> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 1; i < dateFocus.getMonth().maxLength(); i++) {
            List<Creneau_dispo> listCreneauDispo = CreneauDispoFacade.getInstance().getCreneauByDay(year, month, i);
            calendarActivities.addAll(listCreneauDispo);
        }

        return createCalendarMap(calendarActivities);
    }

    /**
     * Gère l'événement du bouton de retour.
     *
     * @param actionEvent L'événement de l'action du bouton.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showWelcomePageCoach();
    }
}
