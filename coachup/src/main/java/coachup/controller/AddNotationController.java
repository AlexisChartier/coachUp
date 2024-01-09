package coachup.controller;

import coachup.MainApp;
import coachup.facade.NotationFacade;
import coachup.facade.UserFacade;
import coachup.model.Notation;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la vue d'ajout de notation pour un coach.
 */
public class AddNotationController implements Initializable {

    @FXML
    private Slider notationSlider;

    @FXML
    private TextArea commentField;

    @FXML
    private Label nomducoachLabel;

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
     * @param url L'emplacement initial pour la racine de l'objet.
     * @param rb  Les ressources locales utilisées pour localiser l'objet racine.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UserFacade userFacade = UserFacade.getInstance();
            User coach = userFacade.getUserById(userFacade.getCoachId());
            String coachName = coach.getNom();
            nomducoachLabel.setText(coachName);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gère l'action du bouton d'ajout de notation.
     *
     * @param event L'événement de l'action du bouton.
     * @throws SQLException            En cas d'erreur SQL lors de l'ajout de la notation.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors de l'ajout de la notation.
     */
    @FXML
    private void addNotationButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        float note = (float) notationSlider.getValue();
        UserFacade userFacade = UserFacade.getInstance();
        int coachId = userFacade.getCoachId();
        int userId = userFacade.getCurrentUser().getIdUtilisateur();
        Notation notation = new Notation();
        notation.setComment(commentField.getText());
        notation.setNote(note);
        notation.setCoachId(coachId);
        notation.setUserId(userId);
        if (NotationFacade.getInstance().addNotation(notation) != -1) {
            System.out.println("Notation added");
        } else {
            System.out.println("Notation failed");
        }
    }

    /**
     * Gère l'action du bouton de retour à la page d'accueil.
     *
     * @param actionEvent L'événement de l'action du bouton.
     * @throws SQLException            En cas d'erreur SQL lors du retour à la page d'accueil.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors du retour à la page d'accueil.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
