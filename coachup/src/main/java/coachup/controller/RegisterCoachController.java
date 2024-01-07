package coachup.controller;

import coachup.MainApp;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.model.Categorie;
import coachup.model.Coach;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterCoachController {

    @FXML
    public TextField diplomeField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField nameField;

    @FXML
    public VBox categoriesBox;

    private MainApp mainApp = new MainApp();
    private CategorieFacade categorieFacade = CategorieFacade.getInstance();

    private CoachFacade coachFacade = CoachFacade.getInstance();

    public RegisterCoachController() throws SQLException, ClassNotFoundException {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        // Appeler la méthode pour charger les catégories depuis la base de données
        loadCategories();
        categoriesBox.setAlignment(Pos.CENTER);
    }

    private void loadCategories() {
        try {
            // Récupérer la liste des catégories depuis la base de données
            List<Categorie> categories = categorieFacade.getAllCategories();

            // Créer des CheckBox pour chaque catégorie et les ajouter à la boîte
            for (Categorie categorie : categories) {
                CheckBox checkBox = new CheckBox(categorie.getNom());
                checkBox.getProperties().put("categorieId", categorie.getIdcategorie());

                categoriesBox.getChildren().add(checkBox);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Gérer les exceptions en conséquence
        }
    }

    @FXML
    public void handleRegisterButton() throws SQLException, ClassNotFoundException, IOException {
        String nom = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String diplome = diplomeField.getText();
        ArrayList<Integer> selectedCategoryIds = new ArrayList<>();

        for (Node node : categoriesBox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;

                // Vérifier si la CheckBox est sélectionnée
                if (checkBox.isSelected()) {
                    // Récupérer l'ID associé à la CheckBox
                    Integer categoryId = (Integer) checkBox.getProperties().get("categorieId");

                    // Ajouter l'ID à la liste
                    selectedCategoryIds.add(categoryId);
                }
            }

            // Ajouter votre logique pour gérer l'inscription du coach ici
        }
        Coach coach = new Coach();
        Integer[] arr = null;
        arr = selectedCategoryIds.toArray(selectedCategoryIds.toArray(new Integer[0]));
        coach.setCategories(arr);
        coach.setNom(nom);
        coach.setDiplome(diplome);
        coach.setMotDePasse(password);
        coach.setEmail(email);
        mainApp.registerCoachUser(coach);
    }
}
