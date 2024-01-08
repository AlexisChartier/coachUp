package coachup.controller;

import coachup.MainApp;
import coachup.cell.CategorieCellFactory;
import coachup.facade.CategorieFacade;
import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Categorie;
import coachup.model.Coach;
import coachup.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class CoachDetailController {


    @FXML
    public TextField newNameTextField;
    @FXML
    public TextField newEmailTextField;
    @FXML
    public VBox newCategoriesComboBox;

    @FXML
    public TextField newDiplomaTextField;
    @FXML
    public TextField newPriceTextField;
    @FXML
    public Label currentDiplomaLabel;

    @FXML
    public Label currentPriceLabel;
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
    public Label currentDiplomaField;


    @FXML
    public void handleReturnButton() throws SQLException, ClassNotFoundException {
        mainApp.showCoachApprovalList(UserFacade.getInstance().getCurrentUser());
    }

    @FXML
    public void handleDeleteButton() throws SQLException, ClassNotFoundException {
        if (coach != null) {
            int id = coach.getIdUtilisateur();
                if(CoachFacade.getInstance().getCoachById(coach.getIdUtilisateur()) != null){
                    CoachFacade.getInstance().deleteCoach(coach.getIdUtilisateur());
                }
                UserFacade.getInstance().deleteUser(id);
                coach = null;
                mainApp.showCoachApprovalList(UserFacade.getInstance().getCurrentUser());
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

            currentDiplomaField.setText(coach.getDiplome());
            currentNameLabel.setText(coach.getNom());
            currentEmailLabel.setText(coach.getEmail());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void handleSaveButton(ActionEvent actionEvent) {

    }
}
