package coachup.controller;

import coachup.MainApp;
import coachup.cell.UserCellFactory;
import coachup.facade.CoachFacade;
import coachup.facade.UserFacade;
import coachup.model.Coach;
import coachup.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class UserListController {

    private MainApp mainApp = new MainApp();

    private User selectedUser;

    private User adminUser;

    /**
     * Définit l'utilisateur administrateur pour ce contrôleur.
     *
     * @param adminUser L'utilisateur administrateur.
     */
    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    /**
     * Définit l'application principale pour ce contrôleur.
     *
     * @param mainApp L'application principale.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private ListView<User> userListView;

    /**
     * Initialise le contrôleur, appelé automatiquement après le chargement du fichier FXML.
     *
     * @throws SQLException           Si une erreur SQL survient.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée.
     */
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        List<User> users = UserFacade.getInstance().getAllUsers();
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        userListView.setItems(observableUsers);
        userListView.setCellFactory(new UserCellFactory());

        userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedUser = newValue;
        });
    }

    /**
     * Renvoie l'utilisateur sélectionné dans la ListView.
     *
     * @return L'utilisateur sélectionné.
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     * Gère l'action du bouton de détail pour l'utilisateur sélectionné.
     *
     * @param actionEvent L'événement d'action.
     * @throws SQLException           Si une erreur SQL survient.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée.
     */
    @FXML
    public void handleDetailButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserFacade.getInstance().setManagedUser(selectedUser);
        mainApp.showDetailPage(selectedUser, adminUser);
    }

    /**
     * Gère l'action du bouton de suppression pour l'utilisateur sélectionné.
     *
     * @throws SQLException           Si une erreur SQL survient.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée.
     */
    @FXML
    private void handleDeleteButton() throws SQLException, ClassNotFoundException {
        if (selectedUser != null) {
            if (selectedUser.getRole().equals("student")) {
                UserFacade.getInstance().deleteUser(selectedUser.getIdUtilisateur());
                refreshUserList();
            } else if (selectedUser.getRole().equals("coach")) {
                if (CoachFacade.getInstance().getCoachById(selectedUser.getIdUtilisateur()) != null) {
                    CoachFacade.getInstance().deleteCoach(selectedUser.getIdUtilisateur());
                }
                UserFacade.getInstance().deleteUser(selectedUser.getIdUtilisateur());
                refreshUserList();
            }
        }
    }

    /**
     * Rafraîchit la liste des utilisateurs dans la ListView.
     *
     * @throws SQLException           Si une erreur SQL survient.
     * @throws ClassNotFoundException Si la classe n'est pas trouvée.
     */
    private void refreshUserList() throws SQLException, ClassNotFoundException {
        List<User> users = UserFacade.getInstance().getAllUsers();
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        userListView.setItems(observableUsers);
        selectedUser = null;
    }

    /**
     * Gère l'action du bouton de retour vers la page d'accueil de l'administrateur.
     *
     * @param actionEvent L'événement d'action.
     */
    public void handleReturnButton(ActionEvent actionEvent) {
        mainApp.showWelcomePageAdmin(adminUser);
    }
}
