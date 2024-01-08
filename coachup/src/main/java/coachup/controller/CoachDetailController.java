package coachup.controller;

import coachup.MainApp;
import coachup.cell.CategorieCellFactory;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import coachup.model.Coach;
import coachup.model.User;
import com.sun.javafx.scene.control.IntegerField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDetailController {


    @FXML
    public TextField newNameTextField;
    @FXML
    public TextField newEmailTextField;
    @FXML
    public VBox newCategoriesBox;

    @FXML
    public TextField newDiplomaTextField;
    @FXML
    public IntegerField newPriceIntegerField;
    @FXML
    public Label currentDiplomaLabel;

    @FXML
    public Label currentPriceLabel;

    @FXML
    public TextField newPasswordField;
    @FXML
    public Label currentPasswordField;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Coach coach;

    @FXML
    public Label currentNameLabel;
    @FXML
    public Label currentEmailLabel;

    @FXML
    public ComboBox<Categorie> currentCategoriesComboBox;

    @FXML
    public Label getCurrentDiplomaLabel;


    @FXML
    public void handleReturnButton() throws SQLException, ClassNotFoundException {
        if(UserFacade.getInstance().getCurrentUser().getRole().equals("admin")){
            mainApp.showCoachApprovalList(UserFacade.getInstance().getCurrentUser());
        }
        else{
            mainApp.showWelcomePageCoach();
        }
    }

    @FXML
    public void handleDeleteButton() throws SQLException, ClassNotFoundException, IOException {
        if (coach != null) {
            int id = coach.getIdUtilisateur();
                if(CoachFacade.getInstance().getCoachById(coach.getIdUtilisateur()) != null){
                    CoachFacade.getInstance().deleteCoach(coach.getIdUtilisateur());
                }
                UserFacade.getInstance().deleteUser(id);
                coach = null;
                if(UserFacade.getInstance().getCurrentUser().getRole().equals("admin")){
                    mainApp.showCoachApprovalList(UserFacade.getInstance().getCurrentUser());
                }
                else{
                    mainApp.showLoginPage();
                }
        }
    }

    @FXML
    public void initialize() {
        try{
            CoachFacade coachFacade = CoachFacade.getInstance();
            if(UserFacade.getInstance().getCurrentUser().getRole().equals("admin")){
                coach = CoachFacade.getInstance().getManagedCoach();
            }
            else{
                coach = CoachFacade.getInstance().getCurrentCoach();
            }
            List<Categorie> allCategories = coachFacade.getCategoriesByCoachID(coach.getIdUtilisateur());

            // Ajouter un élément fictif pour le texte par défaut
            Categorie defaultCategory = new Categorie();
            defaultCategory.setNom("Sélectionner une catégorie");
            allCategories.add(0, defaultCategory);

            ObservableList<Categorie> categoriesObservableList = FXCollections.observableArrayList(allCategories);

            // Utiliser une cellule d'usine pour afficher uniquement le nom de la catégorie
            currentCategoriesComboBox.setCellFactory(param -> new ListCell<Categorie>() {
                @Override
                protected void updateItem(Categorie item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });

            // Utiliser une cellule d'usine pour afficher le nom de la catégorie dans la liste déroulante
            currentCategoriesComboBox.setButtonCell(new ListCell<Categorie>() {
                @Override
                protected void updateItem(Categorie item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText("");
                    } else {
                        setText(item.getNom());
                    }
                }
            });

            // Ajouter les catégories à la ComboBox
            currentCategoriesComboBox.setItems(categoriesObservableList);

            // Sélectionner l'élément fictif comme élément par défaut
            currentCategoriesComboBox.getSelectionModel().select(0);

            currentDiplomaLabel.setText(coach.getDiplome());
            currentNameLabel.setText(coach.getNom());
            currentEmailLabel.setText(coach.getEmail());
            currentPasswordField.setText(coach.getMotDePasse());
            loadCategories();
            newCategoriesBox.setAlignment(Pos.CENTER);
            Integer prix = coach.getPrix();
            currentPriceLabel.setText(prix.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void handleSaveButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String nom = newNameTextField.getText();
        String email = newEmailTextField.getText();
        String password = newPasswordField.getText();
        String diplome = newDiplomaTextField.getText();
        int prix = newPriceIntegerField.getValue();
        ArrayList<Integer> selectedCategoryIds = new ArrayList<>();

        for (Node node : newCategoriesBox.getChildren()) {
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
        coach.setPrix(prix);
        CoachFacade coachFacade = CoachFacade.getInstance();
        User user = new User();
        user.setEmail(coach.getEmail());
        String emailcoach = coach.getEmail();
        user.setNom(coach.getNom());
        user.setMotDePasse(coach.getMotDePasse());
        user.setRole("coach");
        int iduser = UserFacade.getInstance().updateUser(user);
        user = UserFacade.getInstance().getUserById(iduser);
        coach.setIdUtilisateur(user.getIdUtilisateur());
        coachFacade.updateCoach(coach);
        if(UserFacade.getInstance().getCurrentUser().getRole().equals("coach")){
            mainApp.showWelcomePageCoach();
        }
        else{
            mainApp.showWelcomePageAdmin(UserFacade.getInstance().getCurrentUser());
        }

    }

    private void loadCategories() {
        try {
            // Récupérer la liste des catégories depuis la base de données
            CategorieFacade categorieFacade = CategorieFacade.getInstance();
            List<Categorie> categories = categorieFacade.getAllCategories();

            // Créer des CheckBox pour chaque catégorie et les ajouter à la boîte
            for (Categorie categorie : categories) {
                CheckBox checkBox = new CheckBox(categorie.getNom());
                checkBox.getProperties().put("categorieId", categorie.getIdcategorie());

                newCategoriesBox.getChildren().add(checkBox);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Gérer les exceptions en conséquence
        }
    }
}
