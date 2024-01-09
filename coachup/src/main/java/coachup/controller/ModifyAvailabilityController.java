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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la page de gestion des créneaux du coach
 */
public class ModifyAvailabilityController implements Initializable {

    private MainApp mainApp = new MainApp();

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Creneau_dispo currentCreneauDispo;

    /**
     * Définit le créneau actuel pour la modification.
     *
     * @param currentCreneauDispo Le créneau à modifier.
     */
    public void setCurrentCreneauDispo(Creneau_dispo currentCreneauDispo) {
        this.currentCreneauDispo = currentCreneauDispo;
    }

    @FXML
    private TextField startTimePicker;

    @FXML
    private TextField endTimePicker;

    @FXML
    private ListView<Creneau_dispo> creneauListView;

    /**
     * Définit les créneaux à afficher dans la liste.
     *
     * @param creneaux La liste des créneaux à afficher.
     */
    public void setCreneaux(List<Creneau_dispo> creneaux) {
        ObservableList<Creneau_dispo> observableList = FXCollections.observableArrayList(creneaux);
        creneauListView.setItems(observableList);
    }

    /**
     * Gère l'action du bouton d'ajout de créneau.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void addCreneauButtonAction() throws SQLException, ClassNotFoundException {
        mainApp.openAddAvailabilityPage(creneauListView.getItems().get(0));
    }

    /**
     * Gère l'action de fermeture de la page.
     */
    @FXML
    public void close() {
        mainApp.showCalendarPage();
    }

    /**
     * Initialise la liste des créneaux avec des cellules personnalisées.
     *
     * @param url       L'URL d'initialisation.
     * @param rb        Les ressources.
     */
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
                        mainApp.openModifyCreneauDispoPage(item);
                    });

                    deleteButton.setOnAction(event -> {
                        try {
                            CreneauDispoFacade.getInstance().deleteCreneauDispo(item.getCreneauDispoId());
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
}
