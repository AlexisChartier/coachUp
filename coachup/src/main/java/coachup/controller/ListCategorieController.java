package coachup.controller;

import coachup.MainApp;
import coachup.cell.CategorieManagementCellFactory;
import coachup.facade.CategorieFacade;
import coachup.model.Categorie;
import coachup.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;

/**
 * Contrôleur pour la vue de gestion des catégories.
 */
public class ListCategorieController {

    private MainApp mainApp;

    /**
     * Définit l'application principale.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private User user;

    /**
     * Définit l'utilisateur actuel.
     *
     * @param user L'utilisateur actuel.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private ListView<Categorie> categorieListView;

    private ObservableList<Categorie> categorieObservableList;

    /**
     * Initialise la vue.
     */
    @FXML
    public void initialize() {
        // Chargement des catégories depuis la base de données
        try {
            categorieObservableList = FXCollections.observableArrayList(CategorieFacade.getInstance().getAllCategories());
            categorieListView.setItems(categorieObservableList);
            categorieListView.setCellFactory(new CategorieManagementCellFactory());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère le bouton pour ajouter une nouvelle catégorie.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handleAjouterButton(ActionEvent actionEvent) {
        mainApp.showAddCategoriePage();
    }

    /**
     * Gère le bouton pour modifier une catégorie sélectionnée.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException             En cas d'erreur SQL.
     * @throws ClassNotFoundException En cas de classe non trouvée.
     */
    @FXML
    public void handleModifierButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // Logique pour afficher la page de modification de catégorie avec la catégorie sélectionnée
        Categorie selectedCategorie = categorieListView.getSelectionModel().getSelectedItem();
        CategorieFacade.getInstance().setManagedCategory(selectedCategorie);
        if (selectedCategorie != null) {
            mainApp.showModfiCatPage();
        }
    }

    /**
     * Gère le bouton pour supprimer une catégorie sélectionnée.
     *
     * @param actionEvent L'événement d'action.
     */
    @FXML
    public void handleSupprimerButton(ActionEvent actionEvent) {
        // Logique pour supprimer la catégorie sélectionnée
        Categorie selectedCategorie = categorieListView.getSelectionModel().getSelectedItem();
        if (selectedCategorie != null) {
            try {
                // Supprimer la catégorie de la base de données
                CategorieFacade.getInstance().deleteCategory(selectedCategorie.getIdcategorie());
                // Mettre à jour la liste des catégories affichées
                categorieObservableList.setAll(CategorieFacade.getInstance().getAllCategories());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gère le bouton de retour.
     *
     * @param actionEvent L'événement d'action.
     */
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showWelcomePageAdmin(user);
    }
}
