package coachup;

import coachup.controller.*;
import coachup.model.Coach;
import coachup.facade.CreneauDispoFacade;
import coachup.facade.NotationFacade;
import coachup.facade.UserFacade;
import coachup.model.Notation;
import coachup.model.User;
import coachup.model.Creneau_dispo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        this.primaryStage = primaryStage;
        primaryStage.setTitle("CoachUp");
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        Parent root = (Parent) loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

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

    public void showRegisterCoachPage(){

    }

    public void showUserList(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/userListView.fxml")));
            Parent root = (Parent) loader.load();

            UserListController userListController = loader.getController();
            //userListController.setMainApp(this);
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
     * Authentifie l'utilisateur en vérifiant les informations de connexion.
     *
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si l'authentification est réussie, false sinon.
     */
    public boolean authenticateUser(String email, String password) throws SQLException, ClassNotFoundException, IOException {
        UserFacade userFacade = UserFacade.getInstance();

        boolean isAuthenticated = userFacade.loginUser(email, password);

        if (isAuthenticated) {
            User user = userFacade.getUserByEmail(email);
            if(Objects.equals(user.getRole(), "student")){
                showWelcomePage(user);
            }
            else if(Objects.equals(user.getRole(), "admin")){
                showWelcomePageAdmin();
            }
            else if(Objects.equals(user.getRole(), "coach")){
                CoachFacade coachFacade = CoachFacade.getInstance();
                Coach coach = coachFacade.getCoachById(user.getIdUtilisateur());
                coachFacade.setCurrentCoach(coach);
                if(coach.getApproved()){
                    //ShowWelcomePageCoach
                }
                else{
                    showLoginPage();
                }
            }
        } else {
            System.out.println("Authentication failed. Invalid email or password.");
        }

        return isAuthenticated;
    }

    /**
    public boolean addNotation(String comment, int notation, int CoachId, int UserId) throws SQLException, ClassNotFoundException {

    }

*/

    public void showWelcomePageAdmin(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/view/welcomeAdmin.fxml")));
            Parent root = (Parent) loader.load();

            WelcomeAdminController welcomeAdminController = loader.getController();
            welcomeAdminController.setMainApp(this);
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
        }
    }
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



    public boolean registerStudentUser(User user) throws SQLException, ClassNotFoundException {
        UserFacade userFacade  = UserFacade.getInstance();
        return userFacade.addUser(user);
    }

    public void registerCoachUser(Coach coach) throws SQLException, ClassNotFoundException, IOException {
        CoachFacade coachFacade = CoachFacade.getInstance();
        User user = new User();
        user.setEmail(coach.getEmail());
        String email = coach.getEmail();
        user.setNom(coach.getNom());
        user.setMotDePasse(coach.getMotDePasse());
        user.setRole("coach");
        int iduser = UserFacade.getInstance().addUser(user);
        user = UserFacade.getInstance().getUserById(iduser);
        coach.setIdUtilisateur(user.getIdUtilisateur());
        coachFacade.addCoach(coach);
        this.showLoginPage();
    }

    public boolean addNotation(Notation notation) throws SQLException, ClassNotFoundException {
        NotationFacade notationFacade = NotationFacade.getInstance();
        return notationFacade.addNotation(notation);
    }

    public Notation[] getNotationByCoachId(int coachId) throws SQLException, ClassNotFoundException {
        NotationFacade notationFacade = NotationFacade.getInstance();
        return notationFacade.getNotationByCoachId(coachId);
    }

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
    public Notation[] getNotationByUserId(int userId) throws SQLException, ClassNotFoundException {
        NotationFacade notationFacade = NotationFacade.getInstance();
        return notationFacade.getNotationByUserId(userId);
    }

    public boolean deleteNotation(int notationid) throws SQLException, ClassNotFoundException {
        NotationFacade notationFacade = NotationFacade.getInstance();
        return notationFacade.deleteNotation(notationid);
    }

    public Notation getNotationById(int notationid) throws SQLException, ClassNotFoundException {
        NotationFacade notationFacade = NotationFacade.getInstance();
        return notationFacade.getNotationById(notationid);
    }

    public boolean modifyNotation(Notation notation) throws SQLException, ClassNotFoundException {
        NotationFacade notationFacade = NotationFacade.getInstance();
        return notationFacade.modifyNotation(notation);
    }

    public boolean addAvailability(Creneau_dispo creneau_dispo) throws SQLException, ClassNotFoundException {
        CreneauDispoFacade creneaudispofacade = CreneauDispoFacade.getInstance();
        return creneaudispofacade.addCreneauDispo(creneau_dispo);
    }

    public boolean modifyCreneauDispo(Creneau_dispo creneau_dispo) throws SQLException, ClassNotFoundException {
        CreneauDispoFacade creneaudispofacade = CreneauDispoFacade.getInstance();
        return creneaudispofacade.modifyCreneauDispo(creneau_dispo);
    }

    public List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day) throws SQLException, ClassNotFoundException {
        CreneauDispoFacade creneaudispofacade = CreneauDispoFacade.getInstance();
        return creneaudispofacade.getCreneauByDay(year, month, day);
    }

    public boolean deleteCreneauDispo(int creneau_dispo_id) throws SQLException, ClassNotFoundException {
        CreneauDispoFacade creneaudispofacade = CreneauDispoFacade.getInstance();
        return creneaudispofacade.deleteCreneauDispo(creneau_dispo_id);
    }

}
