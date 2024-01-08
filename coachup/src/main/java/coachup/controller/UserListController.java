package coachup.controller;

import coachup.MainApp;
import coachup.cell.UserCellFactory;
import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import coachup.facade.UserFacade;
import coachup.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class UserListController {

    @FXML
    private TableView<User> userTableView;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        // Ajouter des utilisateurs de test à la liste (vous pouvez le remplacer par la récupération des utilisateurs depuis la base de données)
        userTableView.getItems().addAll(
                UserFacade.getInstance().getAllUsers()
        );

                // Convertissez la liste en ObservableList pour l'assigner à la ListView
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        userListView.setItems(observableUsers);
        // Configurer les colonnes
        TableColumn<User, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, Void> detailColumn = new TableColumn<>("Détail");
        detailColumn.setCellFactory(param -> new TableCellWithButton("Détail", this::showUserDetail));

        TableColumn<User, Void> deleteColumn = new TableColumn<>("Supprimer");
        deleteColumn.setCellFactory(param -> new TableCellWithButton("Supprimer", this::deleteUser));

        userTableView.getColumns().addAll(nameColumn, detailColumn, deleteColumn);
    }

    private void showUserDetail(ActionEvent event, User user) {
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
    private void deleteUser(ActionEvent event, User user) {
    }

    @FXML
    private void showUserDetail(ActionEvent event) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
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
            System.out.println("Détail de l'utilisateur : " + selectedUser.getNom());
        }
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userTableView.getItems().remove(selectedUser);
            System.out.println("Suppression de l'utilisateur : " + selectedUser.getNom());
        }
    }

    // Cette classe peut être utilisée pour créer des cellules de table avec un bouton
    private static class TableCellWithButton extends TableCell<User, Void> {
        private final Button button;
        private final ActionEventConsumer actionEventConsumer;

        public TableCellWithButton(String buttonText, ActionEventConsumer actionEventConsumer) {
            this.button = new Button(buttonText);
            this.actionEventConsumer = actionEventConsumer;
            button.setOnAction(this::handleButtonAction);
        }

        private void handleButtonAction(ActionEvent event) {
            if (actionEventConsumer != null) {
                actionEventConsumer.accept(event, getTableRow().getItem());
            }
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : button);
        }
    }

    @FunctionalInterface
    private interface ActionEventConsumer {
        void accept(ActionEvent event, User user);
    }
}
