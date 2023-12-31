package coachup.controller;

import coachup.MainApp;
import coachup.facade.NotationFacade;
import coachup.facade.UserFacade;
import coachup.model.Notation;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyNotationController implements Initializable {

    @FXML
    private Slider notationSlider;

    @FXML
    private TextArea commentField;

    @FXML
    private javafx.scene.control.Label coachnameLabel;

    private UserFacade userfacade = UserFacade.getInstance();

    private MainApp mainApp = new MainApp();

    /**
     * Initialise un nouveau contrôleur ModifyNotationController.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    public ModifyNotationController() throws SQLException, ClassNotFoundException {
    }

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initialise la page avec les valeurs par défaut.
     *
     * @param location L'emplacement pour l'initialisation.
     * @param resources Les ressources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int noteid = userfacade.getNotationid();
        Notation notation = null;
        try {
            notation = NotationFacade.getInstance().getNotationById(noteid);
            int coachid = notation.getCoachId();
            User coach = userfacade.getUserById(coachid);
            coachnameLabel.setText(coach.getNom());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Définir la valeur du Slider à la note de la notation
        notationSlider.setValue(notation.getNote());
        // Définir le texte du champ de commentaire à celui de la notation
        commentField.setText(notation.getComment());
    }

    /**
     * Gère l'action du bouton de modification de notation.
     *
     * @param event L'événement déclenché par le bouton.
     */
    @FXML
    private void modifyNotationButtonAction(ActionEvent event) {
        try {
            int notationid = userfacade.getNotationid();
            Notation notation = NotationFacade.getInstance().getNotationById(notationid);
            notation.setComment(commentField.getText());
            notation.setNote((float) notationSlider.getValue());
            if (NotationFacade.getInstance().modifyNotation(notation)) {
                System.out.println("Notation modified");
            } else {
                System.out.println("Notation modification failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton de suppression de notation.
     *
     * @param event L'événement déclenché par le bouton.
     */
    @FXML
    private void deleteNotationButtonAction(ActionEvent event) {
        try {
            int notationid = 1;
            if (NotationFacade.getInstance().deleteNotation(notationid)) {
                System.out.println("Notation deleted");
            } else {
                System.out.println("Notation delete failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton de retour.
     *
     * @param actionEvent L'événement déclenché par le bouton.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePage(UserFacade.getInstance().getCurrentUser());
    }
}
