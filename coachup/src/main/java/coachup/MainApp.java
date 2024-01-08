package coachup;

import coachup.controller.*;
import coachup.model.Coach;
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
                showWelcomePageAdmin(user);
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

    public int registerStudentUser(User user) throws SQLException, ClassNotFoundException {
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
}
