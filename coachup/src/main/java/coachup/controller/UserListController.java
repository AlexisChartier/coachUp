package coachup.controller;

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

    private void deleteUser(ActionEvent event, User user) {
    }

    @FXML
    private void showUserDetail(ActionEvent event) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
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
