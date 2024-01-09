package coachup;

import coachup.controller.*;
import coachup.facade.CreneauDispoFacade;
import coachup.model.Coach;
import coachup.model.Creneau_dispo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import coachup.facade.UserFacade;
import coachup.facade.CoachFacade;
import coachup.facade.NotationFacade;
import coachup.model.Notation;
import coachup.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Classe principale de l'application CoachUp.
 */
public class MainApp extends Application {

    /**
     * Méthode principale de l'application qui lance l'interface graphique.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;

    /**
     * Méthode appelée au démarrage de l'application pour initialiser l'interface graphique.
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @throws Exception En cas d'erreur lors du chargement de l'interface graphique.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CoachUp");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        Parent root = (Parent) loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        Scene scene = new Scene(root);
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * Affiche la page de connexion.
     */
    public void showLoginPage() throws IOException {
        this.primaryStage.close();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        Parent root = (Parent) loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        Scene scene = new Scene(root);
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(scene);
        this.primaryStage.show();    }

    /**
     * Affiche la page de bienvenue après une authentification réussie.
     *
     * @param user L'utilisateur authentifié.
     */
    public void showWelcomePage(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/welcome.fxml")));
            Parent root = (Parent) loader.load();

            WelcomeController welcomeController = loader.getController();
            welcomeController.setUser(user);
            welcomeController.setMainApp(this);
            Stage welcomeStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = welcomeStage;
            Scene scene = new Scene(root);
            welcomeStage.setScene(scene);
            welcomeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page d'accueil pour un coach.
     */
    public void showWelcomePageCoach(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/welcomeCoach.fxml")));
            Parent root = (Parent) loader.load();

            WelcomeControllerCoach welcomeControllerCoach = loader.getController();
            welcomeControllerCoach.setMainApp(this);
            Stage welcomeStageCoach = new Stage();
            this.primaryStage.close();
            this.primaryStage = welcomeStageCoach;
            Scene scene = new Scene(root);
            welcomeStageCoach.setScene(scene);
            welcomeStageCoach.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Affiche la page d'inscription pour les étudiants.
     */
    public void showRegisterStudentPage(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/registerStudent.fxml")));
            Parent root = (Parent) loader.load();

            RegisterStudentController registerStudentController = loader.getController();
            registerStudentController.setMainApp(this);
            Stage registerStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = registerStage;
            Scene scene = new Scene(root);
            registerStage.setScene(scene);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page d'inscription pour les coachs.
     */
    public void showRegisterCoachPage(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/registerCoach.fxml")));
            Parent root = (Parent) loader.load();

            RegisterCoachController registerCoachController = loader.getController();
            registerCoachController.setMainApp(this);
            Stage registerCoachStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = registerCoachStage;
            Scene scene = new Scene(root);
            registerCoachStage.setScene(scene);
            registerCoachStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la liste des utilisateurs.
     *
     * @param user L'utilisateur administrateur.
     */
    public void showUserList(User user){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/userList.fxml")));
            Parent root = (Parent) loader.load();

            UserListController userListController = loader.getController();
            userListController.setMainApp(this);
            userListController.setAdminUser(user);
            Stage userListStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = userListStage;
            Scene scene = new Scene(root);
            userListStage.setScene(scene);
            userListStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page de détail du profil d'un utilisateur.
     *
     * @param user      L'utilisateur dont le profil doit être affiché.
     * @param userAdmin L'utilisateur administrateur.
     */
    public void showDetailPage(User user, User userAdmin){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/detailProfile.fxml")));
            Parent root = (Parent) loader.load();

            DetailProfileController detailProfileController = loader.getController();
            detailProfileController.setMainApp(this);
            detailProfileController.setUser(user);
            detailProfileController.setAdminUser(userAdmin);
            Stage detailStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = detailStage;
            Scene scene = new Scene(root);
            detailStage.setScene(scene);
            detailStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
    public boolean addNotation(String comment, int notation, int CoachId, int UserId) throws SQLException, ClassNotFoundException {

    }

*/

    /**
     * Affiche la page d'accueil pour un administrateur.
     *
     * @param user L'utilisateur administrateur.
     */
    public void showWelcomePageAdmin(User user){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/welcomeAdmin.fxml")));
            Parent root = (Parent) loader.load();

            WelcomeAdminController welcomeAdminController = loader.getController();
            welcomeAdminController.setMainApp(this);
            welcomeAdminController.setCurrentUser(user);
            Stage welcomeAdminStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = welcomeAdminStage;
            Scene scene = new Scene(root);
            welcomeAdminStage.setScene(scene);
            welcomeAdminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche le détail d'un coach
     */
    public void showDetailCoach(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/coachDetail.fxml")));
            Parent root = (Parent) loader.load();

            CoachDetailController coachDetailController = loader.getController();
            coachDetailController.setMainApp(this);
            coachDetailController.setMainApp(this);
            Stage coachDetailStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = coachDetailStage;
            Scene scene = new Scene(root);
            coachDetailStage.setScene(scene);
            coachDetailStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Affiche la page d'ajout de notation
     */
    public void showAddNotation(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/addnotation.fxml")));
            Parent root = (Parent) loader.load();

            AddNotationController addnotationcontroller = loader.getController();
            addnotationcontroller.setMainApp(this);
            Stage addnotationstage = new Stage();
            this.primaryStage.close();
            this.primaryStage = addnotationstage;
            Scene scene = new Scene(root);
            addnotationstage.setScene(scene);
            addnotationstage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page de modification de notation
     */
    public void showModifyNotation(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/modifynotation.fxml")));
            Parent root = (Parent) loader.load();

            ModifyNotationController modifynotationcontroller = loader.getController();
            modifynotationcontroller.setMainApp(this);
            Stage modifynotationstage = new Stage();
            this.primaryStage.close();
            this.primaryStage = modifynotationstage;
            Scene scene = new Scene(root);
            modifynotationstage.setScene(scene);
            modifynotationstage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Montre toutes les notations d'un coach
     */
    public void ShowAllCoachNotations(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/showAllCoachNotations.fxml")));
            Parent root = (Parent) loader.load();

            ShowAllCoachNotationsController showAllCoachNotationsController = loader.getController();
            showAllCoachNotationsController.setMainApp(this);
            Stage showAllCoachNotationsStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = showAllCoachNotationsStage;
            Scene scene = new Scene(root);
            showAllCoachNotationsStage.setScene(scene);
            showAllCoachNotationsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Montre toutes les notes attribuées par un user
     */
    public void ShowAllUserNotations(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/showAllUserNotations.fxml")));
            Parent root = (Parent) loader.load();

            ShowAllUserNotationsController showAllUserNotationsController = loader.getController();
            showAllUserNotationsController.setMainApp(this);
            Stage showAllUserNotationsStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = showAllUserNotationsStage;
            Scene scene = new Scene(root);
            showAllUserNotationsStage.setScene(scene);
            showAllUserNotationsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Montre la page de gestion du calendrier du coach
     */
    public void showCalendarPage() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/calendar.fxml")));
            Parent root = (Parent) loader.load();
            CalendarController calendarController = loader.getController();
            calendarController.setMainApp(this);
            Stage calendarStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = calendarStage;
            Scene scene = new Scene(root);
            calendarStage.setScene(scene);
            calendarStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Montre la page d'ajout de disponibilité du coach
     * @param creneau
     */
    public void openAddAvailabilityPage(Creneau_dispo creneau){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddAvailability.fxml"));
            Parent root = loader.load();
            AddAvailabilityController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCreneau_dispo(creneau);

            Stage stage = new Stage();
            this.primaryStage.close();
            this.primaryStage = stage;
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openModifyAvailabilityPage(List<Creneau_dispo> listcreneaux){
        try{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/ModifyAvailability.fxml")));
            Parent root = (Parent) loader.load();
            ModifyAvailabilityController modifyavailabilitycontroller = loader.getController();
            modifyavailabilitycontroller.setCreneaux(listcreneaux);
            modifyavailabilitycontroller.setMainApp(this);
            Stage modifyavailabilitystage = new Stage();
            this.primaryStage.close();
            this.primaryStage = modifyavailabilitystage;
            Scene scene = new Scene(root);
            modifyavailabilitystage.setScene(scene);
            modifyavailabilitystage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void openModifyCreneauDispoPage(Creneau_dispo creneau_dispo){
        try{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/ModifyCreneauDispo.fxml")));
            Parent root = (Parent) loader.load();
            ModifyCreneauDispoController Controller = loader.getController();
            Controller.setCurrentCreneauDispo(creneau_dispo);
            Controller.setMainApp(this);
            Controller.setup();
            Stage modifyCreneauDispoStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = modifyCreneauDispoStage;
            Scene scene = new Scene(root);
            modifyCreneauDispoStage.setScene(scene);
            modifyCreneauDispoStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Affiche la liste des coachs en attente d'approbation.
     *
     * @param user L'utilisateur administrateur.
     */
    public void showCoachApprovalList(User user){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/coachApprobationList.fxml")));
            Parent root = (Parent) loader.load();

            CoachApprobationController coachApprobationController = loader.getController();
            coachApprobationController.setMainApp(this);
            coachApprobationController.setUser(user);
            Stage coachAppStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = coachAppStage;
            Scene scene = new Scene(root);
            coachAppStage.setScene(scene);
            coachAppStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la liste des catégories disponibles.
     *
     * @param currentUser L'utilisateur connecté.
     */
    public void showCategoriesList(User currentUser) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/listCategorie.fxml")));
            Parent root = (Parent) loader.load();

            ListCategorieController listCategorieController = loader.getController();
            listCategorieController.setMainApp(this);
            listCategorieController.setUser(currentUser);
            Stage listCatStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = listCatStage;
            Scene scene = new Scene(root);
            listCatStage.setScene(scene);
            listCatStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la page d'ajout d'une nouvelle catégorie.
     */
    public void showAddCategoriePage(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/ajoutCategorie.fxml")));
            Parent root = (Parent) loader.load();

            AjoutCategorieController ajoutCategorieController = loader.getController();
            ajoutCategorieController.setMainApp(this);
            Stage addCatStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = addCatStage;
            Scene scene = new Scene(root);
            addCatStage.setScene(scene);
            addCatStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page de modification d'une catégorie existante.
     */
    public void showModfiCatPage(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/modifCategorie.fxml")));
            Parent root = (Parent) loader.load();

            ModificationCategorieController modificationCategorieController = loader.getController();
            modificationCategorieController.setMainApp(this);
            Stage modifCatStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = modifCatStage;
            Scene scene = new Scene(root);
            modifCatStage.setScene(scene);
            modifCatStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la page d'historique des séances.
     */
    public void showHistSeance() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/histSeance.fxml")));
            Parent root = (Parent) loader.load();

            HistSeanceController histSeanceController = loader.getController();
            histSeanceController.setMainApp(this);
            Stage histSeanceStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = histSeanceStage;
            Scene scene = new Scene(root);
            histSeanceStage.setScene(scene);
            histSeanceStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la page de détails d'une séance.
     */
    public void showDetailSeance() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/seanceDetail.fxml")));
            Parent root = (Parent) loader.load();

            SeanceDetailController seanceDetailController = loader.getController();
            seanceDetailController.setMainApp(this);
            Stage seanceDetailStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = seanceDetailStage;
            Scene scene = new Scene(root);
            seanceDetailStage.setScene(scene);
            seanceDetailStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche le hub de paiement.
     */
    public void showHubPayment() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/hubPayment.fxml")));
            Parent root = (Parent) loader.load();

            HubPaymentController hubPaymentController = loader.getController();
            hubPaymentController.setMainApp(this);
            Stage hubPayStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = hubPayStage;
            Scene scene = new Scene(root);
            hubPayStage.setScene(scene);
            hubPayStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la liste des paiements en attente.
     */
    public void showPendingPayment() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/pendingPayments.fxml")));
            Parent root = (Parent) loader.load();

            PendingPaymentsController pendingPaymentsController = loader.getController();
            pendingPaymentsController.setMainApp(this);
            Stage pendingSess = new Stage();
            this.primaryStage.close();
            this.primaryStage = pendingSess;
            Scene scene = new Scene(root);
            pendingSess.setScene(scene);
            pendingSess.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la liste des prochaines séances.
     */
    public void showUpComingSessions() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/upcomingSessions.fxml")));
            Parent root = (Parent) loader.load();

            UpcomingSessionsController upcomingSessionsController = loader.getController();
            upcomingSessionsController.setMainApp(this);
            Stage upcomingSess = new Stage();
            this.primaryStage.close();
            this.primaryStage = upcomingSess;
            Scene scene = new Scene(root);
            upcomingSess.setScene(scene);
            upcomingSess.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la page des paiements en attente pour une séance spécifique.
     */
    public void showPaymentPage() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/pendingSeance.fxml")));
            Parent root = (Parent) loader.load();

            PendingSeanceDetailController pendingSeanceDetailController = loader.getController();
            pendingSeanceDetailController.setMainApp(this);
            Stage pendingSessD = new Stage();
            this.primaryStage.close();
            this.primaryStage = pendingSessD;
            Scene scene = new Scene(root);
            pendingSessD.setScene(scene);
            pendingSessD.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la page des détails d'une séance à venir.
     */
    public void showUpcomingDetail() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/upcomingSeance.fxml")));
            Parent root = (Parent) loader.load();

            UpcomingSessionDetailController upcomingSessionDetailController = loader.getController();
            upcomingSessionDetailController.setMainApp(this);
            Stage upDetail = new Stage();
            this.primaryStage.close();
            this.primaryStage = upDetail;
            Scene scene = new Scene(root);
            upDetail.setScene(scene);
            upDetail.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Affiche la liste des coachs.
     */
    public void showListCoachPage() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/listCoach.fxml")));
            Parent root = (Parent) loader.load();

            ListCoachController listCoachController = loader.getController();
            listCoachController.setMainApp(this);
            Stage listCoachStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = listCoachStage;
            Scene scene = new Scene(root);
            listCoachStage.setScene(scene);
            listCoachStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page de recherche de coachs.
     */
    public void showRechercheCoach(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/rechercheCoach.fxml")));
            Parent root = (Parent) loader.load();

            RechercheCoachController rechercheCoachController = loader.getController();
            rechercheCoachController.setMainApp(this);
            Stage rechercheStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = rechercheStage;
            Scene scene = new Scene(root);
            rechercheStage.setScene(scene);
            rechercheStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page de réservation.
     */
    public void showReservationPage() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/reservationPage.fxml")));
            Parent root = (Parent) loader.load();

            ReservationPageController reservationPageController = loader.getController();
            reservationPageController.setMainApp(this);
            Stage rechercheStage = new Stage();
            this.primaryStage.close();
            this.primaryStage = rechercheStage;
            Scene scene = new Scene(root);
            rechercheStage.setScene(scene);
            rechercheStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
