package coachup.model;

import coachup.facade.UserFacade;

import java.sql.SQLException;

/**
 * La classe Notation représente une notation attribuée par un utilisateur à un coach.
 * Chaque notation est définie par un identifiant de notation, un commentaire, une note, l'identifiant du coach et l'identifiant de l'utilisateur.
 */
public class Notation {

    private int NotationId;
    private String comment;
    private float note;
    private int CoachId;
    private int UserId;

    /**
     * Constructeur par défaut de la classe Notation.
     */
    public Notation() {
        // Constructeur par défaut
    }

    /**
     * Constructeur de la classe Notation avec des paramètres spécifiés.
     *
     * @param NotationId L'identifiant de la notation.
     * @param comment    Le commentaire associé à la notation.
     * @param note       La note attribuée.
     * @param CoachId    L'identifiant du coach.
     * @param UserId     L'identifiant de l'utilisateur.
     */
    public Notation(int NotationId, String comment, float note, int CoachId, int UserId) {
        this.NotationId = NotationId;
        this.comment = comment;
        this.note = note;
        this.CoachId = CoachId;
        this.UserId = UserId;
    }

    /**
     * Obtient l'identifiant de la notation.
     *
     * @return L'identifiant de la notation.
     */
    public int getNotationId() {
        return NotationId;
    }

    /**
     * Définit l'identifiant de la notation.
     *
     * @param NotationId L'identifiant de la notation à définir.
     */
    public void setNotationId(int NotationId) {
        this.NotationId = NotationId;
    }

    /**
     * Obtient le commentaire associé à la notation.
     *
     * @return Le commentaire associé à la notation.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Définit le commentaire associé à la notation.
     *
     * @param comment Le commentaire à définir.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Obtient la note attribuée.
     *
     * @return La note attribuée.
     */
    public float getNote() {
        return note;
    }

    /**
     * Définit la note attribuée.
     *
     * @param note La note à définir.
     */
    public void setNote(float note) {
        this.note = note;
    }

    /**
     * Obtient l'identifiant du coach.
     *
     * @return L'identifiant du coach.
     */
    public int getCoachId() {
        return CoachId;
    }

    /**
     * Définit l'identifiant du coach.
     *
     * @param CoachId L'identifiant du coach à définir.
     */
    public void setCoachId(int CoachId) {
        this.CoachId = CoachId;
    }

    /**
     * Obtient l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getUserId() {
        return UserId;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param UserId L'identifiant de l'utilisateur à définir.
     */
    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    /**
     * Obtient le nom du coach associé à la notation.
     *
     * @return Le nom du coach associé à la notation.
     * @throws SQLException            Si une erreur SQL survient lors de la récupération du coach.
     * @throws ClassNotFoundException  Si la classe du coach n'est pas trouvée lors de la récupération.
     * @throws RuntimeException        Si une exception est levée lors de la récupération du coach.
     */
    public String getCoachName() throws SQLException, ClassNotFoundException {
        UserFacade userfacade = UserFacade.getInstance();
        try {
            User coach = userfacade.getUserById(CoachId);
            return coach.getNom();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtient le nom de l'utilisateur associé à la notation.
     *
     * @return Le nom de l'utilisateur associé à la notation.
     */
    public String getUserName() {
        return "User Name";
    }
}
