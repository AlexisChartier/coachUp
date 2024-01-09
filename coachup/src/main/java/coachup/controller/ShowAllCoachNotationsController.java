package coachup.controller;

import coachup.MainApp;
import coachup.facade.CoachFacade;
import coachup.facade.NotationFacade;
import coachup.model.Notation;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowAllCoachNotationsController implements Initializable {

    @FXML
    private VBox notationBox;

    private User user;

    /**
     * Définit l'utilisateur associé à ce contrôleur.
     *
     * @param user L'utilisateur associé.
     */
    public void setUser(User user) {
        this.user = user;
    }

    private MainApp mainApp = new MainApp();

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Gère l'action du bouton de retour.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void backButtonAction(ActionEvent actionEvent) {
        // Ajoutez le code pour traiter l'action du bouton de retour ici
    }

    /**
     * Initialise le contrôleur, appelé automatiquement après le chargement du fichier FXML.
     * Affiche toutes les notations du coach actuel.
     *
     * @param location  L'emplacement utilisé pour résoudre les chemins relatifs des objets racine ou null si l'emplacement n'est pas connu.
     * @param resources Les ressources utilisées pour localiser l'objet racine, ou null si l'objet racine n'est pas localisé.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Notation[] notations = new Notation[0];
        try {
            notations = NotationFacade.getInstance().getNotationByCoachId(CoachFacade.getInstance().getCurrentCoach().getIdUtilisateur());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Notation notation : notations) {
            HBox notationHBox = new HBox();
            notationHBox.getChildren().add(new Label(" Note: " + notation.getNote()));
            notationHBox.getChildren().add(new Label(" Commentaire: " + notation.getComment()));
            notationHBox.getChildren().add(new Label(" Pseudo de l'utilisateur : " + notation.getUserName()));
            notationBox.getChildren().add(notationHBox);
        }
    }

    /**
     * Gère l'action du bouton de retour vers la page d'accueil du coach.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showWelcomePageCoach();
    }
}
