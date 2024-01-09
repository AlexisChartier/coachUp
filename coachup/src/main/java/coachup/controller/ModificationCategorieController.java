package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Contrôleur pour la page de modification d'une catégorie
 */
public class ModificationCategorieController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    private Categorie selectedCategorie;

    /**
     * Définit la catégorie sélectionnée pour la modification.
     *
     * @param categorie La catégorie à modifier.
     */
    public void setSelectedCategorie(Categorie categorie) {
        this.selectedCategorie = categorie;
    }

    /**
     * Initialise les champs avec les données de la catégorie sélectionnée.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        selectedCategorie = CategorieFacade.getInstance().getManagedCategorie();
        populateFields();
    }

    /**
     * Remplit les champs avec les données de la catégorie sélectionnée.
     */
    private void populateFields() {
        if (selectedCategorie != null) {
            nomField.setText(selectedCategorie.getNom());
            descriptionField.setText(selectedCategorie.getDescription());
        }
    }

    /**
     * Gère l'action du bouton de modification.
     */
    @FXML
    public void handleModifierButton() {
        if (selectedCategorie != null) {
            try {
                Categorie categorie = new Categorie();
                categorie.setIdcategorie(selectedCategorie.getIdcategorie());
                categorie.setNom(nomField.getText());
                categorie.setDescription(descriptionField.getText());
                CategorieFacade.getInstance().updateCategory(categorie);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gère l'action du bouton de retour.
     *
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleReturnButton() throws SQLException, ClassNotFoundException {
        mainApp.showCategoriesList(UserFacade.getInstance().getCurrentUser());
    }
}
