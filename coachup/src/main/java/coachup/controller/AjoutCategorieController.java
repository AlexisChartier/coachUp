package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Contrôleur pour la vue d'ajout de catégorie.
 */
public class AjoutCategorieController {

    private MainApp mainApp;

    /**
     * Configure l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    /**
     * Gère l'action du bouton "Ajouter" pour ajouter une nouvelle catégorie.
     */
    @FXML
    public void handleAjouterButton(ActionEvent actionEvent) {
        try {
            Categorie categorie = new Categorie();
            categorie.setDescription(descriptionField.getText());
            categorie.setNom(nomField.getText());
            CategorieFacade.getInstance().addCategory(categorie);
            mainApp.showCategoriesList(UserFacade.getInstance().getCurrentUser());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton de retour à la page d'accueil de l'administrateur.
     *
     * @param actionEvent L'événement de l'action du bouton.
     * @throws SQLException            En cas d'erreur SQL lors du retour à la page d'accueil de l'administrateur.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée lors du retour à la page d'accueil de l'administrateur.
     */
    @FXML
    public void handleReturnButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        mainApp.showWelcomePageAdmin(UserFacade.getInstance().getCurrentUser());
    }
}
