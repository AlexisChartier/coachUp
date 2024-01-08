package coachup.controller;

import coachup.MainApp;
import coachup.cell.UserCellFactory;
import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import coachup.model.User;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class UserListController {

    private MainApp mainApp = new MainApp();

    private User selectedUser;

    private User adminUser;

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private ListView<User> userListView;

    // Méthode appelée automatiquement après chargement de la vue
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        // Récupérez la liste des utilisateurs depuis votre DAO
        List<User> users = UserFacade.getInstance().getAllUsers();
        // Votre logique pour récupérer les utilisateurs depuis la base de données

        // Convertissez la liste en ObservableList pour l'assigner à la ListView
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        userListView.setItems(observableUsers);

        // Personnalisez la cellule de la ListView avec la factory que nous avons définie
        userListView.setCellFactory(new UserCellFactory());

        userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedUser = newValue;
        });
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    // Ajoutez d'autres méthodes ou logique au besoin

    // Méthode appelée lorsqu'un élément est sélectionné dans la ListView
    @FXML
    private void handleItemSelected() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Logique pour gérer la sélection de l'utilisateur
            System.out.println("Utilisateur sélectionné : " + selectedUser.getNom());
        }
    }

    public void handleDetailButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserFacade.getInstance().setManagedUser(selectedUser);
        mainApp.showDetailPage(selectedUser, adminUser);
    }

    @FXML
    private void handleDeleteButton() throws SQLException, ClassNotFoundException {
        if (selectedUser != null) {
            if(selectedUser.getRole().equals("student")){
                // Appelez votre méthode de suppression dans la classe UserDao (ou similaire)
                UserFacade.getInstance().deleteUser(selectedUser.getIdUtilisateur());

                // Rafraîchissez la liste après la suppression si nécessaire
                // userListView.getItems().remove(selectedUser);
                // ou rechargez toute la liste depuis la base de données
                List<User> users = UserFacade.getInstance().getAllUsers();
                // Votre logique pour récupérer les utilisateurs depuis la base de données

                // Convertissez la liste en ObservableList pour l'assigner à la ListView
                ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
                userListView.setItems(observableUsers);

                // Réinitialisez la variable selectedUser
                selectedUser = null;
            }
            else if(selectedUser.getRole().equals("coach")){
                if(CoachFacade.getInstance().getCoachById(selectedUser.getIdUtilisateur()) != null){
                    CoachFacade.getInstance().deleteCoach(selectedUser.getIdUtilisateur());
                }
                List<User> users = UserFacade.getInstance().getAllUsers();
                UserFacade.getInstance().deleteUser(selectedUser.getIdUtilisateur());
                // Convertissez la liste en ObservableList pour l'assigner à la ListView
                ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
                userListView.setItems(observableUsers);

                // Réinitialisez la variable selectedUser
                selectedUser = null;
            }
        }
    }

    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showWelcomePageAdmin(adminUser);
    }
}
